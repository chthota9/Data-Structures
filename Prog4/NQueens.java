//------------------------------------------------
//Charishma Thota
//cthota
//NQueens.java
// program that takes in a chessboard size and places the queens so they dont attack each other using stacks
// format : size , col, row....
//------------------------------------------------
import java.lang.Math;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class NQueens {
    public static void main(String [] args) throws IOException {
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
    String [] data = input.split(" "); // splits the test input
    ArrayList<Queen> theQueens = new ArrayList<>(); // to hold the queens prof inputs
    for( int i =1; i< data.length; i=i+2){
        int bCol = Integer.parseInt(data[i]); //gets col value
        int bRow = Integer.parseInt(data[i+1]); // get row value
        Queen piece = sortInto(bCol -1,bRow -1); // makes it into a Queen object
        theQueens.add(piece); // adds into the arraylist
    }
int sizeOfBoard = Integer.parseInt(data[0]); // deminisions of the board

//create stack
Stack<Queen> boardStack = new Stack<Queen> ();


        if (!firstCheck(theQueens)){ // if the queens prof gives us are invalid print out no solution
            out.println("No solution");
        }
        else if(firstCheck(theQueens)){ //if valid, continue placing queens
            int move = 0; // like col
            // for backtracking purposes
            int prevRow = 0; 
            boolean popped = false;
            boolean board = true;
            boolean placedQueen = false; // to know when to increment move
            boolean found = false; // board validity 
            while(move < sizeOfBoard) {
                found = false;
                for(int i = 0; i< theQueens.size(); i++){
                    if(move == theQueens.get(i).col){ // checks if there prof's queens are in that col
                        move++; // if so skip, and go to next col
                        found = true; 
                        break;
                    }
                }
                if(found){
                    continue; // continues with the incremented move
                }
                if(popped == false){ // if not popped start checking from row 0
                    prevRow = 0;
                }
                for(int i = prevRow; i<sizeOfBoard; i++){ // if no queen is placed in that col
                    placedQueen = false; // did not place a queen in that col
                    popped = false; // did not pop in that col
                    Queen random = new Queen(move,i); // makes random queen to check for attacking
                    if(checkQueens(random,boardStack,theQueens)){ // returns true if not attacking
                          boardStack.push(random); //push onto stack
                          placedQueen = true;
                          break;
                    }
                    else {
                        continue; // continue until you a valid location
                    }
                }
                if(placedQueen){ // if you placed a queen in that col, increment
                    move++;
                }
                else { // backtrack to previous col
                    boolean check = boardStack.empty();
                    if(check) { // if stack is empty it is not a valid board
                        board = false;
                        break;
                    }
                        Queen random2 = boardStack.pop(); // store the popped queens value in a random queen
                        move = random2.col; // get the col value
                        prevRow = random2.row + 1; // get the row value plus one to increment to the row you want to check
                        if(prevRow == sizeOfBoard) { // if you checked all possible locations in that col
                            if(boardStack.empty()){ // if you are at 0, there is nothing to pop so stack will be empty
                                board = false;
                                break;
                            }
                            else {
                                Queen random3 = boardStack.pop(); // if there is more to pop, store values again in random queen
                                move = random3.col;
                                prevRow = random3.row + 1;
                            }
                        }
                        popped = true; // so you dont set prevRow to 0 anymroe
                    }
               } // ends while loop

               
             
             if(board){ // if found solution
                   int stackCount = 0; // stackCount + arrayCount has to make up sizeOfBoard
                   int arrayCount = 0;
                   boolean emptyStack = false;
                   //prints out the solution in order from the stack and arraylist
                   for(int i =0; i< sizeOfBoard; i++){ //if prof already inputs a full board there wont be anything in the stack
                        if(boardStack.empty()){ 
                            emptyStack = true;
                        }
                       if((emptyStack == false) && i == boardStack.get(stackCount).col){
                           out.print((boardStack.get(stackCount).col + 1) + " " + (boardStack.get(stackCount).row + 1) + " ");
                           if (boardStack.size() - 1 == stackCount) {
                               continue;
                           }
                           else{
                               stackCount++;
                           }
                           
                       }
                       else{
                           for(int find = 0; find < theQueens.size(); find++){
                               if(theQueens.get(find).col == i) {
                                   out.print((theQueens.get(find).col + 1) + " " + (theQueens.get(find).row + 1) + " "); // find the queen's col you want in the list
                               }
                           }
                           if(theQueens.size() - 1 == arrayCount) {
                               continue;
                           }
                           else {
                               arrayCount++;
                           }
                       }
             }
             out.println();
             }
             // if solution was not found
               else{
                   out.print("No solution");
                   out.println();
               } 
            } //closes else if
        } // closes while loop
    in.close(); //closes files
    out.close();
} // closes main method

// methods start here

public static boolean firstCheck(ArrayList<Queen> theseQueens){ //checks if the queens given to us are valid
    int counter = 0;
    for(int i = 0; i < theseQueens.size(); i++) {
        for(int j = 0; j <theseQueens.size(); j++){
           if(theseQueens.get(i) == theseQueens.get(j)){
               continue;
           }
            else if(theseQueens.get(i).isAttacking(theseQueens.get(j))){ // checks for all possible queen combinations in the input given to us
            return false;
            }
        }
    }
return true;
}

public static boolean checkQueens(Queen q, Stack<Queen> theseStack, ArrayList<Queen> theseQueens){ // checks if a queen is attacking a placed queen 
    boolean attacking = true;
    boolean attacking2 = true;
    for(int i =0; i< theseStack.size(); i++) {
        if (q.isAttacking(theseStack.get(i))){ // uses isAttacking from queen class
            attacking = false;
        }
    }
    for(int i = 0; i<theseQueens.size(); i++){
        if(q.isAttacking(theseQueens.get(i))){
            attacking2 = false;
        }
    }
    if(attacking == false || attacking2 == false){
        return false;
    }
return true;
}

public static Queen sortInto(int col, int row){ // makes a Queen object
    return new Queen(col,row);
    }
} // closed main class