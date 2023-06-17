package controler;

import model.Board;
import model.Player;

public class Game {
    private final Board board ;
    private final Player player1;
    private final Player player2;
    private int currentPlayer;
    private boolean isOver = false;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = 1;
    }

    public boolean playGame(){
        /**
         * Prepare game for Play
         */
        this.board.initializeBoard();
        Player winner= this.player1;

        /**
         *
         */

        while(!isOver){
            if(currentPlayer==1) {
                player1.move();
                currentPlayer=2;
            }else{
                player2.move();
                currentPlayer=1;
            }
            winner=isOver();
        }
        return winner.isWhite();
    }

    public Player isOver(){
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
