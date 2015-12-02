package mknutsen.graphicslibrary.graphicsobject;

import mknutsen.graphicslibrary.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * GraphicObject has all of the basic components for any rectangular based on object that will be being displayed
 * including position, width, height, and velocity.
 *
 * @author Max Knutsen - mknutse1@umbc.edu
 */
public abstract class GraphicObject {

    private final boolean moving;

    private BufferedImage image;

    private int x, y;

    private double velocity;

    public GraphicObject(int x, int y, boolean isObjectMovable, BufferedImage image) {
        super();
        this.image = image;
        this.moving = isObjectMovable;
        this.x = x;
        this.y = y;
        if (isObjectMovable) {
            // if the object isnt going to stay stil,
            // the velocity of the piece will be tracked
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int originalX = getX(), originalY = getY();
                    while (true) {
                        int deltaX = getX() - originalX;
                        int deltaY = getY() - originalY;

                        int sign;
                        if (deltaX == 0) {
                            sign = deltaY == 0 ? 0 : deltaY / Math.abs(deltaY);
                        } else if (deltaY == 0) {
                            sign = deltaX / Math.abs(deltaX);
                        } else {
                            sign = deltaX / Math.abs(deltaX) * deltaY / Math.abs(deltaY);
                        }

                        double distanceTraveled = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                        setVelocity((double) distanceTraveled / (double) Config.VELOCITY_SLEEP_AMOUNT * sign);
                        originalX = getX();
                        originalY = getY();
                        try {
                            Thread.sleep(Config.VELOCITY_SLEEP_AMOUNT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }).start();
        }
    }

    public GraphicObject(int x, int y, boolean isObjectMovable) {
        this(x, y, isObjectMovable, null);
    }

    public final int getX() {
        return x;
    }

    public final void setX(int x) {
        this.x = x;
    }

    public final int getY() {
        return y;
    }

    public final void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;

    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public final double getVelocity() {
        return velocity;
    }

    /**
     * Assigns the velocity if the player is moving
     *
     * @param velocity
     *         the velocity to assign
     */
    private void setVelocity(double velocity) {
        this.velocity = moving ? velocity : 0;
    }

    /**
     * Draws the object on given canvas
     *
     * @param gr
     *         canvas to draw on
     * @return false if the object could not be drawn for some reason
     */
    public abstract boolean draw(Graphics gr);
}
