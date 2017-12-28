/*
 * Borrowed heavily and adapted from https://introcs.cs.princeton.edu/java/home/.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {
	// colors
	public static final Color RED = Color.RED;
	public static final Color ORANGE = Color.ORANGE;
	public static final Color YELLOW = Color.YELLOW;
	public static final Color GREEN = Color.GREEN;
	public static final Color BLUE = Color.BLUE;
	public static final Color WHITE = Color.WHITE;
	public static final Color BLACK = Color.BLACK;
	
	// defaults
	private static final Color DEFAULT_PEN_COLOR = BLACK;
	private static final Color DEFAULT_CLEAR_COLOR = WHITE;
	
	private static final double DEFAULT_PEN_RADIUS = 0.002;
	
	private static final int DEFAULT_SIZE = 512;
    private static int width = DEFAULT_SIZE;
    private static int height = DEFAULT_SIZE;

    private static final double BORDER = 0.00;
    private static final double DEFAULT_XMIN = 0.0;
    private static final double DEFAULT_XMAX = 1.0;
    private static final double DEFAULT_YMIN = 0.0;
    private static final double DEFAULT_YMAX = 1.0;
    
    private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 16);
    
	// current state variable
	private static Color penColor;
	private static double penRadius;

    private static double xmin, ymin, xmax, ymax;

    private static Font font;

    // buffered graphics
    private static BufferedImage graphicsImage;
    private static Graphics2D graphics;

    // the frame for drawing to the screen
    private static JFrame frame;
    
    static {
    	initialize();
    }
    
    private static void initialize() {
    	setCanvasSize();
    	
    	frame = new JFrame();
    	graphicsImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = graphicsImage.createGraphics();
        
        setXScale();
        setYScale();
        
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 512, 512);

        // frame stuff
        ImageIcon icon = new ImageIcon(graphicsImage);
        JLabel draw = new JLabel(icon);

        frame.setContentPane(draw);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("Display");

        frame.pack();
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }

	public static void setCanvasSize() {
		setCanvasSize(DEFAULT_SIZE, DEFAULT_SIZE);
	}

	public static void setCanvasSize(int w, int h) {
		width = w;
		height = h;
	}
	
	public static void setXScale() {
		setXScale(DEFAULT_XMIN, DEFAULT_XMAX);
	}

	public static void setXScale(double min, double max) {
		double size = max - min;
		
		xmin = min - BORDER * size;
        xmax = max + BORDER * size;
	}
	
	public static void setYScale() {
		setYScale(DEFAULT_YMIN, DEFAULT_YMAX);
	}

	public static void setYScale(double min, double max) {
		double size = max - min;
		
		ymin = min - BORDER * size;
        ymax = max + BORDER * size;
	}
}
