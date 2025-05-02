package org.ttt.factory;

import org.ttt.models.BotDifficultyLevel;
import org.ttt.strategies.botPlayingStrategy.BotPlayingStrategy;
import org.ttt.strategies.botPlayingStrategy.EasyBotPlayingStrategy;
import org.ttt.strategies.botPlayingStrategy.HighBotPlayingStrategy;
import org.ttt.strategies.botPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.LOW)){
            return new EasyBotPlayingStrategy();
        } else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        } else if(botDifficultyLevel.equals(BotDifficultyLevel.HIGH)){
            return new HighBotPlayingStrategy();
        }
        return null;
    }
}