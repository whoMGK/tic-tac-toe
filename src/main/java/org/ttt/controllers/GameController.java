package org.ttt.controllers;

import org.ttt.exceptions.InvalidBotCountException;
import org.ttt.exceptions.InvalidPlayerCountException;
import org.ttt.models.Board;
import org.ttt.models.Game;
import org.ttt.models.Player;
import org.ttt.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies, int nextPlayerIndex) throws InvalidBotCountException, InvalidPlayerCountException {
        return Game.getGameBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setNexPlayerIndex(nextPlayerIndex)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.getBoard().printBoard();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void undo() {

    }

}
