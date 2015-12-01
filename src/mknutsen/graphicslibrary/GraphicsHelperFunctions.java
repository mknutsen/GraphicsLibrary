package mknutsen.graphicslibrary;

import java.awt.*;

/**
 * Created by mknutsen on 12/1/15.
 */
public final class GraphicsHelperFunctions {

    private GraphicsHelperFunctions() {

    }

    public static void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
    }

    public static void drawCenteredString(Graphics g, String text, int y, int width) {
        int[] widths = g.getFontMetrics().getWidths();
        for (String line : text.split("\n")) {
            int lineWidth = 0;
            for (final char c : line.toCharArray()) {
                lineWidth += widths[c];
            }
            int x = (width - lineWidth) / 2;
            g.drawString(line, x, y);
            y += g.getFontMetrics().getHeight();
        }
    }
}
