package com.example.demo.service.impl;

import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.model.enumeration.GameStatus;
import com.example.demo.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.model.enumeration.GameStatus.FINISHED;
import static com.example.demo.model.enumeration.GameStatus.IN_PROGRESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    private static final int GAME_ID = 1;
    private static final int PLAYER_ID = 2;

    @InjectMocks
    private GameServiceImpl testedInstance;

    @Mock
    private GameRepository gameRepository;

    @Test
    void shouldPositionPlayerWhenGameStarts(){
        Player player = new Player();
        player.setId(PLAYER_ID);
        Game game = new Game();
        game.setId(GAME_ID);
        game.setGameStatus(GameStatus.CREATED);
        game.setPlayer(player);

        Game startedGame = constructStartedGame(player);
        Mockito.when(gameRepository.save(game)).thenReturn(startedGame);
        Game resultGame = testedInstance.startGame(game, player);
        assertEquals(1, resultGame.getBoardRow(), "After game started all players should be at 1:1");
        assertEquals(1, resultGame.getBoardColumn(), "After game started all players should be at 1:1");
        assertEquals(IN_PROGRESS, resultGame.getGameStatus(), "Game status should be in progress after game started");
    }

    @Test
    void shouldMovePlayerForwardFromStart(){
        Player player = new Player();
        player.setId(PLAYER_ID);
        Game game = constructStartedGame(player);
        Game expectedGameState = constructStartedGame(player);
        expectedGameState.setBoardColumn(4);
        Mockito.when(gameRepository.save(expectedGameState)).thenReturn(expectedGameState);
        Game resultGame = testedInstance.movePlayer(game, 3);
        assertEquals(4, resultGame.getBoardColumn(), "Player should move by 3 blocks");
        assertEquals(1, resultGame.getBoardRow(), "Player should stay at same row");
        assertEquals(IN_PROGRESS, resultGame.getGameStatus(), "Game should still be playable");
    }

    @Test
    void shouldRollADice(){
        int result = testedInstance.rollDice();
        assertThat(result).isBetween(1, 6);
    }

    @Test
    void shouldWinTheGame() {
        Player player = new Player();
        player.setId(PLAYER_ID);
        Game game = new Game();
        game.setId(GAME_ID);
        game.setPlayer(player);
        game.setBoardColumn(7);
        game.setBoardRow(9);

        Game expectedGameStatus = constructFinishedGame(game.getId(), player);
        Mockito.when(gameRepository.save(expectedGameStatus)).thenReturn(expectedGameStatus);
        Game resultGame = testedInstance.movePlayer(game, 3);

        assertEquals(GameStatus.FINISHED, resultGame.getGameStatus(), "Player should win the game");
    }

    @Test
    void shouldNotWinTheGame() {
        Player player = new Player();
        player.setId(PLAYER_ID);
        Game game = new Game();
        game.setId(GAME_ID);
        game.setPlayer(player);
        game.setBoardColumn(9);
        game.setBoardRow(7);
        Game resultGame = testedInstance.movePlayer(game, 4);

        assertEquals(GameStatus.FINISHED, resultGame.getGameStatus(), "Player shouldn't win the game");
    }

    private Game constructStartedGame(Player player) {
        Game game = new Game();
        game.setPlayer(player);
        game.setBoardRow(1);
        game.setBoardColumn(1);
        game.setGameStatus(IN_PROGRESS);

        return game;
    }

    private Game constructFinishedGame(int gameId, Player player) {
        Game game = new Game();
        game.setId(gameId);
        game.setPlayer(player);
        game.setBoardRow(9);
        game.setBoardColumn(10);
        game.setGameStatus(FINISHED);

        return game;
    }
}
