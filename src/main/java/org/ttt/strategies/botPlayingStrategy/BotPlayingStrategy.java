package org.ttt.strategies.botPlayingStrategy;

import org.ttt.models.Board;
import org.ttt.models.Move;

public interface BotPlayingStrategy {
    public Move makeMove(Board board);
}
