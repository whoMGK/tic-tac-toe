package org.ttt;

import org.ttt.controllers.GameController;
import org.ttt.exceptions.InvalidBotCountException;
import org.ttt.exceptions.InvalidPlayerCountException;
import org.ttt.models.*;
import org.ttt.strategies.winningStrategies.ColumnWinningStrategy;
import org.ttt.strategies.winningStrategies.DiagonalWinningStrategy;
import org.ttt.strategies.winningStrategies.RowWinningStrategy;
import org.ttt.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("p1", 1, new Symbol('X'), PlayerType.HUMAN));
        players.add(new Player("p2", 2, new Symbol('O'), PlayerType.HUMAN));
        List<WinningStrategy> winningStrategies = List.of(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy());
        Scanner scanner = new Scanner(System.in);
        int nextPlayerIndex = 1;

        GameController gameController = new GameController();
        Game game = gameController.startGame(dimension, players, winningStrategies, nextPlayerIndex);
        gameController.printBoard(game);
        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.makeMove(game);
            gameController.printBoard(game);
            //Undo
            System.out.println("Do you want to undo? (y/n): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                gameController.undo(game);
                gameController.printBoard(game);
            }
        }
        gameController.printBoard(game);
        Player winner = gameController.getWinner(game);
        if (winner != null) {
            System.out.println("Winner: " + gameController.getWinner(game).getName());
        } else {
            System.out.println("Game ended in a draw");
        }

    }
}