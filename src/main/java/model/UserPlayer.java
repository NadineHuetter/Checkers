package model;

import controler.Reader;





public class UserPlayer extends Player{

    /**
     * Class representing the User
     *
     */

    public UserPlayer(boolean white) {
        super(white);
    }


    @Override
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

