//------------------------------------------------
//Charishma Thota
//cthota
//LinkedList.java
// includes the linkedlist methods: insert, traverse, isRepeating that get used in the main class
// ----------------------------------------------

public class LinkedList{
    Node head;

    public LinkedList(){
        head = null;
    }

    void insert(ChessPiece c){
        Node item = new Node(c); // creates a node
        item.next = head; // item.next points to head
        head = item; //  head will equal item 
    }

    ChessPiece traverse(int col, int row){
        Node current = head; 
        while (current != null){ // until the end of the list
        // finds the pieces matching coordinates in the list
            if ((current.data.col == col) && (current.data.row == row)){ 
                return current.data; // if so return
            }
            current = current.next; // if not move on to the next one
        }
        return null; // if you dont find anything
    }

 boolean isRepeating(LinkedList w){
        Node curr = w.head; // points to w.head (need this to change the head after you check one)
        Node curr2 = w.head;// need to this move across the elements
 
        while(w.head.next!= null){ // until you reach the end of the list
        
            while (curr2.next != null){ // as long as the next element exists
           
                if (w.head.data.theSame(curr2.next.data)){ // checks if two pieces have the same data
                    return true; // if so return true
                 }
                 curr2 = curr2.next; // if not move to the next element
            }
            w.head = curr.next; // if there was no matching data with the current head set the next element as head
           curr2 = w.head;// therefore change curr2 to equal the new head 
           curr = curr2; // change the pointer to shift over 1
        
        }
        return false;
    }
}






  