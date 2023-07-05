package core;

import model.Player;

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

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public Move addMove(Player player, int startPosition , int endPositiion, int jumpedStone){
        StringBuilder sb = new StringBuilder();

        if(player.isWhite()){
            sb.append( "white;"+ startPosition+ ";"+ endPositiion + ";"+ jumpedStone);
        }
        else{
            sb.append( "black;"+ startPosition+ ";"+ endPositiion + ";"+ jumpedStone);
        }
        this.move += sb.toString();
        this.numberOfMoves+=1;
        return null;
    }

    public int[] getMove( int step){
        /**
         * Method to access the cordinates of every Step in a Move
         *if there was no stone Jumped in this move the last element will be 0
         * first element is color of Player --> pls don't use
         * second is startpoint
         * third is endmove
         * last gives position of Jumped stone
         *
         */
        int[] move = new int[4];
        String[] elements = this.move.split(";");
        System.out.println(elements.length);
        System.out.println(step);

        if (elements[step*4 + 0]== "white"){
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
