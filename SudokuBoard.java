
public class SudokuBoard {
    private SudokuSquare myBoard[][];
    public final int boardSize;

    public SudokuBoard(SudokuSquareLinkedList aList) {
        boardSize = 4;
        myBoard = new SudokuSquare[boardSize][boardSize];
        for(int r = 0; r < boardSize; ++r) {
            for(int c = 0; c < boardSize; ++c) {
                myBoard[r][c] = new SudokuSquare(r, c, 0, false);
            }
        }
        SudokuSquareNode aNode = aList.getNext();
        while(aNode != null) {
            SudokuSquare aSquare = aNode.getData();
            int r, c, value;
            r = aSquare.getRow();
            c = aSquare.getColumn();
            value = aSquare.getValue();
            myBoard[r][c] = new SudokuSquare(r, c, value, true);
            aNode = aList.getNext();
        }
    }

    public void reset() {
        for(int i = 0; i < boardSize; ++i) {
            for(int j = 0; j < boardSize; ++j) {
                if(! myBoard[i][j].isLocked()) {
                    myBoard[i][j] = new SudokuSquare(i, j, 0, false);
                }
            }
        }
    }

    public boolean isFull() {
        for(int i = 0; i < boardSize; ++i) {
            for(int j = 0; j < boardSize; ++j) {
                if(myBoard[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public SudokuSquare getSquare(int row, int column) {
        return myBoard[row][column];
    }

    public void enterMove(int row, int col, int value) throws SudokuException {
        if(! isValid(row, col, value)) { throw new SudokuException("Invalid Move"); }
        myBoard[row][col] = new SudokuSquare(row, col, value, false);
    }

    public boolean isValid(int row, int column, int value) {
        if(value < 0 || value > 4) { return false; }
        if(row < 0 || row > boardSize) { return false; }
        if(column < 0 || column > boardSize) { return false; }
        if(myBoard[row][column].isLocked()) { return false; }
        return !(valueInQuadrant(row, column, value) || valueInRow(row, value) || valueInColumn(column, value));
    }

    private boolean valueInQuadrant(int row, int col, int value) {
        if(row < 2) {
            if(col < 2) {
                for(int i = 0; i < 2; ++i) {
                    for(int j = 0; j < 2; ++j) {
                        if(myBoard[i][j].getValue() == value){ return true; }
                    }
                }
            } else {
                for (int i = 0; i < 2; ++i) {
                    for (int j = 2; j < boardSize; ++j) {
                        if (myBoard[i][j].getValue() == value) { return true; }
                    }
                }
            }
        } else {
            if (col < 2) {
                for (int i = 2; i < boardSize; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        if (myBoard[i][j].getValue() == value) { return true; }
                    }
                }
            } else {
                for (int i = 2; i < boardSize; ++i) {
                    for (int j = 2; j < boardSize; ++j) {
                        if (myBoard[i][j].getValue() == value) { return true; }
                    }
                }
            }
        }
        return false;
    }

    private boolean valueInRow(int row, int value) {
        for(int c = 0; c < boardSize; ++c) {
            if(myBoard[row][c].getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean valueInColumn(int col, int value) {
        for(int r = 0; r < boardSize; ++r) {
            if(myBoard[r][col].getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
