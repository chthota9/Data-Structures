//------------------------------------------------
//Charishma Thota
//cthota
//Chessboard.java
// includes the main class where the it will the take the list of chesspieces,
// store into linked list
// check if moves are legal 
// if not prints out the illegal move
// ----------------------------------------------

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.*;

public class ChessMoves{
    public static void main(String[] args) throws IOException{

        if(args.length < 2){ // error checking if the arugments are less than 2 inputs
    	    System.out.println("Usage: java –jar ChessBoard.jar <input file> <output file>");
    	    System.exit(1);
        }

        FileReader theFile = new FileReader(args[0]); //to read for file reading and file writing
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        BufferedReader reader = new BufferedReader(theFile);
        String input; // to read in input lines
        
        String[] theMoves; // arrays to be used for moves
        String[] thePieces;
       
        int bCol; // to store the positions
        int bRow;

        char typeOfPiece = 'a';

        while ((input = reader.readLine()) != null){ //if there is input to be read
            LinkedList list = new LinkedList(); 
            
            String[] lineInput = input.split(":"); // splits after colon
            thePieces = lineInput[0].split(" ");
            String string = "";
        
            for (int i = 0; i< thePieces.length; i=i+3){ // uses input to make pieces
                string = thePieces[i];
                typeOfPiece = string.charAt(0);
                bCol = Integer.parseInt(thePieces[i+1]); //store them in arrays
                bRow = Integer.parseInt(thePieces[i+2]);
                list.insert(theType(typeOfPiece, bCol, bRow)); // to make the specfic type
            } 

            theMoves = lineInput[1].trim().split(" ");
            int[] moves = new int[theMoves.length];

            for (int j = 0; j < moves.length; j++){ // the array for sequence of moves
                moves[j] = Integer.parseInt(theMoves[j]);
            }
    
            int counter = 0; // to keep track of which move
            boolean valid = true; // to check if board is valid
            boolean play = true; // to keep track white is going first

            while(counter < (moves.length -1)){ // main program loop
                ChessPiece thePiece = list.traverse(moves[counter],moves[counter +1]);
                boolean check = pieceCheck(thePiece);
                if (check == false){ // if a piece even exists
                    //if not print out move + illegal
                    out.print(moves[counter] + " " + moves[counter+1] + " " +       
                    moves[counter +2] + " " + moves[counter +3] + " ");
                    out.println("illegal");
                     valid = false; //board is invalid
                     break;
                }
                
                if(play){ // checks if order is correct (white and then black)
                 boolean turn = orderCheck(thePiece);
                    if (turn == true){
                        //if not print out move + illegal
                        out.print(moves[counter] + " " + moves[counter+1] + " " +       
                        moves[counter +2] + " " + moves[counter +3] + " ");
                        out.println("illegal");
                         valid = false; //board is invalid
                         break;
                    }
                }
                else if (!play){ // same as above
                boolean turn2 = orderCheck(thePiece);
                    if (turn2 == false){
                        //print out move + illegal
                        out.print(moves[counter] + " " + moves[counter+1] + " " +       
                        moves[counter +2] + " " + moves[counter +3] + " ");
                        out.println("illegal");
                         valid = false;
                         break;
                    }
                }
                

                if (ability(list,thePiece,moves[counter +2],moves[counter+3])){ // return true if the piece can move to the point
                    if (list.traverse(moves[counter +2],moves[counter+3]) != null){ // checks if there is already a piece there
                        Node dum = list.head;
                        while(dum != null){
                            if (((dum.data.col) == (moves[counter+2])) && 
                            ((dum.data.row) == (moves[counter+3]))){
                              list.delete(dum.data); // delete the piece
                              break;
                            }
                            dum = dum.next;
                        }
                        Node place = list.head;
                        while(place != null){
                            if (((place.data.col) == (thePiece.col)) && ((place.data.row) == (thePiece.row))){
                                place.data.col = moves[counter +2]; // set the piece to the new coordinate
                                place.data.row = moves[counter +3];
                                break;
                            }
                            place = place.next;
                        }
                    }
            else if(list.traverse(moves[counter + 2], moves[counter +3]) == null) { // if there isnt a piece already there
               Node dummy = list.head;
                        while(dummy != null){
                            if (((dummy.data.col) == (thePiece.col)) && ((dummy.data.row) == (thePiece.row))){
                                dummy.data.col = moves[counter +2]; // set its new position
                                dummy.data.row = moves[counter +3];
                                break;
                            }
                            dummy = dummy.next;
            }
        }
                    // check if after each move, if our king is being attacked
                   
                        if(play){ // if order is white then black
                              Node hold = list.head;
                            while(hold != null){
                                if ((hold.data.color) == 'k'){ //to check for our king
                                    Node holder = list.head;
                                    while (holder != null ){
                                        boolean attack = holder.data.isAttacking(hold.data); // if attacks
                                        boolean item = java.lang.Character.isUpperCase(holder.data.color); // if same color
                                        if(attack && item &&  !(holder.data.isBlocked(hold.data.col,hold.data.row,list))){ // as well as not blocking
                                            out.print(moves[counter] + " " + moves[counter+1] + " " + moves[counter +2] + " " + moves[counter +3] + " ");
                                            out.println("illegal");
                                             valid = false;
                                             break;
                                        }
                                        holder = holder.next;
                                    }
                                    break;
                                }
                                hold = hold.next;
                            }
                            if (!valid){ // for validity of board, if so, break
                                break;
                            }  
                        }
                        else if (!play){ // same as above but for opposite color sequence
                            Node hold2 = list.head;
                            while(hold2 != null){
                                if ((hold2.data.color) == 'K'){
                                    Node holder2 = list.head;
                                    while (holder2 != null ){
                                        boolean attack = holder2.data.isAttacking(hold2.data);
                                        boolean item = java.lang.Character.isUpperCase(holder2.data.color);
                                        if (attack && !item && !(holder2.data.isBlocked(hold2.data.col,hold2.data.row,list))){ // if attacks, lower case, not blocking
                                            out.print(moves[counter] + " " + moves[counter+1] + " " + moves[counter +2] + " " + moves[counter +3] + " ");
                                            out.println("illegal");
                                             valid = false; // change validity 
                                             break;
                                        }
                                        holder2 = holder2.next;
                                    }
                                    break;
                                }
                                hold2 = hold2.next;
                            }
                            if (!valid){
                                break;
                            }
                        }
                }
                else{ // if it cant move to that location
                    out.print(moves[counter] + " " + moves[counter+1] + " " + moves[counter +2] + " " + moves[counter +3] + " ");
                    out.println("illegal");
                     valid = false;
                     break;
                }
                play = !play; //change the color boolean to keep track of the correct order
                counter = counter + 4; // increment move array
            }
            if (valid){
                out.println("legal");
            }
        }
        out.close(); // close file reader and file
        theFile.close();
    } // end of main

// methods

