package com.example.demo.service.impl;

import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.model.enumeration.GameStatus;
import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game movePlayer(Game game, int positions) {
        int newColumnPosition = game.getBoardColumn() + positions;
        game.setBoardColumn(newColumnPosition);
        return gameRepository.save(game);
    }

    @Override
    public Game startGame(Game game, Player player) {
        game.setGameStatus(GameStatus.IN_PROGRESS);
        game.setBoardRow(1);
        game.setBoardColumn(1);
        return gameRepository.save(game);
    }

    @Override
    public int rollDice() {
        return new Random().nextInt(6) + 1;
    }
}
