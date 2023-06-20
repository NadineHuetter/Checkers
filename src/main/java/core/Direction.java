package core;

public enum Direction {
    NorthWest,
    NorthEast,
    SouthWest,
    SouthEast;

    public int getMovement(){
        switch (this){
            case NorthEast:
                return -9;
            case NorthWest:
                return -11;
            case SouthEast:
                return 11;
            case SouthWest:
                return 9;
            default:
                return 0;
        }
    }
}
