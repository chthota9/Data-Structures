#------------------------------------------------------
# Makefile
# Charishma Thota
# cthota
#------------------------------------------------------
JAVASRC	 = ChessBoard.java Knight.java Bishop.java Rook.java King.java Queen.java ChessPiece.java Node.java LinkedList.java
SOURCES	= README Makefile $(JAVASRC)
MAINCLASS	= ChessBoard
CLASSES	= ChessBoard.class Knight.class Bishop.class Rook.class King.class Queen.class ChessPiece.class Node.class LinkedList.class
JARFILE	= ChessBoard.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
