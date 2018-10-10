//------------------------------------------------
//Charishma Thota
//cthota
//Bishop.java
// checks if a bishop attacks and for blocking
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

    public boolean isBlocked(int boardCol, int boardRow, LinkedList chessBoard){ //blocking for bishop
        int tempCol = col;
        int tempRow = row;
        // checks for which quadrant the piece is at depending on slope
        if (((boardRow - row)/(boardCol - col)) == 1 ){
            if ((tempRow < boardRow) && (tempCol < boardCol)){
                tempRow = tempRow +1; // so it doesnt check with itself
                tempCol = tempCol +1;
            }
            while ((tempRow < boardRow) && (tempCol < boardCol)){
                if ((chessBoard.traverse(tempCol, tempRow)) != null){ // finds a piece until it reaches the point
                    return true;
                }
                else{
                    tempRow++;
                    tempCol++;
                }
            }
            // same logic for the rest (as the one above) but different slopes and quadrants
            if((tempRow > boardRow) && (tempCol > boardCol)){
                tempRow = tempRow -1;
                tempCol = tempCol -1;
            }
            while ((tempRow > boardRow) && (tempCol > boardCol)){
                if ((chessBoard.traverse(tempCol, tempRow)) != null){
                    return true;
                }
                else{
                    tempRow--;
                    tempCol--;
                }
            }
            return false;
        }

        if (((boardRow - row)/(boardCol - col)) == -1 ){
            if((tempRow > boardRow) && (tempCol < boardCol)){
                tempRow = tempRow -1;
                tempCol = tempCol +1;
            }
            while ((tempRow > boardRow) && (tempCol < boardCol)){
                if ((chessBoard.traverse(tempCol, tempRow)) != null){
                    return true;
                }
                else{
                    tempRow--;
                    tempCol++;
                }
            }
            if((tempRow < boardRow) && (tempCol > boardCol)){
                tempRow = tempRow +1;
                tempCol = tempCol - 1;
            }
            while ((tempRow < boardRow) && (tempCol > boardCol)){

                if ((chessBoard.traverse(tempCol, tempRow)) != null){
                    return true;
                }
                else{
                    tempRow++;
                    tempCol--;
                }
            }
            return false;
        }

        return false;
    }
}
