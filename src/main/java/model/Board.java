

package model;

import core.Pieces;

public class Board {
    private Pieces[] board = new Pieces[10*10];

    public Pieces getPiece(int i){
        return board[i];
    }
    public void setPiece(int i, Pieces piece){
        board[i]= piece;
    }


    public void initializeBoard(){

        for(int i = 0; i<100; i++ ){

           int col = i%10;
           int row= (i-col)/10;

           if (row ==0 || row == 9 || col == 0 || col == 9){
             board[i] = Pieces.Boarder;
              //Border
           } else if ((row+col)%2 == 0 && row>0 && row <4) {
               board[i] = Pieces.PawnB;//PawnB
           } else if ((row+col)%2 == 0 && row>5 && row <9) {
               board[i] = Pieces.PawnW;//PawnW
           }else{
               board[i] = Pieces.Empty;//Empty
           }

        }

    }

    public void printBoard(){
        /**
         * Method to Print the current board
         */
        for(int i = 0; i<10 ;i++){
            for (int j = 0; j<10; j++){
                System.out.print(board[i*10+j].getUnicode()+ "|");

            }
            System.out.print("\n------------------------------------------\n");
        }
    }

}

