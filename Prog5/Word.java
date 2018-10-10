// -------------------------------------------------
// Charishma Thota
// cthota
// Word.java
// information on word object
// -------------------------------------------------

import java.lang.Math;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Object;

public class Word implements Comparator<Word>, Comparable<Word>{
    String word;
    int frq;
    
    public Word(){

    }
    public Word(String word, int frq) { //constructor
        this.word = word;
        this.frq= frq; 
    }

    public int compare(Word x, Word y){
        return y.frq - x.frq;
    }

    public int compareTo(Word w){
        return (this.word).compareTo(w.word);
    }
} //end of class Word 

