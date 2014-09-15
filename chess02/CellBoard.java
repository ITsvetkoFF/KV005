package softservinc.atqc.chess02;

import java.util.LinkedList;

/**
 * Created by Tanya on 13.09.2014.
 */
class CellBoard {

    Cell startCell = new Cell(1, 1);
    Cell endCell = new Cell(3, 3);

    Cell[][]way = new Cell[1000][1000];
    LinkedList<Cell> queue = new LinkedList<Cell>();

    public void setWay() {
        Cell cell = new Cell();
        HorseFigure figure = new HorseFigure();
        Cell currentCell = startCell;
        while (!cell.isEqual(currentCell, endCell)) {
            LinkedList<Cell> nextSteps = figure.getAvailableNextSteps(currentCell);
            for (int i = 0; i < nextSteps.size(); i++) {
                queue.add(nextSteps.get(i));
                if (way[nextSteps.get(i).x][nextSteps.get(i).y] == null)
                    way[nextSteps.get(i).x][nextSteps.get(i).y] = currentCell;
            }
            currentCell = queue.pop();
        }
    }
    public void outWay() {
        Cell cell = new Cell();
        Cell currentCell = endCell;
        int count = 0;
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