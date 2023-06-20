package controler;

import model.Board;
import model.Player;

public class Game {

    /**
     * class to implement a single game including a game Log
     *
     * @author Nadine Huetter
     */
    private final Board board ;
    private final Player player1;
    private final Player player2;
    private int currentPlayer;
    private String log ;
    private boolean isOver;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = 1;
        this.isOver = false;
        this.log="";
    }

    public boolean playGame(){
        /**
         * the method play Game implements the basic procedure of a single game,
         * starting with the Preparation
         *
         * @author Nadine Huetter
         */
        this.board.initializeBoard();
        Player winner= this.player1;

        while(!isOver){ //this.isOver gets changed in the method isOver
            if(currentPlayer==1) {
                this.board.printBoard();
                player1.move();
                currentPlayer=2;
            }else{
                this.board.printBoard();
                player2.move();
                currentPlayer=1;
            }
            winner=isOver();
        }
        return winner.isWhite();
    }

    public Player isOver(){
        /**
         * this method checks if either of the players have no pieces left.
         * is that the case it tells the game that its Over and gives back the
         * winning Player
         * If no one won jet, Player 1 is the default winner
         *
         * @author Nadine Huetter
         */
        if (player1.getAmountOfPieces() == 0) {
            this.isOver = true;
            return player2;
        } else if (player2.getAmountOfPieces()==0) {
            this.isOver = true;
            return player1;

        }else {
        return player1;
        }
    }




}
