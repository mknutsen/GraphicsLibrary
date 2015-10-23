package mknutsen.graphicslibrary.graphicsobject;

import mknutsen.graphicslibrary.GraphicWidget;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Scanner;

/**
 * Extension of GraphicObject that handles rectangualr shaped object. Implements GraphicWidget.
 */
public abstract class RectangleGraphicObject extends GraphicObject implements GraphicWidget {

    private int width, height;

    /**
     * Basic constructor.
     *
     * @param x
     *         x location of top left
     * @param y
     *         y location of top left
     * @param width
     *         width of the rectangle
     * @param height
     *         height of the rectangle
     * @param isObjectMovable
     *         true if the object is moveable and should have a velocity associated with it
     * @param image
     *         image to be drawn (if null,  handle the null)
     */
    public RectangleGraphicObject(int x, int y, int width, int height, boolean isObjectMovable, BufferedImage image) {
        super(x, y, isObjectMovable, image);
        this.width = width;
        this.height = height;
    }

    public static void main(String[] args) {
        RectangleGraphicObject a = new RectangleGraphicObject(0, 0, 100, 100, false, null) {

            @Override
            public boolean draw(final Graphics gr) {
                return false;
            }
        };
        RectangleGraphicObject b = new RectangleGraphicObject(0, 200, 100, 100, false, null) {

            @Override
            public boolean draw(final Graphics gr) {
                return false;
            }
        };
        RectangleGraphicObject c = new RectangleGraphicObject(50, 50, 200, 200, false, null) {

            @Override
            public boolean draw(final Graphics gr) {
                return false;
            }
        };
        Scanner scan = new Scanner(System.in);
        System.out.println(a.isOverlappingMaster(b));
        System.out.println(a.isOverlappingMaster(c));
        System.out.println(b.isOverlappingMaster(c));
    }

    /**
     * @return the width of the rectangle
     */
    public final int getWidth() {
        return width;
    }

    /**
     * @param width
     *         new width of the rectangle
     */
    public final void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return height of the rectangle
     */
    public final int getHeight() {
        return height;
    }

    /**
     * @param height
     *         new height of the rectangle
     */
    public final void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isInside(final MouseEvent e) {
        return isInside(e.getX(), e.getY());
    }

    @Override
    public boolean isInside(final int x, final int y) {
        return (x > getX() && x < getX() + getWidth()) && (y > getY() && y < getY() + getHeight());
    }

    @Override
    public boolean isOverlapping(final GraphicWidget other) {
        //        System.out.printf("X %d Y %d Width %d Height %d", getX(), getY(), getWidth(), getHeight());
        for (int i = getX(); i <= getX() + getWidth(); i += getWidth()) {
            for (int j = getY(); j <= getY() + getHeight(); j += getHeight()) {
                if (other.isInside(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isOverlappingMaster(final GraphicWidget other) {
        return isOverlapping(other) || other.isOverlapping(this);
    }
}
