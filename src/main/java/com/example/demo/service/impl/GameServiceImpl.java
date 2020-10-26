package com.example.demo.service.impl;

import com.example.demo.model.Game;
import com.example.demo.model.Player;
import com.example.demo.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Game movePlayer(Game game, int positions) {
        return new Game();
    }

    @Override
    public Game startGame(Game game, Player player) {
        return new Game();
    }

    @Override
    public int rollDice() {
        return 0;
    }
}
