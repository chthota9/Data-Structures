//------------------------------------------------
//Charishma Thota
//cthota
//Knight.java
// checks if a knight attacks
// ----------------------------------------------

public class Knight extends ChessPiece {
    public Knight(int col, int row, char color){
        super(col,row, color);
    }
    // checks every possible position a knight can move to
    public boolean isAttacking(ChessPiece i){
        if(row == i.row - 1 && col == i.col - 2)
            return true;
        else if(row == i.row + 1 && col == i.col - 2)
            return true;
        else if(row == i.row - 2 && col == i.col - 1)
            return true;
        else if(row == i.row + 2 && col == i.col - 1)
            return true;
        else if(row == i.row - 2 && col == i.col + 1)
            return true;
        else if(row == i.row + 2 && col == i.col + 1)
            return true;
        else if(row == i.row - 1 && col == i.col + 2)
            return true;
        else if(row == i.row + 1 && col ==  i.col + 2)
            return true;
        else
            return false;
        }
    }