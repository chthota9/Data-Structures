// -------------------------------------------------
// Charishma Thota
// cthota
// Bard.java
// program that reads in shakespeare.txt and creates a hash table that holds (key,value) data of a unique word
// will return the unique word depending on the length and frequency called from the input
// -------------------------------------------------

import java.lang.Math;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Object;

class Bard{
    public static void main(String [] args) throws IOException {
        // check if command line arguements is exactly 2 
        if(args.length < 2){
            System.out.println("Usage: java –jar NQueens.jar <input file> <output file>");
            System.exit(1);
        }
        //open the text file and the input file
        FileReader in  = new FileReader("shakespeare.txt");
        FileReader thisInput = new FileReader(args[0]);
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        BufferedReader textFile = new BufferedReader(in);
        BufferedReader inputFile = new BufferedReader(thisInput);
        String read;
        Hashtable<String, Integer> myHash = new Hashtable<String,Integer>(); // declare hash table that holds (key,value).
        int maxLength = 1; // used to get the max word to later use to make array lists
        while((read = textFile.readLine()) != null) {
            // convert all punctation to whitespace
            read = read.replaceAll("\\?", " ");
            read = read.replaceAll("\\!", " ");
            read = read.replaceAll("\\,", " ");
            read = read.replaceAll("\\.", " ");
            read = read.replaceAll("\\:", " ");
            read = read.replaceAll("\\;", " ");
            read = read.replaceAll("\\[", " ");
            read = read.replaceAll("\\]", " "); 
            read=read.toLowerCase(); // converts the words to lowercase
            String [] allWords = read.trim().split("\\s+"); // splits by whitespace and gets rid of the space surrounding the word
            for(int i = 0; i< allWords.length; i++){
                String getWord = allWords[i];
                if (getWord.length() > maxLength){ // if its greater than current mac word
                    maxLength = getWord.length(); // it becomes the new max word
                }
                if (myHash.containsKey(getWord)){ // if you find word
                    myHash.put(getWord,myHash.get(getWord)+1); // increment the frequency
                }
                if(!myHash.containsKey(getWord)){ // if you dont find the word
                    myHash.put(getWord,1); // put the word in the hashtable and set frequency to 1
                }
            }
        } // end of while loop
        Set<String> mySet = myHash.keySet(); // make set from hashtable to iterate through it
        ArrayList<ArrayList<Word>> myArrayList = new ArrayList<ArrayList<Word>>(); // arraylist of arraylist<Word> to sort the words in text file
       for (int i = 0; i < maxLength; i++ ){ // depending on max length
           ArrayList<Word> randomList = new ArrayList<Word>(); // make that number of array lists which correspond to the length of the word
            myArrayList.add(randomList); // add to arraylist
        }
         for (String keys : mySet){ // iterate through set
            int theLengthOfKey = keys.length(); // length of the word to account for 0 letter words
            Word thisWord = new Word(keys,myHash.get(keys)); // make a Word object out its word and frequency
            if ((theLengthOfKey -1) >= 0){ // account for 0 letter words
                myArrayList.get(theLengthOfKey-1).add(thisWord); // add the word to the corresponding arraylist by getting to that specfic index
            }
        } 
        for (int i = 0; i < maxLength; i ++){
            Collections.sort(myArrayList.get(i)); // sort lexiographically 
            Collections.sort(myArrayList.get(i), new Word()); // sort frequency
        }
         String hisText; // to hold his input
         int frequency = 0;
         int indexOfArray = 0;
         while ((hisText = inputFile.readLine()) != null){ // as long as there is more input
            String[] hisInts = hisText.split(" "); // split by whitespace
            indexOfArray = Integer.parseInt(hisInts[0]); // length
            frequency = Integer.parseInt(hisInts[1]); // rank 
            // if the index is more than the max length of a word
            // checks if the the frequency is more than the size or else it would be array out of bounds
             if ( (indexOfArray > (maxLength)) || (myArrayList.get(indexOfArray -1).size() <= frequency) ){ 
                 out.println("-");
             }
             else {
            out.println(myArrayList.get(indexOfArray -1).get(frequency).word); // print out the word
             }
         }
         //close the files
        in.close();
        thisInput.close();
        out.close();
    } // closes main method
} // closes main class