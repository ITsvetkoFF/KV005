package chessboard;

import java.util.LinkedList;

/**
 * Created by rotiutc on 11.09.2014.
 */
public class ChessHorseFigure {

    ChessCell[] steps = {new ChessCell(1, 2),
                         new ChessCell(2, 1),
                         new ChessCell(-1, 2),
                         new ChessCell(2, -1),
                         new ChessCell(-2, 1),
                         new ChessCell(1, -2),
                         new ChessCell(-2, -1),
                         new ChessCell(-1, -2)};

    ChessCell maxCell = new ChessCell(999, 999);
    boolean[][] visitedCells = new boolean[1000][1000];//true - visited
    ChessCell[][] previous = new ChessCell[1000][1000];
    LinkedList<ChessCell> queue = new LinkedList<ChessCell>();

    public void setQueue(ChessCell cell) {
        for (int i = 0; i < 8; i++) {
            if (cell.x + steps[i].x <= maxCell.x
                    && cell.y + steps[i].y <= maxCell.y
                    && cell.x + steps[i].x >= 0
                    && cell.y + steps[i].y >= 0
                    && !visitedCells[cell.x + steps[i].x][cell.y + steps[i].y]) {
                ChessCell next = new ChessCell(cell.x + steps[i].x, cell.y + steps[i].y);
                visitedCells[cell.x][cell.y] = true;
                visitedCells[cell.x + steps[i].x][cell.y + steps[i].y] = true;//set visited cells
                if (previous[cell.x + steps[i].x][cell.y + steps[i].y] == null) {
                    previous[cell.x + steps[i].x][cell.y + steps[i].y] = cell;
                }
                queue.add(next);
            }
        }
    }
    public boolean isEqual(ChessCell current, ChessCell end) {
        return (current.x == end.x && current.y == end.y);
    }
}

