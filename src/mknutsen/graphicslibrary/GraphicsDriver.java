package mknutsen.graphicslibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * GraphicsDriver Takes in multiple GraphicsComponents and switches between them based on callback triggers.
 *
 * @author Max Knutsen - mknutse1@umbc.edu
 */
public class GraphicsDriver extends JFrame {

    private static final long serialVersionUID = 1L;

    private final GraphicsComponent[] components;

    private int currentComponentCounter, width, height;

    /**
     * Takes in any number of GraphicsComponents to switch between
     *
     * @param width
     *         width of the frame
     * @param height
     *         height of the frame
     * @param components
     *         GraphicsComponents to view in the given order
     */
    public GraphicsDriver(int width, int height, final GraphicsComponent... components) {
        this.width = width;
        this.height = height;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentComponentCounter = 0;
        this.components = components;
        setSize(width, height);
        setVisible(true);
        setLocationRelativeTo(null);
        switchGraphicsComponent(null);
    }

    public static void main(String[] args) {
        new GraphicsDriver(700, 700, new GraphicsComponent() {

            @Override
            public void takeParameters(final Object[] obj) {

            }
        });
    }

    /**
     * Closes the jFrame as if the x had been pressed
     */
    private final void killWindow() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

    }

    /**
     * Switches graphics components
     *
     * @param previouslyReturned
     *         passes in whatever object(s) the previous graphics component sent when it quit
     */
    private final void switchGraphicsComponent(Object[] previouslyReturned) {
        if (currentComponentCounter != 0) {
            components[currentComponentCounter - 1].setFocusable(false);
            remove(components[currentComponentCounter - 1]);
        }
        if (currentComponentCounter >= components.length) {
            killWindow();
        } else {
            components[currentComponentCounter].setPreferredSize(new Dimension(width, height));
            components[currentComponentCounter].setCallback(new CompletionCallback() {

                @Override
                public void execute(Object... returnValue) {
                    switchGraphicsComponent(returnValue);
                }

            });
            if (previouslyReturned != null) {
                components[currentComponentCounter].takeParameters(previouslyReturned);
            }
            add(components[currentComponentCounter]);
            components[currentComponentCounter].setFocusable(true);
            pack();
            components[currentComponentCounter].requestFocusInWindow();
            setVisible(true);
            currentComponentCounter++;
        }
    }
}
