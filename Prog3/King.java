//------------------------------------------------
//Charishma Thota
//cthota
//King.java
// checks if a king attacks and blocking for king
// ----------------------------------------------

public class King extends ChessPiece {
    public King(int col, int row, char color){
        super(col, row, color);
    }
    // moves like queen but only one space at a time
    // so coordinates would only be one off from each other
    public boolean isAttacking(ChessPiece i) {
        if((row == i.row) && (Math.abs(col-i.col) == 1)){ 
            return true;
        }
        if((col == i.col) && (Math.abs(row-i.row) == 1)) {
            return true;
        }
        if(Math.abs(row-i.row)==1 && Math.abs(col-i.col)==1) {
            return true;
        }
        return false;
    }
     public boolean isBlocked(int boardCol, int boardRow, LinkedList chessBoard){ // for blocking
        return false;
    }
}
