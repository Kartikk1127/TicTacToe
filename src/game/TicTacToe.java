package game;

import enums.PieceType;
import model.*;

import java.util.*;

public class TicTacToe {

    Deque<Player> players; //deque is taken because once a player has played his move, he will be allowed to play only after all the players
    //have played their moves. Hence, deque will help us in popping a player from the top(once he plays) and putting the player in the last.
    Board board;

    public TicTacToe(){
        initializeGame();
    }

    private void initializeGame() {

        //creating 2 players
        players = new LinkedList<>();
        PlayingPieceX pieceX = new PlayingPieceX();
        Player player1 = new Player("Kartik",pieceX);

        PlayingPieceO pieceO = new PlayingPieceO();
        Player player2 = new Player("Rameshwar",pieceO);

        players.add(player1);
        players.add(player2);

        //initializing the board
        board = new Board(3);
    }

    public String startGame(){

        boolean noWinner = true;
        while (noWinner){

            //take out the player whose turn is and put the payer in the list back
            Player playerTurn  = players.removeFirst();

            //get the free space from the board
            board.printBoard();
            List<Pair<Integer,Integer>> freeSpaces = board.getFreeCells();
            if (freeSpaces.isEmpty()){
                noWinner = false;
                continue;
            }

            //read the user input
            System.out.println("Player: " + playerTurn.getName() + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String [] values = s.split(",");
            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);

            //place the piece
            boolean isAdded = board.addPiece(inputRow,inputColumn,playerTurn.getPiece());
            if (!isAdded){
                //player cannot insert the piece in this cell, player has to choose another cell
                System.out.println("Incorrect position, please try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);
            boolean winner = isThereWinner(inputRow,inputColumn,playerTurn.getPiece().pieceType);
            if (winner) return playerTurn.getName();
        }
        return "tie";
    }

    private boolean isThereWinner(int inputRow, int inputColumn, PieceType pieceType) {
        boolean rowMatched = true, columnMatched = true, diagonalMatched = true, antiDiagonalMatched = true;
        for(int i = 0; i < board.size; i++){
            if (board.board[inputRow][i] == null || board.board[inputRow][i].pieceType != pieceType) {
                rowMatched = false;
                break;
            }
        }
        for (int i = 0; i < board.size; i++){
            if (board.board[i][inputColumn] == null || board.board[i][inputColumn].pieceType != pieceType) {
                columnMatched = false;
                break;
            }
        }
        for (int i = 0, j = 0; i < board.size; i++,j++){
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                diagonalMatched = false;
                break;
            }
        }

        for (int i = 0,j = board.size-1; i < board.size; i++,j--){
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                antiDiagonalMatched = false;
                break;
            }
        }

        return rowMatched || columnMatched || diagonalMatched || antiDiagonalMatched;
    }


}
