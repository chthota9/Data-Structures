//-----------------------------------------------------------------------------
// Charishma Thota
// cthota
// FileReverse.java
// Reads a text file and reverses every word in that file and inputs it onto another text file
//-----------------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;
import java.util.*;

class FileReverse{
	public static void main(String[] args) throws IOException{

	

	// check number of command line arguments is at least 2
	if(args.length < 2){	
	System.out.println("Usage: java –jar FileTokens.jar <input file> <output file>");
        System.exit(1);
	}

	// open files
	Scanner in = new Scanner(new File(args[0]));
	PrintWriter out = new PrintWriter(new FileWriter(args[1]));

	// read lines from in, extract and print tokens from each line
	while( in.hasNext() ){
	String word = in.next();
	out.println(stringReverse(word));
	}
	// close files
	in.close();
	out.close();
}

	public static String stringReverse(String s){
	int letterCount = s.length() - 1; // counts the amount of letters in a word
	// create two arrays that will store current word and reversed word
	char[] wordReversed = new char[ letterCount + 1]; 
	char[] currentWord = s.toCharArray(); 
	for( int i = letterCount; i>=0; i--){	// loop around the char array to reverse it
		wordReversed[letterCount - i] = currentWord[i];
		}
	// convert char array to String to match return type
	String revWord = new String(wordReversed);
	return revWord;
	}
}
