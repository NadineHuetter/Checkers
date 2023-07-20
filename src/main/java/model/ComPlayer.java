package model;

import core.Move;
import core.Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComPlayer extends Player {
    /**
     * class representing the Computer as a Player
     *
     */

    public ComPlayer(boolean white) {
        super(white);
    }


    @Override
    public Board move(Board board, Player opponent) {

        //Max

        //Min

        //Max


        return null;
    }

    public List<Map<Move,List<Map<Move, List<Move>>>>> buildTree(Board initialBoard){
        List<Map<Move,List<Map<Move, List<Move>>>>> finalTree = new ArrayList<>();
        ComPlayer opponent = new ComPlayer(!this.isWhite()); // to build the second Layer of the tree
        List<Move> firstLayer = getPossibleMoves(initialBoard);
        for (Move move1: firstLayer) {
            Board newBoard = new Board();
            newBoard = executeMove(initialBoard,move1,opponent);
            List<Move> secondLayer = opponent.getPossibleMoves(newBoard);
            List<Map<Move,List<Move>>> secondLayerFin = new ArrayList<>();
            for (Move move2: secondLayer) {
                Board newBoard2 = new Board();
                newBoard2 = opponent.executeMove(newBoard,move2,this);

                List<Move> thirdLayer = getPossibleMoves(newBoard2);
                secondLayerFin.add(Map.of(move2,  thirdLayer ));

            }
            finalTree.add(Map.of(move1,secondLayerFin));
        }

        return finalTree;
    }



    public Board executeMove (Board board, Move move, ComPlayer opponent){
        int numOfSteps = move.getNumberOfMoves() -1;
        int[] firstMovement = move.getMove(0);
        Pieces usedPiece = board.getPiece(firstMovement[1]);

        for (int i = 0 ; i <= numOfSteps; i++) {
            int[] tempMovement = move.getMove(i);

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
}
