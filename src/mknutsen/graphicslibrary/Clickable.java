package mknutsen.graphicslibrary;

import java.awt.event.MouseEvent;

/**
 * Created by mknutsen on 10/21/15.
 */
public interface Clickable {

    boolean isInside(MouseEvent e);
}
