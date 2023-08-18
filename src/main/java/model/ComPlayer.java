package model;

import core.Color;
import core.Move;
import core.Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        List<Map<Move, List<Map<Move, List<Map<Move, Board>>>>>> tree = buildTree(board);


        List<Move> listOfProbableMoves = new ArrayList<>();
        int max1 = -200;

        for (Map<Move, List<Map<Move, List<Map<Move, Board>>>>> map1: tree) { //First Layer (Max)
            for (Move move1: map1.keySet()) {

                List<Map<Move, List<Map<Move, Board>>>> Layer2 = map1.get(move1);

                int min2 = 600;

                for (Map<Move, List<Map<Move, Board>>> map2 : Layer2) { //Second Layer (Min)
                    for (Move move2: map2.keySet()) {
                        int max3 =-200;
                        List<Map<Move, Board>> Layer3 =map2.get(move2);

                        for (Map<Move, Board> map3: Layer3) { //Third Layer (Max)
                            for (Move move3: map3.keySet()) {
                                Board currentBoard = map3.get(move3);
                                int quantifier;
                                if(this.isWhite()){
                                    quantifier = currentBoard.getWhitePieces()- currentBoard.getBlackPieces();
                                }else{quantifier= currentBoard.getBlackPieces()- currentBoard.getWhitePieces();}
                                if(quantifier>max3){
                                    max3=quantifier;

                                }
                            }
                        }

                        if(max3<min2){
                            min2 = max3;
                        }
                    }
                    
                }

                if(min2>max1){
                    listOfProbableMoves.clear();
                    listOfProbableMoves.add(move1);
                } else if (min2 == max1) {
                    listOfProbableMoves.add(move1);
                }
            }
        }


        //chooseMove & execute
        Move chosenMove = new Move();
        int amountOfBestMoves = listOfProbableMoves.size();
        int chosenMoveIndex;
        Random random = new Random();
        chosenMoveIndex = random.nextInt(amountOfBestMoves);
        chosenMove = listOfProbableMoves.get(chosenMoveIndex);

        Board newBoard= executeMove(board,chosenMove,opponent.isWhite());

        return newBoard;
    }

    public List<Map<Move,List<Map<Move, List<Map<Move,Board>>>>>> buildTree(Board initialBoard){
        List<Map<Move,List<Map<Move, List<Map<Move,Board>>>>>> finalTree = new ArrayList<>();
        ComPlayer opponent = new ComPlayer(!this.isWhite()); // to build the second Layer of the tree
        List<Move> firstLayer = getPossibleMoves(initialBoard);
        for (Move move1: firstLayer) {
            Board newBoard = new Board();
            newBoard = executeMove(initialBoard,move1, opponent.isWhite());
            List<Move> secondLayer = opponent.getPossibleMoves(newBoard);
            List<Map<Move,List<Map<Move,Board>>>> secondLayerFin = new ArrayList<>();
            System.out.println(secondLayer.size() + "   test 2");
            for (Move move2: secondLayer) {
                Board newBoard2 = new Board();
                newBoard2 = opponent.executeMove(newBoard,move2,this.isWhite());

                List<Move> thirdLayer = getPossibleMoves(newBoard2);
                List<Map<Move,Board>> thirdLayerFin = new ArrayList<>();
                System.out.println("test3");
                for (Move move3: thirdLayer) {
                    Board newBoard3 = executeMove(newBoard2,move3,opponent.isWhite());
                    thirdLayerFin.add(Map.of(move3,newBoard3));
                    System.out.println("test7");


                }
                secondLayerFin.add(Map.of(move2,thirdLayerFin ));
                System.out.println("test4");

            }
            finalTree.add(Map.of(move1,secondLayerFin));
            System.out.println(finalTree);
        }

        return finalTree;
    }



    public Board executeMove (Board board, Move move, boolean opponentIsWhite){
        int numOfSteps = move.getNumberOfMoves() -1;
        int[] firstMovement = move.getMove(0);
        Pieces usedPiece = board.getPiece(firstMovement[1]);

        for (int i = 0 ; i <= numOfSteps; i++) {
            int[] tempMovement = move.getMove(i);

            board.setPiece(tempMovement[1], Pieces.Empty);
            board.setPiece(tempMovement[2],usedPiece);
            if(tempMovement[3] != 0){
                board.setPiece(tempMovement[3],Pieces.Empty);
                if(opponentIsWhite){
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
