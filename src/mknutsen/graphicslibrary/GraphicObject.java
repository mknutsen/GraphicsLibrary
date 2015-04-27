package mknutsen.graphicslibrary;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * GraphicObject has all of the basic components for any rectangular based on
 * object that will be being displayed including position, width, height, and
 * velocity.
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 */
public abstract class GraphicObject {
	private int x, y, width, height;
	private double velocity;
	private boolean moving;

	public GraphicObject(int x, int y, int width, int height,
			boolean isObjectMovable) {
		super();
		this.moving = isObjectMovable;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
							sign = deltaX / Math.abs(deltaX) * deltaY
									/ Math.abs(deltaY);
						}

						double distanceTraveled = Math.sqrt(Math.pow(deltaX, 2)
								+ Math.pow(deltaY, 2));
						setVelocity((double) distanceTraveled
								/ (double) Config.VELOCITY_SLEEP_AMOUNT * sign);
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Determine if the given point is inside the object
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return true if point is inside, false otherwise
	 */
	public boolean isInside(int x, int y) {
		return ((this.y <= y && this.y + height >= y) && (this.x <= x && this.x
				+ width >= x));
	}

	/**
	 * Determines if other object overlaps with this object
	 * 
	 * @param other
	 *            potentially overlapping object
	 * @return true if overlapping, false otherwise
	 */
	public boolean isOverlapping(GraphicObject other) {
		return isInside(other.x, other.y)
				|| isInside(other.x + other.width, other.y)
				|| isInside(other.x, other.y + other.height)
				|| isInside(other.x + other.width, other.y + other.height);

	}

	/**
	 * Determine if the given point is inside the object
	 * 
	 * @param e
	 *            MouseEvent signifying a point
	 * @return true if point is inside, false otherwise
	 */
	public boolean isInside(MouseEvent e) {
		return isInside(e.getX(), e.getY());
	}

	private void setVelocity(double velocity) {
		this.velocity = moving ? velocity : 0;
	}

	public double getVelocity() {
		return velocity;
	}

	/**
	 * Draws the object on given canvas
	 * 
	 * @param gr
	 *            canvas to draw on
	 * @return false if the object could not be drawn for some reason
	 */
	public abstract boolean draw(Graphics gr);
}