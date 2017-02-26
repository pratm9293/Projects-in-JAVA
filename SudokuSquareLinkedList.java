
public class SudokuSquareLinkedList {
    private SudokuSquareNode head;
    SudokuSquareNode squareToReturn;

    public SudokuSquareLinkedList() {
        this.head = null;
        squareToReturn = null;
    }

    public SudokuSquareLinkedList(SudokuSquareNode head) {
        this.head = head;
        squareToReturn = head;
    }

    public void append(SudokuSquareNode end) {
        if(head == null) {
            head = end;
            squareToReturn = head;
            return;
        }
        SudokuSquareNode current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(end);
    }

    public SudokuSquareNode getNext() {
        SudokuSquareNode aSudokuNode = squareToReturn;
        if(squareToReturn != null) {
            squareToReturn = squareToReturn.getNext();
        }
        return aSudokuNode;
    }
}
