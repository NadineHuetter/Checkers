package core;

public enum  Pieces{
    DameB,
    DameW,
    PawnB,
    PawnW,
    Empty,
    Boarder;

    public String getUnicode(){
    switch(this){
        case DameB:
            return" \u265a ";
        case DameW:
            return" \u2654 ";
        case PawnB:
            return" \u26ab ";
        case PawnW:
            return" \u26aa ";

        case Boarder:
            return" \u2613 ";
        default:
            return "   ";

    }
    }
}
