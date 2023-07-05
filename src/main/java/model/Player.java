package model;

import core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class Player {
    /**
     * abstract class representing the Player
     *
     * @author Nadine Huetter
     *
     */

    private int amountOfPieces;
    private boolean white; //decides If the Player Plays the white or the Black Pieces

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

    public Move getjumpPossibilitys(int i, Board board){
        /**
         * test if one Piece can jump an opponents Piece
         *
         * it's Impossible for a Piece on the board to have two ways to jump because per turn only one way can open up,
         * and you are forced to immediately jump your opponents pieces if possible
         *
         * @author Nadine Huetter
         */
        Move jumpPossibilitys = new Move();
        Pieces currentPiece = board.getPiece(i);
        int currentPlace =i;


        if (currentPiece.getKind() == Kind.Pawn){

            if(jumpPossibilitys.getNumberOfMoves()== 0){
                if(this.isWhite()){
                    if(jump(currentPlace,board,Direction.NorthEast)){
                        jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.NorthEast.getMovement(),currentPlace+Direction.NorthEast.getMovement());
                        currentPlace=currentPlace+2*Direction.NorthEast.getMovement();
                    }
                    else if(jump(currentPlace,board,Direction.NorthWest)){
                        jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.NorthWest.getMovement(),currentPlace+Direction.NorthWest.getMovement());
                        currentPlace=currentPlace+2*Direction.NorthWest.getMovement();
                    }

                }else{
                    if(jump(currentPlace,board,Direction.SouthEast)){
                        jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.SouthEast.getMovement(),currentPlace+Direction.SouthEast.getMovement());
                        currentPlace=currentPlace+2*Direction.SouthEast.getMovement();
                    }
                    else if(jump(currentPlace,board,Direction.SouthWest)){
                        jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.SouthWest.getMovement(),currentPlace+Direction.SouthWest.getMovement());
                        currentPlace=currentPlace+2*Direction.SouthWest.getMovement();
                    }

                }

            }else {
                if(jump(currentPlace,board,Direction.NorthEast)){
                    jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.NorthEast.getMovement(),currentPlace+Direction.NorthEast.getMovement());
                    currentPlace=currentPlace+2*Direction.NorthEast.getMovement();
                }
                else if(jump(currentPlace,board,Direction.NorthWest)){
                    jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.NorthWest.getMovement(),currentPlace+Direction.NorthWest.getMovement());
                    currentPlace=currentPlace+2*Direction.NorthWest.getMovement();
                }
                else if(jump(currentPlace,board,Direction.SouthEast)){
                    jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.SouthEast.getMovement(),currentPlace+Direction.SouthEast.getMovement());
                    currentPlace=currentPlace+2*Direction.SouthEast.getMovement();
                }
                else if(jump(currentPlace,board,Direction.SouthWest)){
                    jumpPossibilitys.addMove(this,currentPlace,currentPlace+2*Direction.SouthWest.getMovement(),currentPlace+Direction.SouthWest.getMovement());
                    currentPlace=currentPlace+2*Direction.SouthWest.getMovement();
                }
            }

        }
        else {//TestDame


        }
        return jumpPossibilitys;
    } //TODO : Write

    public boolean jump(int i,Board board, Direction direction){

        if(this.isWhite()) {
            if (board.getPiece(i + direction.getMovement()).getColor() == Color.Black && board.getPiece(i + 2 * direction.getMovement()) == Pieces.Empty) {
                return true;
            }
        }else if (board.getPiece(i + direction.getMovement()).getColor() == Color.White && board.getPiece(i + 2 * direction.getMovement()) == Pieces.Empty) {
            return true;
        }
        return false;
    }


    public List<Move> getPossibleMoves(Board board){
        /**
         * creates a List with all possible Moves
         * If the can jump gave results, there won't be new Moves addet
         *
         * @author Nadine Huetter
         */
        List<Move> possibleMoves = new ArrayList<>();
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10 ; j++){
                Pieces currentPiece = board.getPiece(i*10+j);
                if(currentPiece == Pieces.Empty || currentPiece == Pieces.Boarder) {
                }
                else {
                    if (this.isWhite()) {
                        if(currentPiece.getColor() == Color.Black){// white can't move black Pieces
                        }
                        else {
                            Move tempMove = getjumpPossibilitys(i*10+j,board);
                            if(tempMove.getNumberOfMoves() != 0){
                                possibleMoves.add(tempMove);
                            }

                        }

                    } else {
                        if(currentPiece.getColor() == Color.White){
                          ; // black can't move white Pieces
                        }
                        else{ //jump movement of the Dame
                            Move tempMove = getjumpPossibilitys(i*10+j,board);
                            if(tempMove.getNumberOfMoves() != 0){
                                possibleMoves.add(tempMove);
                            }

                        }
                    }
                }
            }
        }

        /**
         * test if there were Jumps possible otherwise make a list of all the possible moves not including Jumps
         * if there were Jumps Possible Return the current list (only including the Possible Jumps
         */
        if(possibleMoves.isEmpty() ){
            for(int i = 0; i<10; i++){
                for(int j = 0; j<10 ; j++){
                    Pieces currentPiece = board.getPiece(i*10+j);
                    if(currentPiece == Pieces.Empty || currentPiece == Pieces.Boarder) {
                    }
                    else {
                        if (this.isWhite()) {
                            if(currentPiece.getColor() == Color.Black){// white can't move black Pieces
                            } else if (currentPiece == Pieces.PawnW) { //pawns can only jump direct neightbors
                                if(board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()) == Pieces.Empty){
                                    Move tempMove =new Move();
                                    tempMove.addMove(this, i*10+j,i * 10 + j + Direction.NorthWest.getMovement(),0 );
                                    possibleMoves.add(tempMove);
                                }

                                if(board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()) == Pieces.Empty){
                                    Move tempMove =new Move();
                                    tempMove.addMove(this, i*10+j,i * 10 + j + Direction.NorthEast.getMovement(),0 );
                                    possibleMoves.add(tempMove);

                                }

                            } else{ //jump movement of the Dame
                                int amountOfMovesNW = 0, amountOfMovesNE =0, amountOfMovesSW=0 ,amountOfMovesSE = 0;
                                Move tempMoveNW = new Move();
                                Move tempMoveNE = new Move();
                                Move tempMoveSW = new Move();
                                Move tempMoveSE = new Move();

                                while (board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNW.addMove(this,i * 10 + j +Direction.NorthWest.getMovement()*amountOfMovesNW,i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNW);

                                }
                                while (board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNE.addMove(this,i * 10 + j +Direction.NorthEast.getMovement()*amountOfMovesNW,i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNE);

                                }
                                while (board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSW.addMove(this,i * 10 + j +Direction.SouthWest.getMovement()*amountOfMovesNW,i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSW);

                                }

                                while (board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSE.addMove(this,i * 10 + j +Direction.SouthEast.getMovement()*amountOfMovesNW,i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSE);

                                }


                            }

                        } else {
                            if(currentPiece.getColor() == Color.White){
                                ; // black can't move white Pieces
                            } else if (currentPiece == Pieces.PawnB) { //pawns can only jump direct neightbors
                                if(board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()) == Pieces.Empty){
                                    Move tempMove =new Move();
                                    tempMove.addMove(this, i*10+j,i * 10 + j + Direction.SouthWest.getMovement(),0 );
                                    possibleMoves.add(tempMove);

                                }

                                if(board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()) == Pieces.Empty){
                                    Move tempMove =new Move();
                                    tempMove.addMove(this, i*10+j,i * 10 + j + Direction.SouthEast.getMovement(),0 );
                                    possibleMoves.add(tempMove);

                                }

                            } else{ //movement of the Dame
                                int amountOfMovesNW = 0, amountOfMovesNE =0, amountOfMovesSW=0 ,amountOfMovesSE = 0;
                                Move tempMoveNW = new Move();
                                Move tempMoveNE = new Move();
                                Move tempMoveSW = new Move();
                                Move tempMoveSE = new Move();
                                while (board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNW.addMove(this,i * 10 + j +Direction.NorthWest.getMovement()*amountOfMovesNW,i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNW);

                                }
                                while (board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNE.addMove(this,i * 10 + j +Direction.NorthEast.getMovement()*amountOfMovesNE,i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNE);

                                }
                                while (board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSW.addMove(this,i * 10 + j +Direction.SouthWest.getMovement()*amountOfMovesSW,i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSW);

                                }
                                while (board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSE.addMove(this,i * 10 + j +Direction.SouthEast.getMovement()*amountOfMovesSE,i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSE);

                                }


                            }
                        }
                    }
                }
            }

        }


        return possibleMoves;
    };


    public Board becomeDame (Board board){
        int startingField = 0;
        if(this.isWhite()){
            startingField = 11;
        }else{
            startingField = 92;
        }


        for(int i =0 ; i<4 ;i++){
            if(this.isWhite()){
                if(board.getPiece(startingField)== Pieces.PawnW){
                    board.setPiece(startingField,Pieces.DameW);


                }


            }else{
                if(board.getPiece(startingField)== Pieces.PawnB){
                    board.setPiece(startingField,Pieces.DameB);
                }
            }
            System.out.println(startingField);
            startingField += 2;

        }
        return board;
    }





    public abstract Board move(Board board, Player opponent);
}
