package model;

import core.Color;
import core.Direction;
import core.Move;
import core.Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Player {
    /**
     * abstract class representing the Player
     *
     * @author Nadine Huetter
     *
     */

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

    public List<Move> getjumpPossibilitys(int i, Board board){
        /**
         * test if one Piece can jump an opponents Piece
         *
         * @author Nadine Huetter
         */
        List<Move> jumpPossibilitys = new ArrayList<>();

        Pieces currentPiece = board.getPiece(i);

        return jumpPossibilitys;
    } //TODO : Write

    public boolean jump(int i,Board board, Direction direction){
        if( board.getPiece(i + direction.getMovement()).getColor() == Color.Black && board.getPiece(i+2* direction.getMovement()) == Pieces.Empty) {
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
                        } else if (currentPiece == Pieces.PawnW) { //pawns can only jump direct neightbors

                        } else{ //jump movement of the Dame

                        }

                    } else {
                        if(currentPiece.getColor() == Color.White){
                          ; // black can't move white Pieces
                        } else if (currentPiece == Pieces.PawnB) { //pawns can only jump direct neightbors

                        } else{ //jump movement of the Dame

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

                                }while (board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
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

                                }while (board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSE.addMove(this,i * 10 + j +Direction.SouthEast.getMovement()*amountOfMovesNW,i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW,0);
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

    public String move() {
        /**
         * the actual move of the player
         * returns a string informing the log what just happened
         *
         * @author Nadine Huetter
         */
        return "";

    }



    }
