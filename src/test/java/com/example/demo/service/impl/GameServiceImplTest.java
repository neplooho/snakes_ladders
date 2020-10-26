package com.example.demo.service.impl;

import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.model.enumeration.GameStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    private static final int GAME_ID = 1;
    private static final int PLAYER_ID = 2;

    @InjectMocks
    private GameServiceImpl testedInstance;

    @Test
    void shouldPositionPlayerWhenGameStarts(){
        Player player = new Player();
        player.setId(PLAYER_ID);
        Game game = new Game();
        game.setId(GAME_ID);
        game.setGameStatus(GameStatus.CREATED);
        game.setPlayer(player);

        Game resultGame = testedInstance.startGame(game, player);
        assertEquals(1, resultGame.getBoardRow(), "After game started all players should be at 1:1");
        assertEquals(1, resultGame.getBoardColumn(), "After game started all players should be at 1:1");
        assertEquals(GameStatus.IN_PROGRESS, resultGame.getGameStatus(), "Game status should be in progress after game started");
    }

    @Test
    void shouldMovePlayerForwardFromStart(){
        Player player = new Player();
        player.setId(PLAYER_ID);
        Game game = new Game();
        game.setId(GAME_ID);
        game.setPlayer(player);
        game.setBoardColumn(1);
        game.setBoardRow(1);
        Game resultGame = testedInstance.movePlayer(game, 3);
        assertEquals(4, resultGame.getBoardColumn(), "Player should move by 3 blocks");
        assertEquals(1, resultGame.getBoardRow(), "Player should stay at same row");
        assertEquals(GameStatus.IN_PROGRESS, resultGame.getGameStatus(), "Game should still be playable");
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
        game.setBoardColumn(9);
        game.setBoardRow(7);
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
}