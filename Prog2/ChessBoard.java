//------------------------------------------------
//Charishma Thota
//cthota
//Chessboard.java
// includes the main class where the it will the take first coordinate, makes a list of the chesspieces, finds any repeating
// piece, finds what piece is at the first coordinate, and checks if that piece attacks any piece on the board
// ----------------------------------------------

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.*;


public class ChessBoard{
    public static void main(String[] args) throws IOException{

        if(args.length < 2){
    	    System.out.println("Usage: java –jar NQueens.jar <input file> <output file>");
    	    System.exit(1);
    	}

        FileReader theFile = new FileReader(args[0]); // reads in first argument
        PrintWriter out = new PrintWriter(new FileWriter(args[1])); // prints out to the second argument
        BufferedReader reader = new BufferedReader(theFile); // run the program line by line
        String input; // for reading in input lines
        
        // to store where u split the input line
        String[] coordinate; 
        String[] thePieces;

        // intializes the variables to pass into the parameters where u create the list
        int bCol;
        int bRow;
        char sort = 'f';
        

        while ((input = reader.readLine() ) != null){ // as long there is stuff to be read

         if (input == null){ // when it reaches the end of the input 
            break;
        }

        // declare the lists
        LinkedList firstList = new LinkedList();
        LinkedList secondList = new LinkedList(); 

        String[] text = input.split(":"); // splits before :
        coordinate = text[0].split(" "); // spliting string to substring

        // initalizes the first two numbers in the input as row and col
        bCol = Integer.parseInt(coordinate[0]); 
        bRow = Integer.parseInt(coordinate[1]);

        thePieces = text[1].split(" "); // everything after 
        String str = "";
       
       // use a loop to store the coordinates and the piece in an array and also insert them into a list
        for (int i =1; i< thePieces.length; i=i+3){
            str = thePieces[i]; 
            sort = str.charAt(0); //stores the string as a char

            int col = Integer.parseInt(thePieces[i+1]);
            int row = Integer.parseInt(thePieces[i+2]);
        
            firstList.insert(sortInto(sort, col, row));
            secondList.insert(sortInto(sort, col, row));

        } 
        
        boolean checkRepeat = secondList.isRepeating(secondList); // returns true if any piece repeats itself

        if (checkRepeat == true){
            out.println("Invalid"); // if so print invalid 
        }

        else { 

        if (firstList.traverse(bCol,bRow) == null){ // finds the piece
            out.println("-"); // if you cant find the piece
        }
        else {
            // main portion where it prints out "y" or "n" if it does attack or does not
            ChessPiece l = firstList.traverse(bCol,bRow); // finds the piece and its place and stores it as a Chesspiece
            char cp = l.color; // gets the leter of the piece
            out.print(cp);
            Node holder = firstList.head; // sets the node to the first element of the list
       
            while (holder != null){ // as long as there is an element in the list
                if (l.isAttacking(holder.data)){ // checks if the pieces attack each other
                    boolean check = l.theSame(holder.data); // sees if its not checking the piece itself
                    if (check == false){   
                        if ((l.color != holder.data.color)){ // checks for opposite color
                                boolean piece1 = java.lang.Character.isUpperCase(l.color);
                                boolean piece2 = java.lang.Character.isUpperCase(holder.data.color);
                                // checks for opposite case (lower v upper)
                                if ((piece1 == true && piece2 == false) || (piece1 == false && piece2 == true)){ 
                                    out.println(" y"); // if attacking occurs
                                    break;
                                } 
                             }
                         }
                     }
                holder = holder.next; // moves the node
                if (holder == null){ // if you reach the end of the list
                    out.println(" n"); // if no attacking happens
                    }
                 }
            }
        }     
    }
        out.close(); // close file reader
        theFile.close(); // close output file
}

// depending on the piece and its type makes that certain piece
public static ChessPiece sortInto(char type, int col, int row){
    if ((type == 'k') || (type == 'K')){ //king
        return new King(col,row,type);
    }
    if ((type == 'q') || (type == 'Q')){ //queen
        return new Queen(col,row,type);
    }
    if ((type == 'r') || (type == 'R')){ // rook
        return new Rook(col,row,type);
    }
    if ((type == 'b') || (type == 'B')){// bishop
        return new Bishop(col,row,type);
    }
    if((type == 'n') || (type == 'N')){ // knight
        return new Knight(col,row,type);
    }
        return null;
}

}