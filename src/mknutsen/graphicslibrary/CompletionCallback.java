package mknutsen.graphicslibrary;

/**
 * Callback to trigger on completion
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public abstract class CompletionCallback {
	/**
	 * executes the given protocol
	 * 
	 * @param returnValue
	 *            values to pass to the next component
	 */
	public abstract void execute(Object... returnValue);

}
