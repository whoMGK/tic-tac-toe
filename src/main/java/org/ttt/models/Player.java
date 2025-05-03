package org.ttt.models;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private String name;
    private int id;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name, int id, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        int row, column;
        while (true) {
            try {
                System.out.println("Enter row");
                row = scanner.nextInt();
                System.out.println("Enter column");
                column = scanner.nextInt();

                if (row < 0 || column < 0 || row >= board.getDimension() || column >= board.getDimension()) {
                    System.out.println("Invalid row or column");
                    board.printBoard();
                    continue;
                }
                if (board.getBoard().get(row).get(column).getCellState().equals(CellState.OCCUPIED)) {
                    System.out.println("This cell is occupied");
                    board.printBoard();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: Please enter numbers only");
                board.printBoard();
                scanner.nextLine();
            }
        }
        return new Move(board.getBoard().get(row).get(column), this);
    }
}
