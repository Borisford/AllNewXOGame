package su.ANV.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.ANV.entities.PlayGroundEntity;
import su.ANV.entities.PlayerEntity;
import su.ANV.entities.StepEntity;
import su.ANV.exeptions.*;
import su.ANV.models.Step;
import su.ANV.repositories.PlayGroundRepository;
import su.ANV.repositories.PlayerRepository;
import su.ANV.repositories.StepRepository;
import su.ANV.units.AI.AI;

import java.util.Random;

@Service
public class GameService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayGroundRepository playGroundRepository;

    @Autowired
    private StepRepository stepRepository;

    public PlayGroundEntity addPlayerToPlayerToPlayGround(Long playerKey, Long playGroundKey)
            throws NoGameException, NoPlayerException, GameIsFullException, PlayerAlreadyInGameException {
        if (!playerRepository.existsByPlayerKey(playerKey)) {
            throw new NoPlayerException("Игрок не найден");
        }
        if (!playGroundRepository.existsByPlayGroundKey(playGroundKey)) {
            throw new NoGameException("Игра не найдена");
        }
        PlayGroundEntity playGroundEntity = playGroundRepository.findByPlayGroundKey(playGroundKey);
        Long playerID = playerRepository.findByPlayerKey(playerKey).getId();
        if (playGroundEntity.playerIsIn(playerID)) {
            throw new PlayerAlreadyInGameException("Игрок уже добавлен в игру");
        }
        if (playGroundEntity.addPlayerID(playerID)) {
            return playGroundRepository.save(playGroundEntity);
        }
        throw new GameIsFullException("В игре нет свободных мест");
    }

    public PlayGroundEntity startMultiPlayerGame(Long playerKey) throws BadNumberOfPlayersException, GameIsFullException, PlayerAlreadyInGameException, BadPlaygroundSideException {
        return startComplexMultiPlayerGame(playerKey, 2);
    }

    public PlayGroundEntity startComplexMultiPlayerGame(Long playerKey, int numberOfPlayers)
            throws BadNumberOfPlayersException, BadPlaygroundSideException, PlayerAlreadyInGameException, GameIsFullException {
        Random random = new Random();
        Long playGroundKeyKey = random.nextLong();
        while (playGroundRepository.existsByPlayGroundKey(playGroundKeyKey)) {
            playGroundKeyKey = random.nextLong();
        }
        PlayGroundEntity playGroundEntity = new PlayGroundEntity(playGroundKeyKey, numberOfPlayers + 1, numberOfPlayers);
        Long playerID = playerRepository.findByPlayerKey(playerKey).getId();
        if (playGroundEntity.playerIsIn(playerID)) {
            throw new PlayerAlreadyInGameException("Игрок уже добавлен в игру");
        }
        if (playGroundEntity.addPlayerID(playerID)) {
            return playGroundRepository.save(playGroundEntity);
        }
        throw new GameIsFullException("В игре нет свободных мест");
    }

    public PlayGroundEntity startSinglePlayerGame(Long playerKey) throws BadNumberOfPlayersException, GameIsFullException, PlayerAlreadyInGameException, BadPlaygroundSideException {
        return startComplexSinglePlayerGame(playerKey, 2);
    }

    public PlayGroundEntity startComplexSinglePlayerGame(Long playerKey, int numberOfPlayers) throws BadNumberOfPlayersException, BadPlaygroundSideException, PlayerAlreadyInGameException, GameIsFullException {
        Random random = new Random();
        Long playGroundKeyKey = random.nextLong();
        while (playGroundRepository.existsByPlayGroundKey(playGroundKeyKey)) {
            playGroundKeyKey = random.nextLong();
        }
        PlayGroundEntity playGroundEntity = new PlayGroundEntity(playGroundKeyKey, numberOfPlayers + 1, numberOfPlayers);
        Long playerID = playerRepository.findByPlayerKey(playerKey).getId();
        if (playGroundEntity.playerIsIn(playerID)) {
            throw new PlayerAlreadyInGameException("Игрок уже добавлен в игру");
        }
        if (!playGroundEntity.addPlayerID(playerID)) {
            throw new GameIsFullException("В игре нет свободных мест");
        }
        for (int i = 1; i < playGroundEntity.getMaxPlayers(); i++) {
            if (!playGroundEntity.addPlayerID(AI.giveAI(1))) {
                throw new GameIsFullException("В игре нет свободных мест");
            }
        }
        return playGroundRepository.save(playGroundEntity);
    }

    public PlayGroundEntity startAutoGame() throws NotAIIDException, NoCellException, BadNumberOfPlayersException, NoVariantsException, GameOverException, GameIsFullException, NoPlayerInGameException, BadPlaygroundSideException, NotEmptyCellException {
        return startComplexAutoGame(2);
    }

    public PlayGroundEntity startComplexAutoGame(int numberOfPlayers)
            throws BadNumberOfPlayersException, BadPlaygroundSideException, GameIsFullException,
            NotAIIDException, NoCellException, NoVariantsException, NoPlayerInGameException, NotEmptyCellException, GameOverException {
        Random random = new Random();
        Long playGroundKeyKey = random.nextLong();
        while (playGroundRepository.existsByPlayGroundKey(playGroundKeyKey)) {
            playGroundKeyKey = random.nextLong();
        }
        PlayGroundEntity playGroundEntity = new PlayGroundEntity(playGroundKeyKey, numberOfPlayers + 1, numberOfPlayers);
        playGroundRepository.save(playGroundEntity);
        for (int i = 0; i < playGroundEntity.getMaxPlayers(); i++) {
            if (!playGroundEntity.addPlayerID(AI.giveAI(1))) {
                throw new GameIsFullException("В игре нет свободных мест");
            }
        }
        playGroundRepository.save(playGroundEntity);
        allAISteps(playGroundEntity);
        return playGroundRepository.save(playGroundEntity);
    }

    public String[] step(Step step)
            throws NotAIIDException, NoVariantsException, NoCellException, NoPlayerInGameException, NotEmptyCellException, IncorrectSignException, GameOverException, NoGameException {
        PlayGroundEntity playGroundEntity = playGroundRepository.getById(step.getPlayGroundId());
        //проверка на gameIsFull
        if (!playGroundEntity.getPlayGroundKey().equals(step.getPlayGroundKey())) {
            throw new NoGameException("Нет такой игры");
        }
        PlayerEntity playerEntity = playerRepository.getById(step.getPlayerId());
        if (!playerEntity.getPlayerKey().equals(step.getPlayerKey())) {
            throw new NoPlayerInGameException("В игре нет такого игрока");
        }
        char sign;
        allAISteps(playGroundEntity);
        int stepNo = playGroundEntity.getStepNo();
        sign = playGroundEntity.getPlayersSignByStepNo(step.getPlayGroundId());
        playGroundEntity.setSign(step.getPlayerId(), step.getCell());
        stepRepository.save(new StepEntity(step.getPlayGroundId(), step.getPlayGroundKey(), step.getPlayerId(), step.getPlayerKey(), step.getCell(), stepNo, sign));
        playGroundRepository.save(playGroundEntity);
        allAISteps(playGroundEntity);
        playGroundEntity = playGroundRepository.getById(step.getPlayGroundId());
        return playGroundEntity.getStrings();
    }

    private void allAISteps(PlayGroundEntity playGroundEntity) throws NotAIIDException, NoVariantsException, NoCellException, NoPlayerInGameException, NotEmptyCellException, GameOverException {
        int stepNo;
        int cell;
        char sign;
        Long tmpAIID;
        whoWin(playGroundEntity);
        while (playGroundEntity.isThisPlayerAI()) {
            stepNo = playGroundEntity.getStepNo();
            tmpAIID = playGroundEntity.getCurrentPlayersID();
            sign = playGroundEntity.getPlayersSignByStepNo(tmpAIID);
            cell = AI.ai(playGroundEntity.getContent(), tmpAIID);
            playGroundEntity.setSign(tmpAIID, cell);
            stepRepository.save(new StepEntity(playGroundEntity.getId(), playGroundEntity.getPlayGroundKey(), tmpAIID, tmpAIID, cell, stepNo, sign));
            playGroundRepository.save(playGroundEntity);
            whoWin(playGroundEntity);
        }
    }

    private void whoWin(PlayGroundEntity playGroundEntity) throws GameOverException {
        char win = playGroundEntity.whoWin();
        if (win == 0) {
            return;
        }
        if (win == 1) {
            throw new GameOverException("Игра закончилась вничью.");
        }
        Long winnerId = playGroundEntity.getPlayersIDBySign(win);
        if (winnerId < 0) {
            throw new GameOverException("Бот победил.");
        }
        throw new GameOverException("Победил " + playerRepository.getById(winnerId).getName());
    }
}
