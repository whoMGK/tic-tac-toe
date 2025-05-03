package org.ttt.strategies.winningStrategies;

import org.ttt.models.Board;
import org.ttt.models.Cell;
import org.ttt.models.Move;
import org.ttt.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    private HashMap<Symbol, HashMap<Integer, Integer>> columnMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int column = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!columnMap.containsKey(symbol)) {
            columnMap.put(symbol, new HashMap<>(Map.of(column, 0)));
        }
        columnMap.get(symbol).put(column, columnMap.get(symbol).getOrDefault(column, 0) + 1);

        return columnMap.get(symbol).get(column) == board.getDimension();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        Symbol symbol = move.getPlayer().getSymbol();
        int column = move.getCell().getColumn();
        columnMap.get(symbol).put(column, columnMap.get(symbol).get(column) - 1);
    }
}
