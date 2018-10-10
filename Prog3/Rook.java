//------------------------------------------------
//Charishma Thota
//cthota
//Rook.java
// checks if a rook attacks and for blocking
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
      public boolean isBlocked(int boardCol, int boardRow, LinkedList chessBoard){ // blocking for rook ,checks for up and down
        int tempCol = col;
        int tempRow = row;
        // for rook at least row or col has to be same
        if ((row == boardRow) && ((boardCol - col) > 0)){ //checks for same row and which quadrant
            tempCol = tempCol +1;
            
            while(tempCol < boardCol){
                if(chessBoard.traverse(tempCol,row) != null){ // tries to find a piece until it reaches new point
                    return true;
                }
                else{
                    tempCol++;
                }
            }
            return false;
        }
        if ((row == boardRow) && ((boardCol - col) < 0)){ // other quadrant
            tempCol = tempCol -1;

            while(tempCol > boardCol){
                if((chessBoard.traverse(tempCol,row)) != null){
                    return true;
                }
                else{
                    tempCol--;
                }
            }
            return false;
        }
        if ((col == boardCol) && ((boardRow - row) > 0)){ // same col and checks for which quadrant
            tempRow = tempRow +1;
            while(tempRow < boardRow){

                if(chessBoard.traverse(col,tempRow) != null){
                    return true;
                }
                else{
                    tempRow++;
                }
            }
            return false;
        }
        if ((col == boardCol) && ((boardRow - row) < 0)){ //other quadrant
            tempRow = tempRow -1;
            while(tempRow > boardRow){
                if(chessBoard.traverse(col,tempRow) != null){
                    return true;
                }
                else{
                    tempRow--;
                }
            }
            return false;
        }
        return false;
    }
}