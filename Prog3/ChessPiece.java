//------------------------------------------------
//Charishma Thota
//cthota
//ChessPiece.java
// contains the superclass for ChessPiece. 
// ----------------------------------------------
public class ChessPiece {
    public int col;
    public int row;
    public char color;

//constructor
    public ChessPiece(int col, int row, char color){
        this.col = col;
        this.row = row;
        this.color = color;
    }
    public boolean theSame(ChessPiece i) { //checks for the same data 
        if(col == i.col && row == i.row) {
            return true;
        }
        return false;
    }
    public boolean isAttacking(ChessPiece i){ //for attacking
        return true;
    }
     public boolean isBlocked(int col, int row, LinkedList ll){ //for blocking
        return false;
    }
}

