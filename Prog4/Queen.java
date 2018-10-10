//------------------------------------------------
//Charishma Thota
//cthota
//Queen.java
// contains the class for object Queen
// col and row make up a queen object
// ----------------------------------------------

import java.lang.Math;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Queen {
    public int col;
    public int row;

//constructor
public Queen(int col, int row){
    this.col = col;
    this.row = row;
}

public boolean isAttacking(Queen i) {
    if (row ==i.row || col == i.col) // checks if a queen is in the same row or column
        return true;
    else if (Math.abs(row-i.row) == Math.abs(col - i.col)) // checks if the queen is in the same diagnol
        return true;
    else
    return false; // if the piece is not attacking
    }
}



