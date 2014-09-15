package softservinc.atqc.chess02;

import java.util.LinkedList;

/**
 * Created by Tanya on 12.09.2014.
 */
class Cell {

    int x;
    int y;

    public Cell() {
    }
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean isEqual(Cell currentCell, Cell endCell) {
        return (currentCell.x == endCell.x && currentCell.y == endCell.y);
    }
}