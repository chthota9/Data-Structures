//------------------------------------------------
//Charishma Thota
//cthota
//Bishop.java
// checks if a bishop attacks
// ----------------------------------------------
public class Bishop extends ChessPiece {
    public Bishop(int col, int row, char color){
        super(col, row, color);
    }
    
        // checks using slope 
        // if on the same diagonal slope would be -1 or 1
        public boolean isAttacking(ChessPiece i){
        if (Math.abs(row-i.row) == Math.abs(col - i.col)) // in order for slope to be 1 or -1, the row or col would equal each other
            return true;
        else 
            return false;
    }
}
