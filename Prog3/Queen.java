//------------------------------------------------
//Charishma Thota
//cthota
//Queen.java
// checks if a queen attacks and for blocking
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
       public boolean isBlocked(int boardCol, int boardRow, LinkedList chessBoard){ // blocking for queen combined rook +bishop
        int tempCol = col; 
        int tempRow = row; 
        // for up and down at least rows or col has the be same
        if ((row == boardRow) && ((boardCol - col) > 0)){ // checks if row is the same and what quadrant the piece is at
            tempCol = tempCol +1;
            
            while(tempCol < boardCol){
                if(chessBoard.traverse(tempCol,row) != null){ //tries to find a piece until it reaches the new point
                    return true;
                }
                else{
                    tempCol++;
                }
            }
            return false;
        }
        if ((row == boardRow) && ((boardCol - col) < 0)){ // for other quadrant
            tempCol = tempCol -1;
            while(tempCol > boardCol){
                if(chessBoard.traverse(tempCol,row) != null){
                    return true;
                }
                else{
                    tempCol--;
                }
            }
            return false;
        }
        if ((col == boardCol) && ((boardRow - row) > 0)){ //check for same col and which quadrant
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
        if ((col == boardCol) && ((boardRow - row) < 0)){ // other quadrant
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
        // similar logic as above but for diagonals
        if (((boardRow - row)/(boardCol - col)) == 1 ){ // for diagonals so uses slope to check which quadrant 
            if ((tempRow < boardRow) && (tempCol < boardCol)){
                tempRow = tempRow +1;
                tempCol = tempCol +1;
            }
            while ((tempRow < boardRow) && (tempCol < boardCol)){
                if ((chessBoard.traverse(tempCol, tempRow)) != null){ //tries to find a piece until it reaches new point
                    System.out.println(72);
                    return true;
                }
                else{
                    tempRow++;
                    tempCol++;
                }
            }

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