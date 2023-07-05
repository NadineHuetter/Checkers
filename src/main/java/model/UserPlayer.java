package model;

import controler.Reader;
import core.Move;
import core.Pieces;


import java.util.List;

public class UserPlayer extends Player{

    /**
     * Class representing the User
     * @param white
     */

    public UserPlayer(boolean white) {
        super(white);
    }

    @Override
    public Board move(Board board, Player opponent) {  //TODO:change board to pointer
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
                opponent.setAmountOfPieces(opponent.getAmountOfPieces()-1);
            }

        }

        board = this.becomeDame(board);

        return board;
    }

    public Integer chooseMove(int numOfMoves){

        int choosenMoveNum = -1;
        String choosenMove = "";
        choosenMove = Reader.Readinput();
        choosenMoveNum = Integer.parseInt(choosenMove);
        if(choosenMoveNum < 0 || choosenMoveNum >= numOfMoves){
            System.out.println("invalid choice please choose again");
            choosenMoveNum =chooseMove(numOfMoves);
        }
        return choosenMoveNum;
    }


    }
