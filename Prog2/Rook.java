//------------------------------------------------
//Charishma Thota
//cthota
//Rook.java
// checks if a rook attacks
// ----------------------------------------------
public class Rook extends ChessPiece {
    public Rook(int col, int row, char color){
        super(col,row, color);
    }
    // rook only moves up and down or side to side
    public boolean isAttacking(ChessPiece i){
        if(row == i.row || col == i.col) // checks if in the same row or col
            return true;
        else
            return false;
    }
}