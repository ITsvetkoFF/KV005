package softservinc.atqc.chess02;

import java.util.LinkedList;

/**
 * Created by Tanya on 13.09.2014.
 */
public class CellBoardUp {

    public static void main(String[] args) {
        CellBoard board = new CellBoard();
        board.setWay();
        board.outWay();

        /*HorseFigure figure = new HorseFigure();
        LinkedList<Cell> list = figure.getAvailableNextSteps(new Cell(1, 1));
        for (int i = 0; i < list.size(); i++) {
            Cell cell = list.get(i);
            String strCell = "[" + cell.x + "," + cell.y + "]";
            System.out.println(strCell);
        }*/
    }
}
