package mknutsen.graphicslibrary.graphicsobject;

import mknutsen.graphicslibrary.GraphicWidget;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * CircleGraphicObject is a graphic object that is circular. it implements GraphicWidget so it can detect overlapping
 * and clicks in the circle.
 */
public abstract class CircleGraphicObject extends GraphicObject implements GraphicWidget {

    private int radius;

    /**
     * @param x
     *         x value of the center of the circle
     * @param y
     *         y value of the center of the circle
     * @param radius
     *         radius of the circle
     * @param isObjectMovable
     *         should a velocity be kept for the circle
     * @param image
     *         image to draw in the circle. if null, handle properly in the draw method
     */
    public CircleGraphicObject(int x, int y, int radius, boolean isObjectMovable, BufferedImage image) {
        super(x, y, isObjectMovable, image);
        this.radius = radius;
    }

    /**
     * Computes magnitude of distance between two points
     *
     * @param x1
     *         point1.x
     * @param y1
     *         point1.y
     * @param x2
     *         point2.x
     * @param y2
     *         point2.y
     * @return magnitude (positive) value of the distance between the two points
     */
    public final static double distance(int x1, int y1, int x2, int y2) {
        return Math.pow(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2), .5);
    }

    public static void main(String[] args) {
        CircleGraphicObject a = new CircleGraphicObject(0, 0, 100, false, null) {

            @Override
            public boolean draw(final Graphics gr) {
                return false;
            }
        };
        System.out.println(a.isInside(0, 99));
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
        return other.isInside(getX(), getY() - getRadius()) ||
                other.isInside(getX() - getRadius(), getY()) ||
                other.isInside(getX(), getY() + getRadius()) ||
                other.isInside(getX() + getRadius(), getY());
    }

    @Override
    public boolean isOverlappingMaster(final GraphicWidget other) {
        return isOverlapping(other) || other.isOverlapping(this);
    }

    /**
     * @return gets the radius of the circle
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius
     *         sets the radius of the circle
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
}
