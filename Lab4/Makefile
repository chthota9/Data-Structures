# Makefile
# Charishma Thota
# cthota

FLAGS   = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN  = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

.PHONY: valgrind

valgrind:
        valgrind --leak-check=full charType charType.c outfile

clean :
	rm -f $(EXEBIN) $(OBJECTS)