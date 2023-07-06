import controler.Game;
import controler.Reader;
import core.Pieces;
import model.Board;
import model.ComPlayer;
import model.Player;
import model.UserPlayer;


public class Main {
    /**
    * the main-funktion combines all other classes and gives the possibility
     * to play several playthroughs without restarting the application
     *
    * @author Nadine Huetter
    */
    public static void main(String[] args){

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
       // String input = Reader.ReadInput();
        //if (input == "y" || input =="Y"){

        //}

    }

    /**
     * choosing the game mode by console input
     *
     * @author Nadine Huetter
     */
    public static Game chooseGamemode(){


        // start game by choosing which mode you want to play
        System.out.println("choose game mode:");
        System.out.println("(0) Player vs. Player");
        System.out.println("(1) Player vs. Computer");
        System.out.println("(2) Computer vs. Computer");

        String input = Reader.ReadInput();
        Board board = new Board();
        Player player1;
        Player player2;
        Game game;
        switch (input) {
            case "0":
                player1 =new UserPlayer(true);
                player2 =new UserPlayer(false);

                game= new Game(board,player1,player2);
                return game;
            case "1":
                player1 =new UserPlayer(true);
                player2 =new ComPlayer(false);

                game= new Game(board,player1,player2);
                return game;
            case"2":
                player1 =new ComPlayer(true);
                player2 =new ComPlayer(false);
                game= new Game(board,player1,player2);

                return game;
            default:
                System.out.println("That choice was Invalid.");
                return chooseGamemode();

        }

    }


}
