package core;

/**
 * Enum to represent the Board Pieces and the Boarder
 * @author Nadine Huetter
 */
public enum  Pieces{
    DameB,
    DameW,
    PawnB,
    PawnW,
    Empty,
    Boarder;


    public Color getColor(){
        /**
         * Method to define the color of the Piece
         * @author Nadine Huetter
         */

        switch (this){
            case DameB:
            case PawnB:
                return Color.Black;

            case DameW:
            case PawnW:
                return Color.White;
            default:
                return Color.Empty;

        }

    }

    public Kind getKind(){
        /**
         * Method to define the Kind of the Piece
         * @author Nadine Huetter
         */
        switch (this){
            case DameB:
            case DameW:
                return Kind.Dame;

            case PawnB:
            case PawnW:
                return Kind.Pawn;
            default:
                return Kind.Empty;

        }
    }

    public String getUnicode(){

        /**
         * Method to define the Unicode of the Piece used to Print the Board into the console
         * @author Nadine Huetter
         */
        switch(this){
            case DameB:
                return" \u265a ";
            case DameW:
                return" \u2654 ";
            case PawnB:
                return" \u26aa ";
            case PawnW:
                return" \u26ab ";

            case Boarder:
                return" \u2613 ";
            default:
                return "   ";

        }
    }
}
