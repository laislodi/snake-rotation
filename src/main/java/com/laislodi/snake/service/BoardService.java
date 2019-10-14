package com.laislodi.snake.service;

public class BoardService {

    private final int[][] board;
    private int x;
    private int y;
    private char dir;

    public BoardService(int[][] board, int x, int y, char dir) {
        this.board = board;
//        board[x][y] = -1;
//        if (dir == 'H') {
//            board[x+1][y] = -1;
//        } else {
//            board[x][y+1] = -1;
//        }
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDir() {
        return dir;
    }

    public void putSnake() {
        board[0][0] = -1;
        board[0][1] = -1;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                str.append(board[i][j]);
                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public int moveDown() {
        if (dir == 'V' && x < board.length - 2 && board[x+2][y] == 0) {
            board[x][y] = 0;
            x++;
            board[x+1][y] = -1;
            return 1;
        } else if (dir == 'H' && x < board.length - 1 && board[x+1][y] == 0 && board[x+1][y+1] == 0) {
            board[x][y] = 0;
            board[x][y+1] = 0;
            x++;
            board[x][y] = -1;
            board[x][y+1] = -1;
            return 1;
        }
        return 0;
    }

    private boolean canMoveDown() {
        return (dir == 'V' && x < board.length - 2 && board[x+2][y] == 0) ||
               (dir == 'H' && x < board.length - 1 && board[x+1][y] == 0 && board[x+1][y+1] == 0);
    }

    public int counterClockwise() {
        if (board[x][y+1] == 0 && board[x+1][y+1] == 0) {
            dir = 'H';
            board[x + 1][y] = 0;
            board[x][y + 1] = -1;
            return 1;
        }
        return 0;
    }

    public int moveRight() {
        if (dir == 'V' && y < board.length && board[x][y+1] == 0 && board[x+1][y+1] == 0) {
            board[x][y] = 0;
            board[x][y+1] = 0;
            y++;
            board[x][y] = -1;
            board[x+1][y] = -1;
            return 1;
        } else if (dir == 'H' && y < board.length - 2 && board[x][y+2] == 0) {
            board[x][y] = 0;
            y++;
            board[x][y+1] = -1;
            return 1;
        }
        return 0;
    }

    private boolean canMoveRight() {
        return (dir == 'V' && y < board.length && board[x][y+1] == 0 && board[x+1][y+1] == 0) ||
                (dir == 'H' && y < board.length - 2 && board[x][y+2] == 0);
    }

    public int clockwise() {
        if (board[x+1][y] == 0 && board[x+1][y+1] == 0) {
            dir = 'V';
            board[x][y + 1] = 0;
            board[x + 1][y] = -1;
            return 1;
        }
        return 0;

    }

    private boolean canTurn() {
        return (dir == 'H' && board[x+1][y] == 0 && board[x+1][y+1] == 0) ||
               (dir == 'V' && board[x][y+1] == 0 && board[x+1][y+1] == 0);
    }

    public int turn() {
        if (dir == 'H') {
            return clockwise();
        } else {
            return counterClockwise();
        }
    }

    private boolean gotIt() {
        return board[board.length-1][board.length - 1] == -1 && board[board.length-1][board.length - 2] == -1;
    }

    public int go() {
        int moves = 0;
        while (!gotIt()) {
            if (canMoveDown()) {
                moveDown();
            } else if (canMoveRight()){
                moveRight();
            } else if (canTurn()) {
                turn();
            } else {

            }
        }
        return moves;
    }
}
