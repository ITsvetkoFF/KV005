package chessboard;

/**
 * Created by rotiutc on 11.09.2014.
 */
public class ChessUp {

    public static void main(String[] args) {
        ChessCell startCell = new ChessCell(1, 1);
        ChessCell endCell = new ChessCell(999, 1);
        ChessCell currentCell = startCell;
        ChessHorseFigure figure = new ChessHorseFigure();
        figure.setQueue(currentCell);
        int count = 0;
        do {
            count++;
            currentCell = figure.queue.pop();
            figure.setQueue(currentCell);
        } while (!figure.isEqual(currentCell, endCell));
        ChessCell cell = currentCell;
        //System.out.println(figure.previous[99][99].x  + ", " + figure.previous[99][99].y  );
        System.out.println("[" + endCell.x + "," + endCell.y + "]");
        int countSteps = 0;
        for (int j = 0; !figure.isEqual(cell, startCell); j++) {
            ChessCell result = figure.previous[cell.x][cell.y];
            String resultString = "[" + result.x + "," + result.y + "]";
            System.out.println(resultString);
            cell = result;
            countSteps++;
        }
        System.out.println("Visited cells: " + count + ". " + "Count of steps: " + countSteps);
        /*ChessCell currentCell = startCell;
        ChessHorseFigure figure = new ChessHorseFigure();
        figure.setQueue(currentCell);
        currentCell = figure.queue.pop();
        figure.setQueue(currentCell);
        for (int i = 0; i < figure.queue.size(); i++) {
            ChessCell result = figure.queue.get(i);
            String resultString = "[" + result.x + "," + result.y + "]";
            //System.out.println(figure.visitedCells[1][1]);
            System.out.println(resultString);
        }*/
    }
}
