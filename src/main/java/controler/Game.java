package controler;

import model.Board;
import model.Player;
/**
 * class to implement a single game
 *
 * @author Nadine Huetter
 */
public class Game {


    private Board board ;
    private final Player player1;
    private final Player player2;
    private int currentPlayer;
    private boolean isOver;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = 1;
        this.isOver = false;

    }
    /**
     * the method play Game implements the basic procedure of a single game,
     * starting with the Preparation
     *
     * @author Nadine Huetter
     */
    public boolean playGame(){

        this.board.initializeBoard();
        Player winner= this.player1;

        while(!this.isOver){ //this.isOver gets changed in the method isOver
            if(currentPlayer==1) {
                this.board.printBoard();
                this.board= player1.move(this.board, player2);
                currentPlayer=2;
            }else{
                this.board.printBoard();
                this.board=player2.move(this.board, player1);
                currentPlayer=1;
            }
            winner=isOver();
        }
        return winner.isWhite();
    }
    /**
     * this method checks if either of the players have no pieces left.
     * is that the case it tells the game that its Over and gives back the
     * winning Player
     * If no one won jet, Player 1 is the default winner
     *
     * @author Nadine Huetter
     */
    public Player isOver(){

        if (board.getWhitePieces() == 0) { //Player 1 is always White
            this.isOver = true;
            return player2;
        } else if (board.getBlackPieces()==0) { //Player 2 is always Black
            this.isOver = true;
            return player1;

        }else {
        return player1;
        }
    }




}
