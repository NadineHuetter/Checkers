package model;

import controler.Reader;
import core.Move;
import core.Pieces;

import java.util.List;


public class UserPlayer extends Player{

    /**
     * Class representing the User
     *
     */

    public UserPlayer(boolean white) {
        super(white);
    }

    @Override
    public Board move(Board board, Player opponent) {


            List<Move> possibleMoves = getPossibleMoves(board);
            int numOfMoves = possibleMoves.size();
            for (int i = 0; i < numOfMoves; i++) {
                System.out.println("("+i+")"+possibleMoves.get(i).getMove());
            }
            int chosenMoveNum=chooseMove(numOfMoves);

            Move chosenMove = possibleMoves.get(chosenMoveNum);
            int numOfSteps = chosenMove.getNumberOfMoves() -1;
            int[] firstMovement = chosenMove.getMove(0);
            Pieces usedPiece = board.getPiece(firstMovement[1]);

            for (int i = 0 ; i <= numOfSteps; i++) {
                int[] tempMovement = chosenMove.getMove(i);

                board.setPiece(tempMovement[1], Pieces.Empty);
                board.setPiece(tempMovement[2],usedPiece);
                if(tempMovement[3] != 0){
                    board.setPiece(tempMovement[3],Pieces.Empty);
                    if(opponent.isWhite()){
                        board.setWhitePieces(board.getWhitePieces()-1);
                    }else{
                        board.setBlackPieces(board.getBlackPieces()-1);
                    }

                }

            }

            board = this.becomeDame(board);

            return board;
    }

    public Integer chooseMove(int numOfMoves){

        int chosenMoveNum;
        String chosenMove ;
        chosenMove = Reader.ReadInput();
        chosenMoveNum = Integer.parseInt(chosenMove);
        if(chosenMoveNum < 0 || chosenMoveNum >= numOfMoves){
            System.out.println("invalid choice please choose again");
            chosenMoveNum =chooseMove(numOfMoves);
        }
        return chosenMoveNum;
    }


    }

