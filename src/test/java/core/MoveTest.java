package core;

import model.Player;
import model.UserPlayer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;



public class MoveTest {


    @Test
    public void addMove1(){
        Move move =new Move();

        Player player = new UserPlayer(true);
        int start = 61;
        int end =43;
        int jumped = 1;

        move.addMove(player,start,end,jumped);
        Assertions.assertEquals("white;61;43;1;",move.getMove());
        Assertions.assertEquals(1,move.getNumberOfMoves());


    }

    @Test
    public void addMove2(){
        Move move =new Move();

        Player player = new UserPlayer(true);
        int start = 61;
        int end =43;
        int jumped = 1;
        int start2 = 43;
        int end2 =25;
        int jumped2 = 1;

        move.addMove(player,start,end,jumped);
        move.addMove(player,start2,end2,jumped2);
        Assertions.assertEquals("white;61;43;1;white;43;25;1;",move.getMove());
        Assertions.assertEquals(2,move.getNumberOfMoves());


    }
    @Test
    public void addMove3(){
        Move move =new Move();

        Player player = new UserPlayer(false);
        int start = 61;
        int end =43;
        int jumped = 1;

        move.addMove(player,start,end,jumped);
        Assertions.assertEquals("black;61;43;1;",move.getMove());
        Assertions.assertEquals(1,move.getNumberOfMoves());


    }



    @Test
    public void getMove(){
        Move move = new Move();
        Player player = new UserPlayer(true);
        int start = 61;
        int end =43;
        int jumped = 1;
        int start2 = 43;
        int end2 =25;
        int jumped2 = 1;

        int[] expected1 = {1,61,43,1};
        int[] expected2 = {1,43,25,1};

        move.addMove(player,start,end,jumped);
        move.addMove(player,start2,end2,jumped2);

        int[] return1= move.getMove(0);
        int[] return2= move.getMove(1);

        Assertions.assertArrayEquals(expected1,return1);
        Assertions.assertArrayEquals(expected2,return2);



    }

}