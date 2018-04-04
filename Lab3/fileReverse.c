/*
* Charishma Thota
* cthota
*  fileReverse.c
*  Reads input file and prints each word on a separate line of
*  the output file.
*/
# include <stdio.h>
# include <stdlib.h>
# include <string.h>

void stringReverse(char* s);

int main(int argc, char* argv[]){
   FILE* in;  /* file handle for input */  
   FILE* out; /* file handle for output */
   char word[256]; /* char array to store words from input file */

   /* check command line for correct number of arguments */
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   /* open input file for reading */
   in = fopen(argv[1], "r");
   if( in==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   /* open output file for writing */
   out = fopen(argv[2], "w");
   if( out==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   /* read words from input file, print on separate lines to output file*/
   while( fscanf(in, " %s", word) != EOF) {
       stringReverse(word);
      fprintf(out, "%s\n", word);
   }

   /* close input and output files */
   fclose(in);
   fclose(out);

   return(EXIT_SUCCESS);
}
void stringReverse(char* s) {
    size_t stringLen= strlen(s)-1; // get the length of string
    size_t j = stringLen;
    char temp; // to swap the letters

    for(size_t i = 0; i < stringLen; i++){
        // swap here
        temp = s[j]; // use an array to get the letter
        s[j] = s[i];
        s[i] = temp;
        j--;

        if(j == (stringLen/2)){ // you only have to go through half of the characters in the word in order to reverse
            break; // so break out of the for loop after going through half
        }
        
    }
  
}
