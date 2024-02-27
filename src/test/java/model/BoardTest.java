package model;

import core.Pieces;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
public class BoardTest {
    @Test
    public void initializeBoard(){
        Board board = new Board();
        Pieces[] result = new Pieces[]{Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.PawnB,Pieces.Empty,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Empty,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Empty,Pieces.PawnW,Pieces.Boarder,
                                        Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder,Pieces.Boarder
                                };

        board.initializeBoard();
        for (int i = 0; i < 100; i++) Assertions.assertEquals(result[i],board.getPiece(i) );

       // Assertions.assertArrayEquals(result, board);




    }

   // @Test   //Not needet due to no output (only output via Konsole)
    //public void printBoard(){}

}