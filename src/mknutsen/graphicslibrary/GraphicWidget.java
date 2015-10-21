package mknutsen.graphicslibrary;

import java.awt.event.MouseEvent;

/**
 * Created by mknutsen on 10/21/15.
 */
public interface GraphicWidget {

    boolean isInside(MouseEvent e);

    boolean isInside(int x, int y);

    boolean isOverlapping(GraphicWidget other);

    boolean isOverlappingMaster(GraphicWidget other);
}
