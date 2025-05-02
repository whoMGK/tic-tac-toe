package org.ttt.strategies.winningStrategies;

import org.ttt.models.Board;
import org.ttt.models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Move move);

    public void handleUndo(Board board, Move move);
}
