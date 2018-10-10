//------------------------------------------------
//Charishma Thota
//cthota
//NQueens.java
// program that takes in a chessboard size and places the queens so they dont attack each other
// format : size , col, row

import java.lang.Math;
import java.util.*;
import java.io.*;
import java.util.Scanner;

class NQueens{
    public static void main(String[] args) throws IOException{
    // check number of command line arguments is at least 2
      if(args.length < 2){
            System.out.println("Usage: java –jar NQueens.jar <input file> <output file>");
            System.exit(1);
            }
    //open files
    FileReader in = new FileReader(args[0]);
    PrintWriter out = new PrintWriter( new FileWriter(args[1]));
    BufferedReader theFile = new BufferedReader(in);
    String input;
    
    while((input = theFile.readLine()) != null) {
        if(input == null) {
            break;
        }
    
    String [] data = input.split(" ");
    int [] theNum = new int[data.length];
    for( int i =0; i< data.length; i++){
        theNum[i] = Integer.parseInt(data[i]);
    }
    // the deminisions and first place of the queen
    int sizeOfBoard = theNum[0];
    int bCol = theNum[1];
    int bRow = theNum[2];

    int chessBoard[][] = new int[sizeOfBoard][sizeOfBoard];
    initilazeArray(chessBoard, sizeOfBoard); // sets the board to all 0's
    //places the first queen
    chessBoard[bCol-1][bRow-1] = 1;

    // prints the solution to the out.txt
    if (theQueens(sizeOfBoard,chessBoard,0,bCol-1, bRow-1)){ // calling recursive step passing through first queen
      for (int i = 0; i < sizeOfBoard; i++){
            for (int j = 0; j < sizeOfBoard; j++){
                if (chessBoard[i][j] == 1){
                    out.print((i+1) + " " + (j+1) + " "); // prints solutions and accounts for the indexes
                }
            }
      }
      out.println(); 
    }
    else {
        out.println("No Solution"); // if there is no solution 
    }
    }
    //close the files
    in.close();
    out.close();
    } // closed main loop here

// methods start here

    // initlize board to all 0s
    public static int[][] initilazeArray(int chessBoard[][], int sizeOfBoard){
        for(int i = 0; i < sizeOfBoard; i++) {
            for(int j = 0; j < sizeOfBoard; j++) {
                chessBoard[i][j] = 0;
                }
            }
            return chessBoard;
    }

      public static boolean isAttacking(int chessBoard[][], int size, int col, int row, int x, int y){
          chessBoard[x][y] = 1;
        //check rows and columns
        for(int i = 0; i < size; i++) {
            if(chessBoard[col][i] == 1 && i != row ){ // for col and also makes sure you aren't checking the first queen
                 return true;
            }
        }
        for(int i=0;i < size; i++) { 
            if(chessBoard[i][row] == 1 && i != col ) { // for row and also makes sure you aren't checking the first queen
                return true;
            }
        }
        // check diagonals using differences. if the difference of the points are equal they are on the same line
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                int diff1 = Math.abs(col - i); // take absolute value in case it is negative
                int diff2 = Math.abs(row - j);
                if( diff1 == diff2 ){ // this means they would have the same slope of 1 
                    if(chessBoard[i][j] == 1 && ((i != col) || (j != row))){ // makes sure you aren't checking the first queen
                    return true;
                    }
                }
            } 
        }
        return false;
      }

    public static boolean theQueens(int size, int chessBoard[][], int move, int x, int y) {
        if(move == size ){
            return true;
        }
            for(int i = 0; i < size; i++) {
                if(isAttacking(chessBoard,size,move,i,x,y)) { //checks if you can place a queen in the position of (move,i)
                    continue; // if isAttacking returns true continue to check the next position
                }
                chessBoard[move][i] = 1; // if returns false place a queen there
                if(theQueens(size,chessBoard, move + 1, x, y)) { //recursive call that increments the move so you can place a queen in the next column
                    return true;
                }
                // backtrack if theQueens return zero
                if(move ==  x && i == y){
                    chessBoard[move][i]= 1; // do not move the first queen
                }
                if(move != x && i != y) {
                    chessBoard[move][i] = 0;  //sets previous one to 0's to backtrack
                }
            }
        return false; 
        }
    }
