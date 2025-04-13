package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int column, PlayingPiece piece){
        if (board[row][column]!=null) return false;
        board[row][column] = piece;
        return true;
    }

    public List<Pair<Integer,Integer>> getFreeCells(){

        List<Pair<Integer,Integer>> freeCellList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (board[i][j] == null){
                    Pair<Integer, Integer> rowColumn = new Pair<>(i,j);
                    freeCellList.add(rowColumn);
                }
            }
        }
        return freeCellList;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if (board[i][j] == null){
                    System.out.print(" ");
                }
                else {
                    System.out.print(board[i][j].pieceType + " ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
