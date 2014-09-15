package softservinc.atqc.chess03;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Tanya on 13.09.2014.
 */
class CellBoard {
    Cell startCell = new Cell(1, 1);
    Cell endCell = new Cell(1, 999);
    Cell[][]way = new Cell[1000][1000];

    public void setWay() {
        Cell cell = new Cell();
        HorseFigure figure = new HorseFigure();
        LinkedList<Cell> queue = new LinkedList<Cell>();
        Cell currentCell = startCell;
        while (!cell.isEqual(currentCell, endCell)) {
            ArrayList<Cell> nextSteps = figure.getAvailableSteps(currentCell);
            for (int i = 0; i < nextSteps.size(); i++) {
                if (way[nextSteps.get(i).x][nextSteps.get(i).y] == null) {
                    way[nextSteps.get(i).x][nextSteps.get(i).y] = currentCell;
                    queue.add(nextSteps.get(i));
                }
            }
            currentCell = queue.pop();
        }
    }
    public void outWay() {
        Cell cell = new Cell();
        Cell currentCell = endCell;
        int count = 0;
        System.out.println("[" + currentCell.x + "," + currentCell.y + "]");
        while (!cell.isEqual(currentCell, startCell)) {
            Cell result = way[currentCell.x][currentCell.y];
            String strResult = "[" + result.x + "," + result.y + "]";
            System.out.println(strResult);
            currentCell = result;
            count++;
        }
        System.out.println("Count of steps: " + count);
    }
}