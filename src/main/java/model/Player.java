package model;

import core.Move;

public class Player {

    int amountOfPieces;
    boolean white; //decides If the Player Plays the white or the Black Pieces

    public Player(boolean white) {
        this.amountOfPieces=12;
        this.white = white;
    }

    public int getAmountOfPieces() {
        return amountOfPieces;
    }

    public void setAmountOfPieces(int amountOfPieces) {
        this.amountOfPieces = amountOfPieces;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public int canJump(int i){ // test if one Piece can jump an opponents Piece

        return 0;
    }

    public Move[] getPossibleMoves(){
        Move[] possibleMoves = new Move[1];
        return possibleMoves;
    };

    public void move() {

    }



    }
