package model;

import core.Direction;
import core.Move;
import core.Pieces;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class PlayerTest {

    private Board testboardsetup(){
        Pieces[] testboard ={Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,
                                Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.Boarder,
                                Pieces.Boarder,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.PawnB,Pieces.Boarder,
                                Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.DameB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Boarder,
                                Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                                Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                                Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                                Pieces.Boarder,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                                Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                                Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder
        };
        Board board = new Board();
        board.setPieces(testboard);
        board.setBlackPieces(11);
        board.setWhitePieces(11);
        return board;
    }


    @Test
    public void jump1(){
        Player player =new UserPlayer(true);

       Board board = testboardsetup();
       Assertions.assertTrue(player.jump(62,board, Direction.NorthEast));
    }

    @Test
    public void jump2(){
        Player player =new UserPlayer(true);

        Board board = testboardsetup();
        Assertions.assertFalse(player.jump(62,board, Direction.NorthWest));
    }

    @Test
    public void jump3(){
        Player player =new UserPlayer(true);

        Board board = testboardsetup();
        Assertions.assertFalse(player.jump(75,board, Direction.NorthEast));
    }



    @Test
    public void testDame1(){
        Player player =new UserPlayer(false);

        Board board = testboardsetup();
        int result= player.testDame(33,board,Direction.SouthEast);
        int expected=66;

        Assertions.assertEquals(expected,result);

    }
    @Test
    public void testDame2(){
        Player player =new UserPlayer(false);

        Board board = testboardsetup();
        int result= player.testDame(33,board,Direction.SouthWest);
        int expected=0;
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void getPossibleMoves(){
        Player player = new UserPlayer(true);
        Board board = testboardsetup();
        List<Move> expected = new ArrayList<>();
        Move move = new Move();
        move.addMove(player,62,44,53);
        move.addMove(player,44,26,35);
        move.addMove(player,26,48,37);

        expected.add(move);

        List<Move> result = player.getPossibleMoves(board);
        Assertions.assertEquals(expected.size(),result.size());
        Assert.assertEquals(expected.get(0).getMove(),result.get(0).getMove());

    }

    @Test
    public void becomeDame(){
        Player player =new UserPlayer(true);

        Pieces[] expected ={Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,
                Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.DameW,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.PawnB,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.DameB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                Pieces.Boarder,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder
        };

        Board board = testboardsetup();
        board= player.becomeDame(board);
        for (int i = 0; i < 100; i++) Assertions.assertEquals(expected[i],board.getPiece(i) );

    }

    @Test
    public void becomeDame2(){
        Player player =new UserPlayer(false);

        Pieces[] expected ={Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,
                Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.PawnB,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.DameB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                Pieces.Boarder,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder
        };

        Board board = testboardsetup();
        board= player.becomeDame(board);
        for (int i = 0; i < 100; i++) Assertions.assertEquals(expected[i],board.getPiece(i) );

    }


}