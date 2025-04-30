package org.ttt.controllers;

import org.ttt.exceptions.InvalidBotCountException;
import org.ttt.exceptions.InvalidPlayerCountException;
import org.ttt.models.Board;
import org.ttt.models.Game;
import org.ttt.models.Player;
import org.ttt.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies, int nextPlayerIndex) throws InvalidBotCountException, InvalidPlayerCountException {
        Game game = Game.getGameBuilder().setDimension(dimension).setPlayers(players).setNexPlayerIndex(nextPlayerIndex).setWinningStrategies(winningStrategies).build();
        return game;
    }
    public Player getWinner(){
        return null;
    }

    public void print(){

    }

    public void makeMove(){

    }

    public void undo(){

    }

}
