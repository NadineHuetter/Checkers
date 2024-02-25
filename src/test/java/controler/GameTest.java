package controler;

import controler.Game;
import model.Board;
import model.Player;
import model.UserPlayer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;


public class GameTest{
    @Test
    public void isOver(){
        Board board = new Board();
        Player player1 = new UserPlayer(true);
        Player player2 = new UserPlayer(false);
        Game game = new Game(board,player1,player2);
        Player winner;
        boolean isOver;

        board.setBlackPieces(3);
        board.setWhitePieces(0);

        winner = game.isOver();

        isOver = game.getIsOver();
        Assertions.assertEquals(winner,player2);
        Assertions.assertTrue(isOver);


    }
    @Test
    public void isOver2(){
        Board board = new Board();
        Player player1 = new UserPlayer(true);
        Player player2 = new UserPlayer(false);
        Game game = new Game(board,player1,player2);
        Player winner;
        boolean isOver;

        board.setBlackPieces(0);
        board.setWhitePieces(2);

        winner = game.isOver();

        isOver = game.getIsOver();
        Assertions.assertEquals(winner,player1);
        Assertions.assertTrue(isOver);

    }
    @Test
    public void isOver3(){
        Board board = new Board();
        Player player1 = new UserPlayer(true);
        Player player2 = new UserPlayer(false);
        Game game = new Game(board,player1,player2);
        Player winner;
        boolean isOver;

        board.setBlackPieces(6);
        board.setWhitePieces(2);

        winner = game.isOver();

        isOver = game.getIsOver();
        Assertions.assertEquals(winner,player1);
        Assertions.assertFalse(isOver);

    }

}