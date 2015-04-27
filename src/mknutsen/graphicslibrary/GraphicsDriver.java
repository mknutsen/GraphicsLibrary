package mknutsen.graphicslibrary;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * GraphicsDriver Takes in multiple GraphicsComponents and switches between them
 * based on callback triggers.
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 */
public class GraphicsDriver extends JFrame {

	private static final long serialVersionUID = 1L;
	private int currentComponentCounter;
	private final GraphicsComponent[] components;

	/**
	 * Takes in any number of GraphicsComponents to switch between
	 * 
	 * @param components
	 *            GraphicsComponents to view in the given order
	 */
	public GraphicsDriver(final GraphicsComponent... components) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentComponentCounter = 0;
		this.components = components;
		setSize(823, 849);
		setVisible(true);
		setLocationRelativeTo(null);
		switchGraphicsComponent(null);

	}

	/**
	 * Closes the jFrame as if the x had been pressed
	 */
	private void killWindow() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	/**
	 * Switches graphics components
	 * 
	 * @param previouslyReturned
	 *            passes in whatever object(s) the previous graphics component
	 *            sent when it quit
	 */
	private void switchGraphicsComponent(Object[] previouslyReturned) {
		if (currentComponentCounter != 0) {
			components[currentComponentCounter - 1].setFocusable(false);
			remove(components[currentComponentCounter - 1]);
		}
		if (currentComponentCounter >= components.length) {
			killWindow();
		} else {
			components[currentComponentCounter].setPreferredSize(new Dimension(
					850, 850));
			components[currentComponentCounter]
					.setCallback(new CompletionCallback() {

						@Override
						public void execute(Object... returnValue) {
							switchGraphicsComponent(returnValue);
						}

					});
			if (previouslyReturned != null) {
				components[currentComponentCounter]
						.takeParameters(previouslyReturned);
			}
			add(components[currentComponentCounter]);
			components[currentComponentCounter].setFocusable(true);
			pack();
			components[currentComponentCounter].requestFocusInWindow();
			setSize(823, 849);
			setVisible(true);
			currentComponentCounter++;
		}
	}
}
