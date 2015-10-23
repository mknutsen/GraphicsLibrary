package mknutsen.graphicslibrary;

import java.awt.event.MouseEvent;

/**
 * Handles detecting whether or not something is inside a graphic or if something is overlapping with the graphic.
 * Created by mknutsen on 10/21/15.
 */
public interface GraphicWidget {

    /**
     * is the mouse event (x,y) inside the graphic
     *
     * @param e
     *         mouse event to check
     * @return true or false
     */
    boolean isInside(MouseEvent e);

    /**
     * is the coordinate inside the graphic
     *
     * @param x
     *         x coordinate
     * @param y
     *         y coordinate
     * @return true if inside/ false otherwise
     */
    boolean isInside(int x, int y);

    /**
     * checks if other is overlapping the graphic
     *
     * @param other
     *         other graphic object to check with
     * @return true if other is overlapping one of the corners
     */
    boolean isOverlapping(GraphicWidget other);

    /**
     * checks if the graphics are overlapping eachother
     *
     * @param other
     *         other graphic to check against
     * @return isOverLapping(other) || other.isOverlapping()
     */
    boolean isOverlappingMaster(GraphicWidget other);
}
