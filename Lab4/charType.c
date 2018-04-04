/*
* Charishma Thota
* cthota
*  charType.c
*  Reads input file and fies the characters on each line of the input
*  file into the following categories:  
*  alphabetic characters (upper or lower case), numeric characters (digits 0-9),
*  punctuation, and white space (space, tab, or newline).
*  the output file.
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>

void extract_chars(char* s, char* a, char* d, char* p, char* w); 
int allWords = 256; /*global variable for total words in a line*/

int main(int argc, char* argv[]){
  FILE* in;  /* file for input*/  
  FILE* out; /* file for output */

  char* words; /*arrays for each line and the arrays for the specific characters*/
  char* alpha;
  char* numbers;
  char* punct;
  char* whiteSpace;

  int lineNum = 1; /*keeps track of lines for printing*/

   /* check number of arguments on the command line */
   if( argc<2 ){
      printf("Usage: %s in.txt out.txt \n", argv[0]);
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

    words = calloc(allWords +1,sizeof(char)); /* allocates memory for all arrays*/
    alpha = calloc(allWords +1,sizeof(char));
    numbers = calloc(allWords +1,sizeof(char));
    punct = calloc(allWords +1,sizeof(char));
    whiteSpace = calloc(allWords +1,sizeof(char));

    while( fgets(words, allWords, in) != NULL){ /* main while loop*/
        extract_chars(words, alpha, numbers, punct, whiteSpace);
        fprintf(out,"line %d contains:\n",lineNum);
            if(strlen(alpha)>1){ /* printing done here, accounts for if theres only one character */
            fprintf(out,"%d alphabetic characters: %s\n",(int)strlen(alpha),alpha);
                    }
            else{
            fprintf(out,"%d alphabetic character: %s\n",(int)strlen(alpha),alpha);
                    }
            if(strlen(numbers)>1){
            fprintf(out,"%d numeric characters: %s\n",(int)strlen(numbers),numbers);
                    }
            else{
            fprintf(out,"%d numeric character: %s\n",(int)strlen(numbers),numbers);
                    }
            if(strlen(punct)>1){
            fprintf(out,"%d punctuation characters: %s\n",(int)strlen(punct),punct);
                    }
            else{
            fprintf(out,"%d punctuation character: %s\n",(int)strlen(punct),punct);
                    }
            if(strlen(whiteSpace)>1){
            fprintf(out,"%d whitespace characters: %s\n",(int)strlen(whiteSpace),whiteSpace);
                    }
            else{
            fprintf(out,"%d whitespace character: %s\n",(int)strlen(whiteSpace),whiteSpace);
                    } 

        lineNum++;/*increments the line number*/
    }

    fprintf(out,"\n");
    /* frees all the memory used*/
  	free(words);
    free(alpha);
    free(numbers);
    free(punct);
    free(whiteSpace);

    words = NULL;
    alpha = NULL;
    numbers = NULL;
    punct = NULL;
    whiteSpace = NULL;
   /* close input and output files */
    fclose(in);
    fclose(out);
    return(EXIT_SUCCESS);
}
  
void extract_chars(char* s, char* a, char* d, char* p, char* w){
  int alphaC = 0; /* counters so the arrays get filled one by one */
  int digitC = 0;
  int punctC = 0;
  int whiteC = 0;
  
  for (int i = 0;  (i< allWords) && (s[i] != '\0'); i++){
    if (isalpha((int)s[i])){
      a[alphaC] = s[i];
      alphaC = alphaC + 1;
    }
  }
  for (int i = 0;  (i< allWords) && (s[i] != '\0'); i++){
      if (isdigit((int)s[i])){
      d[digitC] = s[i];
      digitC = digitC + 1;
    }
  }
  for (int i = 0;  (i< allWords) && (s[i] != '\0'); i++){
    if(ispunct((int)s[i])){
    	p[punctC] = s[i];
      punctC = punctC + 1;
    }
  }
  for (int i = 0;  (i< allWords) && (s[i] != '\0'); i++){
      if(isspace((int)s[i])){
      w[whiteC] = s[i];
      whiteC = whiteC + 1;
    }
  }
    a[alphaC]='\0'; /* null character at the end of each array */
    d[digitC]='\0';
    p[punctC]='\0';
    w[whiteC]='\0';
 }