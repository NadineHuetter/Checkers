package model;

public class ComPlayer extends Player {
    /**
     * class representing the Computer as a Player
     * @param white
     */

    public ComPlayer(boolean white) {
        super(white);
    }

    @Override
    public Board move(Board board, Player opponent) {
        return null;
    }
}
