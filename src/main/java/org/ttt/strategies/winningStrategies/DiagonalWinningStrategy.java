package org.ttt.strategies.winningStrategies;

import org.ttt.models.Board;
import org.ttt.models.Move;
import org.ttt.models.Symbol;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy {
    private HashMap<Symbol, Integer> leftDiagonal = new HashMap<>();
    private HashMap<Symbol, Integer> rightDiagonal = new HashMap<>();


    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int column = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        if (row == column) {
            if (!leftDiagonal.containsKey(symbol)) {
                leftDiagonal.put(symbol, 0);
            }
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);

            return leftDiagonal.get(symbol) == board.getDimension();
        }

        if (row + column == board.getDimension() - 1) {
            if (!rightDiagonal.containsKey(symbol)) {
                rightDiagonal.put(symbol, 0);
            }
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);

            return rightDiagonal.get(symbol) == board.getDimension();
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int column = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();
        if (row == column) {
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) - 1);
        }
        if (row - column == board.getDimension() - 1) {
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) - 1);
        }
    }
}
