package com.laislodi.snake;

import com.laislodi.snake.service.BoardService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // read the board - return a matrix
        BoardService service = readBoard();

        // move the snake from [(0,0),(0,1)] to [(n-1, n-2),(n-1, n-1)]
        // receive the board, return the number of moves
        service.putSnake();
        System.out.println(service.go());

//        service.clockwise();
//        service.counterClockwise();
//        System.out.println(service.moveRight());
//        service.moveDown();
//        service.moveRight();
//        service.moveRight();
//        service.moveDown();
//        service.moveRight();
//        service.moveDown();
//        service.moveDown();
//        service.moveDown();
//        service.moveRight();


        // toString the number of moves
        System.out.println(service.toString());
    }

    private static BoardService readBoard() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ll94707\\IdeaProjects\\snake-rotation\\src\\main\\resources\\input.txt"));

        int n = 0;
        List<String> lines = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
            n++;
        }

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] split = lines.get(i).split(",");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.valueOf(split[j]);
            }
        }
        return new BoardService(board, 0, 0, 'H');
    }

}
