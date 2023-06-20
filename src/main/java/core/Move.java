package core;

import model.Player;

public class Move {
   private String move ;
   private int numberOfMoves;

    public Move() {
        this.numberOfMoves=0;
        this.move = "";

    }

    public Move addMove(Player player, int startPosition , int endPositiion, int jumpedStone){
        StringBuilder sb = new StringBuilder();
        sb.append(player+ ";"+ startPosition+ ";"+ endPositiion + ";"+ jumpedStone);
        this.move += sb.toString();
        return null;
    }

    public int[] getMove( int step){
        /**
         * Method to access the cordinates of every Step in a Move
         *if there was no stone Jumped in this move the last element will be 0
         *
         */
        int[] move = new int[4];
        String[] elements = this.move.split(";");

        for(int i =0; i<4 ; i++){
            move[i]= Integer.parseInt(elements[step*4+i]);
        }
        return move;
    }



}
