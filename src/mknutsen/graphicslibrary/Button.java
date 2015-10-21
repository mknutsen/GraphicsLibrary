package mknutsen.graphicslibrary;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button simply puts texts in a button and centers it
 *
 * @author Max Knutsen - mknutse1@umbc.edu
 */
public class Button extends GraphicObject {

    /*
     * Default width and height for buttons
     */
    public static final int WIDTH = 50, HEIGHT = 50;

    private String text;

    /**
     * Makes button at (x,y) that says text and is the default size
     *
     * @param x
     *         top left corners x
     * @param y
     *         top left corners y
     * @param text
     *         words for it to print
     */
    public Button(int x, int y, String text) {
        this(x, y, WIDTH, HEIGHT, text);
    }

    /**
     * Makes button at (x,y) that says text and is the default size
     *
     * @param x
     *         top left corners x
     * @param y
     *         top left corners y
     * @param width
     *         width of button
     * @param height
     *         height of button
     * @param text
     *         words for it to print
     */
    public Button(int x, int y, int width, int height, String text) {
        super(x, y, width, height, false);
        this.text = text;
    }

    /**
     * Makes button at (x,y) that says text and is the default size
     *
     * @param x
     *         top left corners x
     * @param y
     *         top left corners y
     * @param width
     *         width of button
     * @param height
     *         height of button
     * @param text
     *         words for it to print
     * @param image
     *         image to display
     */
    public Button(int x, int y, int width, int height, String text, BufferedImage image) {
        super(x, y, width, height, false, image);
        this.text = text;
    }

    @Override
    public boolean draw(Graphics gr) {
        if (getImage() != null) {
            gr.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            gr.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);
        }
        int totalWidth = 0;
        for (int width : getWordWidth(text, gr.getFontMetrics())) {
            totalWidth += width;
        }
        gr.drawString(text, getX() + (getWidth() - totalWidth) / 2,
                getY() + gr.getFontMetrics().getHeight() + (getHeight() - gr.getFontMetrics().getHeight()) / 4);
        return true;
    }

    /**
     * @param text
     *         text to determine width
     * @param font
     *         font of the text
     * @return the width of the word
     */
    private int[] getWordWidth(String text, FontMetrics font) {
        int[] widths = new int[text.length()];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = font.getWidths()[text.charAt(i)];
        }
        return widths;
    }

    public String getText() {
        return text;
    }
}