    public static boolean pieceCheck(ChessPiece item) { // to check for existing piece
         if(item == null) {
                    return false;
                }
                return true; 
    } 
    public static boolean orderCheck(ChessPiece piece){ // to check for correct order
         boolean upperCase = java.lang.Character.isUpperCase(piece.color);
         return upperCase;
    }

    public static boolean ability(LinkedList ll, ChessPiece p, int col, int row){ // to check if a piece can move
    // row and col: the coordinate you want to go to
    Node dum = ll.head;
    while (dum != null){ // goes through list
        if ((dum.data.col == col) && (dum.data.row == row)){ // if a piece exists
            boolean check1 = java.lang.Character.isUpperCase(p.color); // upper or lower case
            boolean check2 = java.lang.Character.isUpperCase(dum.data.color); 
            if ((check1 == true && check2 == false) || (check1 == false && check2 == true)){ // opposite types
                if (p.isAttacking(dum.data)){ // a piece can go there, if it attacks and nothing is blocking
                    if (p.isBlocked(col,row,ll)){
                        return false;
                    }
                    if(!(p.isBlocked(col,row,ll))){ // if nothing blocks good.
                        return true;
                    }
                }
                if(!(p.isAttacking(dum.data))) {
                    return false;
                }
            }
            return false;
        }
        dum = dum.next;
    }
    //if there is not a piece
    Bishop random2 = new Bishop(col,row,'b');
    if (p.isAttacking(random2)){ // if the piece can go there if it can attack that location
        if (p.isBlocked(col,row,ll)){ // and nothing blocks
            return false;
        }
        return true;
    }
    else {
        return false;
    }
} // end of ability

      public static ChessPiece theType(char type, int col, int row){ // returns the right type of piece
        if ((type == 'k') || (type == 'K')){
            return new King(col,row,type);
        }
        if ((type == 'q') || (type == 'Q')){
            return new Queen(col,row,type);
        }
        if ((type == 'r') || (type == 'R')){
            return new Rook(col,row,type);
        }
        if ((type == 'b') || (type == 'B')){
            return new Bishop(col,row,type);
        }
        if((type == 'n') || (type == 'N')){
            return new Knight(col,row,type);
        }
            return null;
    } // end of chooseType


} // end of class