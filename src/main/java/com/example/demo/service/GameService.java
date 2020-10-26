package com.example.demo.service;

import com.example.demo.model.Game;
import com.example.demo.model.Player;

public interface GameService {
    /**
     * Moves player in particular game by {@code positions} forward
     * @param game the input
     * @param positions rolled number of moves
     * @return result of operation, current player position
     */
    Game movePlayer(Game game, int positions);

    /**
     * Changes status of the game to started and puts player to his position
     * @param game to be started
     * @param player who starts the game
     * @return current game data
     */
    Game startGame(Game game, Player player);

    /**
     * Returns random integer from 1 to 6 inclusively
     * @return random dice value
     */
    int rollDice();
}
