import org.junit.jupiter.api.Test;
import org.testng.Assert;
import su.ANV.entities.PlayerEntity;

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


}