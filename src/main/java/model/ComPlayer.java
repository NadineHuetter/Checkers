package model;

public class ComPlayer extends Player {
    /**
     * class representing the Computer as a Player
     *
     */

    public ComPlayer(boolean white) {
        super(white);
    }

    @Override
    public Integer chooseMove(int numOfMoves){
        return 0;
    }

}
