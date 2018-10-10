//------------------------------------------------
//Charishma Thota
//cthota
//Queen.java
// checks if a queen attacks
// ----------------------------------------------
public class Queen extends ChessPiece{
    public Queen(int col, int row, char color) {
        super(col,row,color);
    }

     public boolean isAttacking(ChessPiece i) {
        if (row ==i.row || col == i.col) // checks if a piece is in the same row or column
            return true;
        else if (Math.abs(row-i.row) == Math.abs(col - i.col)) // checks if the piece is in the same diagnol
            return true;
        else 
            return false; // if the piece is not attacking
    }

}