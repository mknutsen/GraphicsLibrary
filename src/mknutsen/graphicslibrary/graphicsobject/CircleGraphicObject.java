package mknutsen.graphicslibrary.graphicsobject;

import mknutsen.graphicslibrary.GraphicWidget;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created by mknutsen on 10/21/15.
 */
public abstract class CircleGraphicObject extends GraphicObject implements GraphicWidget {

    private int radius;

    public CircleGraphicObject(int x, int y, int radius, boolean isObjectMovable, BufferedImage image) {
        super(x, y, isObjectMovable, image);
        this.radius = radius;
    }

    public final static double distance(int x1, int y1, int x2, int y2) {
        return Math.pow(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2), .5);
    }

    @Override
    public boolean isInside(final MouseEvent e) {
        return isInside(e.getX(), e.getY());
    }

    @Override
    public boolean isInside(final int x, final int y) {
        return distance(x, y, getX(), getY()) < getRadius();
    }

    @Override
    public boolean isOverlapping(final GraphicWidget other) {
        return false;
    }

    @Override
    public boolean isOverlappingMaster(final GraphicWidget other) {
        return false;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
