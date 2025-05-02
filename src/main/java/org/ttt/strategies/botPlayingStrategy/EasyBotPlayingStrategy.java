package org.ttt.strategies.botPlayingStrategy;

import org.ttt.models.Board;
import org.ttt.models.Cell;
import org.ttt.models.CellState;
import org.ttt.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(cell, null);
                }
            }
        }
        return null;
    }
}
