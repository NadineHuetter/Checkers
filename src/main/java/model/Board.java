

package model;

import core.Pieces;
/**
 * Implementation of the Board as an array
 *
 * @author Nadine Huetter
 */
public class Board {
    private final Pieces[] board = new Pieces[10*10];
    private int whitePieces;
    private int blackPieces;

    /**
     * funktion to Declare the Board purely for testing purposes
     * @param pieces
     */
    public void setPieces(Pieces[] pieces){
        for (int i = 0; i < 100; i++) {
            board[i]=pieces[i];

        }
    }
    public Pieces getPiece(int i){
        return board[i];
    }
    public void setPiece(int i, Pieces piece){
        board[i]= piece;
    }

    public int getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(int whitePieces) {
        this.whitePieces = whitePieces;
    }

    public int getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(int blackPieces) {
        this.blackPieces = blackPieces;
    }
    /**
     * Sets up the starting Position of the stones and marks the border
     *
     * @author Nadine Huetter
     */
    public void initializeBoard(){


        for(int i = 0; i<100; i++ ){

           int col = i%10;
           int row= (i-col)/10;

           if (row ==0 || row == 9 || col == 0 || col == 9){
             board[i] = Pieces.Boarder;
              //Border
           } else if ((row+col)%2 == 0 && row <4) {
               board[i] = Pieces.PawnB;//PawnB
           } else if ((row+col)%2 == 0 && row>5 ) {
               board[i] = Pieces.PawnW;//PawnW
           }else{
               board[i] = Pieces.Empty;//Empty
           }

        }
        this.whitePieces=12;
        this.blackPieces=12;

    }

    /**
     * Method to Print the current board utilizing Unicode
     *
     * @author Nadine Huetter
     */
    public void printBoard(){

        for(int i = 0; i<10 ;i++){
            for (int j = 0; j<10; j++){
                System.out.print(board[i*10+j].getUnicode()+ "|");

            }
            System.out.print("\n------------------------------------------\n");
        }
    }

}

