package softservinc.atqc.chess02;

import java.util.LinkedList;

/**
 * Created by Tanya on 12.09.2014.
 */
class HorseFigure {

    public LinkedList<Cell> getAvailableNextSteps(Cell currentCell) {//get list of available next steps
        LinkedList<Cell> availableNextSteps = new LinkedList<Cell>();
        CellBoard board = new CellBoard();
        for (int disX = -2; disX <= 2; disX++)//disX - displacement x
            for (int disY = -2; disY <= 2; disY++) {
                if (Math.abs(disX) + Math.abs(disY) == 3
                        && currentCell.x + disX <= 999//max x coordinate
                        && currentCell.y + disY <= 999
                        && currentCell.x + disX >= 0//min x coordinate on board
                        && currentCell.y + disY >= 0
                        && board.way[currentCell.x + disX][currentCell.y + disY] == null) {
                    Cell nextStep = new Cell(currentCell.x + disX, currentCell.y + disY);
                    availableNextSteps.add(nextStep);
                }
            }
        return availableNextSteps;
    }
}
