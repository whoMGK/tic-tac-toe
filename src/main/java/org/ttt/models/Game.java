package org.ttt.models;

import org.ttt.exceptions.InvalidBotCountException;
import org.ttt.exceptions.InvalidPlayerCountException;
import org.ttt.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private List<Move> moves;
    private Board board;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;
//    private int dimension;

    private Game(GameBuilder gb) {
        this.players = gb.players;
        this.nextPlayerIndex = gb.nextPlayerIndex;
        this.board = new Board(gb.dimension);
        this.winningStrategies = gb.winningStrategies;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static GameBuilder getGameBuilder() {
        return new GameBuilder();
    }

    //to-do
    public boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) return true;
        }
        return false;
    }

    public void makeMove() {
        //get current player
        Player currPlayer = players.get(nextPlayerIndex);
        System.out.println("Current Player: " + currPlayer.getName());
        //make a move
        Move move = currPlayer.makeMove(board);
        //mark cell
        Cell selectedCell = move.getCell();
        selectedCell.setPlayer(currPlayer);
        selectedCell.setCellState(CellState.OCCUPIED);
        //maintain prev moves
        moves.add(move);
        //update nextPlayerIndex
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        //check winner
        if (checkWinner(move)) {
            setGameState(GameState.ENDED);
        } else if (moves.size() == (board.getDimension() * board.getDimension())) {
            setGameState(GameState.DRAW);
        }
    }


    //Builder class
    public static class GameBuilder {
        private List<Player> players;
        private int dimension;
        List<WinningStrategy> winningStrategies;
        private int nextPlayerIndex;

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setNexPlayerIndex(int nexPlayerIndex) {
            this.nextPlayerIndex = nexPlayerIndex;
            return this;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void checkBotAndPlayerCount() throws InvalidBotCountException, InvalidPlayerCountException {
            int botCount = 0;
            int playerCount = 0;

            for (Player player : players) {
                playerCount++;
                if (player.getPlayerType().equals(PlayerType.BOT)) botCount++;
            }
            if (playerCount >= dimension) throw new InvalidPlayerCountException("Player Count should be less than N-1");
            if (botCount > 1) throw new InvalidBotCountException("Bot count should be less than 2");
        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException {
            checkBotAndPlayerCount();
        }

        public Game build() throws InvalidBotCountException, InvalidPlayerCountException {
            validate();
            return new Game(this);
        }
    }
}
