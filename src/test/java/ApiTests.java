import org.junit.jupiter.api.Test;
import org.testng.Assert;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.entities.PlayerEntity;
import su.ANV.exeptions.IncorrectSignException;
import su.ANV.exeptions.NoCellException;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void createPlayerTest() {
        String vovaName = RandomName.get();

        PlayerEntity vova = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + vovaName)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);

        Assert.assertEquals(vova.getName(), vovaName);

        given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + vovaName)
                .then()
                .log().body()
                .statusCode(400);
    }

    @Test
    public void getPlayerTest() {
        String vovaName = RandomName.get();

        PlayerEntity postVova = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + vovaName)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);

        Assert.assertEquals(postVova.getName(), vovaName);

        PlayerEntity getVova = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/players/" + postVova.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);

        Assert.assertEquals(postVova.getName(), getVova.getName());
        Assert.assertEquals(postVova.getId(), getVova.getId());
    }

    @Test
    public void testSimpleAutoGameTest() {
        String result = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/auto")
                .then()
                .log().body()
                .statusCode(400)
                .extract()
                .asString();

        Assert.assertTrue(result.startsWith("Игра закончилась"));


    }

    @Test
    public void testComplexAutoGameTest() {
        String result = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/complex/auto?numberOfPlayers=5")
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();

        Assert.assertTrue(result.startsWith("Игра закончилась"));
    }

    @Test
    public void testSimpleMultiPlayerGameTest() throws NoCellException, IncorrectSignException {
        String playerOneName = RandomName.get();
        String playerTwoName = RandomName.get();

        PlayerEntity playerOne = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + playerOneName)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);

        PlayerEntity playerTwo = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + playerTwoName)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);

        PlayGroundEntity game = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/multi?playerKey=" + playerOne.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        game = given()
                .when()
                .put("http://localhost:9090/gameplay/rest/players?playerKey=" + playerTwo.getPlayerKey() +"&playGroundKey="+ game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        String requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerOne.getId()+",\n" +
                "    \"playerKey\" : "+playerOne.getPlayerKey()+",\n" +
                "    \"cell\" : 4\n" +
                "}";
        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(200);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[4], 'x');

        requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerTwo.getId()+",\n" +
                "    \"playerKey\" : "+playerTwo.getPlayerKey()+",\n" +
                "    \"cell\" : 0\n" +
                "}";

        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(200);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[0], '*');

        requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerOne.getId()+",\n" +
                "    \"playerKey\" : "+playerOne.getPlayerKey()+",\n" +
                "    \"cell\" : 6\n" +
                "}";
        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(200);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[6], 'x');

        requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerTwo.getId()+",\n" +
                "    \"playerKey\" : "+playerTwo.getPlayerKey()+",\n" +
                "    \"cell\" : 2\n" +
                "}";

        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(200);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[2], '*');

        requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerOne.getId()+",\n" +
                "    \"playerKey\" : "+playerOne.getPlayerKey()+",\n" +
                "    \"cell\" : 8\n" +
                "}";
        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(200);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[8], 'x');

        requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerTwo.getId()+",\n" +
                "    \"playerKey\" : "+playerTwo.getPlayerKey()+",\n" +
                "    \"cell\" : 1\n" +
                "}";

        String result = given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();

        Assert.assertEquals(result, "Игра закончилась победой игрока " + playerTwo.getName());

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[1], '*');

         String winner = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/winners/game/" + game.getId())
                .then()
                .log().body()
                .statusCode(200)
                .extract().asString();

         Assert.assertEquals(winner, playerTwo.getName());
    }
}
