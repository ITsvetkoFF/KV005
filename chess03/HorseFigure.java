package softservinc.atqc.chess03;

import java.util.ArrayList;

/**
 * Created by Tanya on 12.09.2014.
 */
class HorseFigure {
    CellBoard board = new CellBoard();

    public ArrayList<Cell> getAvailableSteps(Cell currentCell) {
        ArrayList<Cell> availableSteps = new ArrayList<Cell>();
        for (int disX = -2; disX <= 2; disX++)//disX - displacement x
            for (int disY = -2; disY <= 2; disY++) {
                if (Math.abs(disX) + Math.abs(disY) == 3
                        && currentCell.x + disX <= 999//max x coordinate
                        && currentCell.y + disY <= 999
                        && currentCell.x + disX >= 0//min x coordinate on board
                        && currentCell.y + disY >= 0
                        && board.way[currentCell.x + disX][currentCell.y + disY] == null) {
                    Cell nextStep = new Cell(currentCell.x + disX, currentCell.y + disY);
                    availableSteps.add(nextStep);
                }
            }
        return availableSteps;
    }
}
