package mknutsen.graphicslibrary;

import javax.swing.*;

/**
 * GraphicsComponent sets forth the basic requirements of a JPanel to communicate properly with the graphics driver.
 *
 * @author Max Knutsen - mknutse1@umbc.edu
 */
public abstract class GraphicsComponent extends JPanel {

    private static final long serialVersionUID = 1L;

    private CompletionCallback callback;

    /**
     * Necessary to equip the component with the ability to trigger component switching
     *
     * @param callback
     *         callback to execute
     */
    public final void setCallback(CompletionCallback callback) {
        this.callback = callback;
    }

    /**
     * Trigger the callback to trigger a component switch
     *
     * @param returnValue
     *         give the following information back to the next component
     */
    protected final void triggerCallback(Object... returnValue) {
        callback.execute(returnValue);
    }

    /**
     * Handles the parameters the previous component wanted this one to have
     *
     * @param obj
     *         parameters from previous component
     */
    public abstract void takeParameters(Object[] obj);
}
