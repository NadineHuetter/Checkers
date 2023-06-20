import controler.Game;
import core.Pieces;
import model.Board;
//import model.ComPlayer;
import model.Player;
//import model.UserPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        /**
         * the main-funktion combines all other classes and gives the possebilety
         * to play several playthroughs without restarting the application
         *
         * @author Nadine Huetter
         */
        System.out.println(Pieces.DameB.getUnicode()+Pieces.DameW.getUnicode());
        Game game = chooseGamemode();
        boolean whiteWon= game.playGame();
        if (whiteWon){
            System.out.println("White won!");
        } else {
            System.out.println("Black won!");
        }


        /**
         * loop implementation
         * @author Nadine Huetter
         */
        // System.out.println("Do you want to play an other round?(y/n)");
       // String input = Readinput();
        //if (input == "y" || input =="Y"){

        //}

    }

    public static String Readinput(){
        /**
         * Reads the console input
         */
        BufferedReader keyboard =
                new BufferedReader( new InputStreamReader(System.in));
        try {
            return keyboard.readLine();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }

    public static Game chooseGamemode(){

        /**
         * choosing the gamemode by console input
         *
         * @author Nadine Huetter
         */
        // start game by choosing which mode you want to play
        System.out.println("choose game mode:");
        System.out.println("(0) Player vs. Player");
        System.out.println("(1) Player vs. Computer");
        System.out.println("(2) Computer vs. Computer");

        String input = Readinput();
        Board board = new Board();
        Game game;
        switch (input) {
            case "0":
                Player player10 =new Player(true); //new UserPlayer();
                Player player20 =new Player(false); //new UserPlayer();

                game= new Game(board,player10,player20);
                return game;
            case "1":
                Player player11 = new Player(true);//new UserPlayer();
                Player player21 = new Player(false);//new ComPlayer();

                game= new Game(board,player11,player21);
                return game;
            case"2":
                Player player12 = new Player(true);//new ComPlayer();
                Player player22 = new Player(false); //new ComPlayer();
                game= new Game(board,player12,player22);

                return game;
            default:
                System.out.println("That choice was Invalid.");
                return chooseGamemode();

        }

    }


}
