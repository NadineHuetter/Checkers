package model;

import core.*;

import java.util.ArrayList;
import java.util.List;


public abstract class Player {
    /**
     * abstract class representing the Player
     *
     */

    final private boolean white; //decides If the Player Plays the white or the Black Pieces

    public Player(boolean white) {

        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    /**
     * test if one Piece can jump an opponents Piece
     * it's Impossible for a Piece on the board to have two ways to jump because per turn only one way can open up,
     * and you are forced to immediately jump your opponents pieces if possible
     *
     * @author Nadine Huetter
     */
    public List<Move> getJumpPossibilities(int i, Board board){

        Move jumpPossibilities = new Move();
        List<Move> allPossibilities = new ArrayList<>();
        Pieces currentPiece = board.getPiece(i);
        int currentPlace =i;


        if (currentPiece.getKind() == Kind.Pawn){
            while(true) {

                if (jumpPossibilities.getNumberOfMoves() == 0) {
                    if (this.isWhite()) {
                        if (jump(currentPlace, board, Direction.NorthEast)) {
                            jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.NorthEast.getMovement(), currentPlace + Direction.NorthEast.getMovement());
                            board.setPiece(currentPlace + Direction.NorthEast.getMovement(),Pieces.Empty);
                            currentPlace = currentPlace + 2 * Direction.NorthEast.getMovement();
                        } else if (jump(currentPlace, board, Direction.NorthWest)) {
                            jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.NorthWest.getMovement(), currentPlace + Direction.NorthWest.getMovement());
                            board.setPiece(currentPlace + Direction.NorthWest.getMovement(),Pieces.Empty);
                            currentPlace = currentPlace + 2 * Direction.NorthWest.getMovement();
                        } else {break;}

                    } else {
                        if (jump(currentPlace, board, Direction.SouthEast)) {
                            jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.SouthEast.getMovement(), currentPlace + Direction.SouthEast.getMovement());
                            board.setPiece(currentPlace + Direction.SouthEast.getMovement(),Pieces.Empty);
                            currentPlace = currentPlace + 2 * Direction.SouthEast.getMovement();
                        } else if (jump(currentPlace, board, Direction.SouthWest)) {
                            jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.SouthWest.getMovement(), currentPlace + Direction.SouthWest.getMovement());
                            board.setPiece(currentPlace + Direction.SouthWest.getMovement(),Pieces.Empty);
                            currentPlace = currentPlace + 2 * Direction.SouthWest.getMovement();
                        } else{break;}

                    }

                } else {
                    if (jump(currentPlace, board, Direction.NorthEast)) {
                        jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.NorthEast.getMovement(), currentPlace + Direction.NorthEast.getMovement());
                        board.setPiece(currentPlace + Direction.NorthEast.getMovement(),Pieces.Empty);
                        currentPlace = currentPlace + 2 * Direction.NorthEast.getMovement();
                    } else if (jump(currentPlace, board, Direction.NorthWest)) {
                        jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.NorthWest.getMovement(), currentPlace + Direction.NorthWest.getMovement());
                        board.setPiece(currentPlace + Direction.NorthWest.getMovement(),Pieces.Empty);
                        currentPlace = currentPlace + 2 * Direction.NorthWest.getMovement();
                    } else if (jump(currentPlace, board, Direction.SouthEast)) {
                        jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.SouthEast.getMovement(), currentPlace + Direction.SouthEast.getMovement());
                        board.setPiece(currentPlace + Direction.SouthEast.getMovement(),Pieces.Empty);
                        currentPlace = currentPlace + 2 * Direction.SouthEast.getMovement();
                    } else if (jump(currentPlace, board, Direction.SouthWest)) {
                        jumpPossibilities.addMove(this, currentPlace, currentPlace + 2 * Direction.SouthWest.getMovement(), currentPlace + Direction.SouthWest.getMovement());
                        board.setPiece(currentPlace + Direction.SouthWest.getMovement(),Pieces.Empty);
                        currentPlace = currentPlace + 2 * Direction.SouthWest.getMovement();
                    }
                    else{break;}
                }
            }
            
            allPossibilities.add(jumpPossibilities);

        }
        else {//TestDame
            if(testDame(i,board,Direction.NorthEast) != 0){
                Move tempMove =new Move();
                tempMove.addMove(this,i,testDame(i,board,Direction.NorthEast)+Direction.NorthEast.getMovement(),testDame(i,board,Direction.NorthEast));
                allPossibilities.add(tempMove);
            }
            if(testDame(i,board,Direction.NorthWest) != 0){
                Move tempMove =new Move();
                tempMove.addMove(this,i,testDame(i,board,Direction.NorthWest)+Direction.NorthWest.getMovement(),testDame(i,board,Direction.NorthWest));
                allPossibilities.add(tempMove);
                
            }
            if(testDame(i,board,Direction.SouthEast) != 0){
                Move tempMove =new Move();
                tempMove.addMove(this,i,testDame(i,board,Direction.SouthEast)+Direction.SouthEast.getMovement(),testDame(i,board,Direction.SouthEast));
                allPossibilities.add(tempMove);
            }
            if(testDame(i,board,Direction.SouthWest) != 0){
                Move tempMove =new Move();
                tempMove.addMove(this,i,testDame(i,board,Direction.SouthWest)+Direction.SouthWest.getMovement(),testDame(i,board,Direction.SouthWest));
                allPossibilities.add(tempMove);
            }
            
        }
        return allPossibilities;
    }
    /**
     * this Method tests the possibility of a specific pawn to jump in a specific direction
     *
     * @author Nadine Huetter
     */
    public boolean jump(int i,Board board, Direction direction){


        if(this.isWhite()) {
            return board.getPiece(i + direction.getMovement()).getColor() == Color.Black && board.getPiece(i + 2 * direction.getMovement()) == Pieces.Empty;
        }else return board.getPiece(i + direction.getMovement()).getColor() == Color.White && board.getPiece(i + 2 * direction.getMovement()) == Pieces.Empty;
    }
    /**
     * this Method tests the possibility of a specific Dame to jump a piece in a specific direction,
     * returning the position of the jumped Stone
     *
     * @author Nadine Huetter
     */
    public int testDame(int i, Board board,Direction direction){

        int positionOfJumpedStone=0;
        int n =1;
        while (board.getPiece(i+n*direction.getMovement()) != Pieces.Boarder){
            if(this.isWhite()) {
                    if(board.getPiece(i+n*direction.getMovement()).getColor() == Color.Black && 
                            board.getPiece(i+(n+1)*direction.getMovement()) == Pieces.Empty ){
                        positionOfJumpedStone = i+n*direction.getMovement();
                        return positionOfJumpedStone; 
                    }
                   
            }else{
                if(board.getPiece(i+n*direction.getMovement()).getColor() == Color.White &&
                        board.getPiece(i+(n+1)*direction.getMovement()) == Pieces.Empty ){
                    positionOfJumpedStone = i+n*direction.getMovement();
                    return positionOfJumpedStone;

                }
            }
            n +=1; 
           
        }

        return positionOfJumpedStone;
    }

    /**
     * creates a List with all possible Moves
     * If the can jump gave results, there won't be new Moves addet
     *
     * @author Nadine Huetter
     */
    public List<Move> getPossibleMoves(Board board){

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
                            List<Move> tempMove = getJumpPossibilities(i*10+j,board);
                            for (Move move: tempMove
                                 ) {if(move.getNumberOfMoves() != 0){
                                possibleMoves.add(move);
                            }
                                
                            }
                            


                        }

                    } else {
                        if(currentPiece.getColor() == Color.White){
                           // black can't move white Pieces
                        }
                        else{ //jump movement of the Dame
                            List<Move> tempMove = getJumpPossibilities(i*10+j,board);
                            for (Move move: tempMove
                            ) {if(move.getNumberOfMoves() != 0){
                                possibleMoves.add(move);
                            }

                            }

                        }
                    }
                }
            }
        }

        /*
          test if there were Jumps possible otherwise make a list of all the possible moves not including Jumps
          if there were Jumps Possible Return the current list (only including the Possible Jumps
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
                            } else if (currentPiece == Pieces.PawnW) { //pawns can only jump direct neighbours
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

                            } else{ // movement of the Dame
                                int amountOfMovesNW = 0, amountOfMovesNE =0, amountOfMovesSW=0 ,amountOfMovesSE = 0;
                                Move tempMoveNW = new Move();
                                Move tempMoveNE = new Move();
                                Move tempMoveSW = new Move();
                                Move tempMoveSE = new Move();

                                while (board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNW.addMove(this,i * 10 + j +Direction.NorthWest.getMovement()*amountOfMovesNW,i * 10 + j + Direction.NorthWest.getMovement()+Direction.NorthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNW);
                                    amountOfMovesNW += 1;

                                }
                                while (board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNE.addMove(this,i * 10 + j +Direction.NorthEast.getMovement()*amountOfMovesNE,i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNE);
                                    amountOfMovesNE += 1;

                                }
                                while (board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSW.addMove(this,i * 10 + j +Direction.SouthWest.getMovement()*amountOfMovesSW,i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSW);
                                    amountOfMovesSW += 1;

                                }

                                while (board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSE.addMove(this,i * 10 + j +Direction.SouthEast.getMovement()*amountOfMovesSE,i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSE);
                                    amountOfMovesSE += 1;

                                }


                            }

                        } else {
                            if(currentPiece.getColor() == Color.White){
                                 // black can't move white Pieces
                            } else if (currentPiece == Pieces.PawnB) { //pawns can only jump direct neighbours
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
                                    amountOfMovesNW += 1;

                                }
                                while (board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveNE.addMove(this,i * 10 + j +Direction.NorthEast.getMovement()*amountOfMovesNE,i * 10 + j + Direction.NorthEast.getMovement()+Direction.NorthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveNE);
                                    amountOfMovesNE += 1;

                                }
                                while (board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSW.addMove(this,i * 10 + j +Direction.SouthWest.getMovement()*amountOfMovesSW,i * 10 + j + Direction.SouthWest.getMovement()+Direction.SouthWest.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSW);
                                    amountOfMovesSW += 1;

                                }
                                while (board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) == Pieces.Empty &&
                                        board.getPiece(i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW) != Pieces.Boarder){
                                    tempMoveSE.addMove(this,i * 10 + j +Direction.SouthEast.getMovement()*amountOfMovesSE,i * 10 + j + Direction.SouthEast.getMovement()+Direction.SouthEast.getMovement()*amountOfMovesNW,0);
                                    possibleMoves.add(tempMoveSE);
                                    amountOfMovesSE +=1;

                                }


                            }
                        }
                    }
                }
            }

        }


        return possibleMoves;
    }

    /**
     * Method to check for, and create a dame
     *
     * @author Nadine Huetter
     */
    public Board becomeDame (Board board){


        int startingField;
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
           // System.out.println(startingField);
            startingField += 2;

        }
        return board;
    }


    /**
     * Method to execute the move of the Player
     * @param board
     * @param opponent
     * @return
     * @author Nadine Huetter
     */
    public abstract Board move(Board board, Player opponent);

}
