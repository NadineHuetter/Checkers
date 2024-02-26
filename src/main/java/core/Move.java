package core;

import model.Player;

/**
 * class Representing a single Move
 *
 * @author Nadine Huetter
 */

public class Move {
   private String move ;
   private int numberOfMoves;

    public Move() {
        this.numberOfMoves=0;
        this.move = "";

    }

    public String getMove() {
        return move;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
    /**
     * Method to add a step to the Move, using a StringBuilder
     *
     * @author Nadine Huetter
     */
    public void addMove(Player player, int startPosition , int endPosition, int jumpedStone){

        StringBuilder sb = new StringBuilder();

        if(player.isWhite()){
            sb.append("white;").append(startPosition).append(";").append(endPosition).append(";").append(jumpedStone).append(";");
        }
        else{
            sb.append("black;").append(startPosition).append(";").append(endPosition).append(";").append(jumpedStone).append(";");
        }
        this.move += sb.toString();
        this.numberOfMoves+=1;

    }
    /**
     * Method to access the coordinates of every Step in a Move
     *if there was no stone Jumped in this move the last element will be 0
     * first element is color of Player --> pls don't use
     * second is start point
     * third is end move
     * last gives position of Jumped stone
     *
     * @author Nadine Huetter
     *
     */
    public int[] getMove( int step){

        int[] move = new int[4];
        String[] elements = this.move.split(";");
        //System.out.println(elements.length);
       // System.out.println(step);

        if (elements[step * 4].equals("white")){
            move[0]= 1; //white is true
        }else{
            move[0]=0;  //black is true
        }

        for(int i =1; i<4 ; i++){
            move[i]= Integer.parseInt(elements[step*4+i]);
        }
        return move;
    }



}
