package model;


import core.Move;
import core.Pieces;

import java.util.ArrayList;
import java.util.List;

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
        List<Move> listOfProbableMoves = buildTree(board);

        System.out.println("test");//ToDo

        //chooseMove & execute

        int amountOfBestMoves = listOfProbableMoves.size();
        if(amountOfBestMoves == 0){
            return board;
        }

        int chosenMoveIndex;
        Random random = new Random();
        chosenMoveIndex = random.nextInt(amountOfBestMoves);
        Move chosenMove = listOfProbableMoves.get(chosenMoveIndex);

        return executeMove(board,chosenMove,opponent.isWhite());
    }

    public List<Move> buildTree(Board initialBoard){
        ComPlayer opponent = new ComPlayer(!this.isWhite()); // to build the second Layer of the tree
        List<Move> firstLayer = getPossibleMoves(initialBoard);

        int max1 = -100;
        List<Move> listOfProbableMoves = new ArrayList<>();

        if(firstLayer.isEmpty()){
            return listOfProbableMoves;
        }

        for (Move move1: firstLayer) {
            System.out.println(move1.getMove() + "layer1");
            Board newBoard= executeMove(initialBoard,move1, opponent.isWhite());
            List<Move> secondLayer = opponent.getPossibleMoves(newBoard);


            if(secondLayer.isEmpty()){break;}
            int min2 = 100;
            for (Move move2: secondLayer) {
                System.out.println(move2.getMove()+ "layer2");
                Board newBoard2 = opponent.executeMove(newBoard,move2,this.isWhite());
                List<Move> thirdLayer = new ArrayList<>();
                thirdLayer = getPossibleMoves(newBoard2);
                int max3 = -100;
                if(thirdLayer.isEmpty()){break;}
                for (Move move3: thirdLayer) {
                    System.out.println(move3.getMove()+"layer3");
                    Board newBoard3 = executeMove(newBoard2,move3,opponent.isWhite());
                    int quantifier;
                    if(this.isWhite()){
                        quantifier = newBoard3.getWhitePieces()- newBoard3.getBlackPieces();
                    }else{quantifier= newBoard3.getBlackPieces()- newBoard3.getWhitePieces();}
                    if(quantifier>max3){
                        System.out.println(quantifier +"q");
                        max3=quantifier;

                    }

                }

                System.out.println(max3+" 3");

                if(max3<min2){
                    min2 = max3;
                }

            }


            if(min2==max1){
                listOfProbableMoves.add(move1);
            }else if(min2>max1) {
                listOfProbableMoves.clear();
                listOfProbableMoves.add(move1);
            }




        }


        return listOfProbableMoves;
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
