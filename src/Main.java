import game.TicTacToe;

public class Main{
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println("Game winner is : " + ticTacToe.startGame());
    }
}