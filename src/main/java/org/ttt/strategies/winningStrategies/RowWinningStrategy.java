package org.ttt.strategies.winningStrategies;

import org.ttt.models.Board;
import org.ttt.models.Move;
import org.ttt.models.Symbol;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy {
    private HashMap<Symbol, HashMap<Integer, Integer>> rowMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMap.putIfAbsent(symbol, new HashMap<>());
        rowMap.get(symbol).put(row, rowMap.get(symbol).getOrDefault(row, 0) + 1);

        return rowMap.get(symbol).get(row) == board.getDimension();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        rowMap.get(symbol).put(row, rowMap.get(symbol).get(row) - 1);
    }
}
