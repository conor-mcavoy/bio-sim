/*
 * Borrowed heavily and adapted from https://introcs.cs.princeton.edu/java/home/.
 */

import java.awt.*;
import java.awt.geom.Rectangle2D;
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
        graphics.fillRect(0, 0, width, height);

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
	
	private static double  scaleX(double x) { return width  * (x - xmin) / (xmax - xmin); }
    private static double  scaleY(double y) { return height * (ymax - y) / (ymax - ymin); }
    private static double factorX(double w) { return w * width  / Math.abs(xmax - xmin);  }
    private static double factorY(double h) { return h * height / Math.abs(ymax - ymin);  }
    private static double   userX(double x) { return xmin + x * (xmax - xmin) / width;    }
    private static double   userY(double y) { return ymax - y * (ymax - ymin) / height;   }
	
	public static void clear() {
		clear(DEFAULT_CLEAR_COLOR);
	}
    
    public static void clear(Color color) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(penColor);
        draw();
    }
    
    public static void setPenColor() {
    	setPenColor(DEFAULT_PEN_COLOR);
    }
    
    public static void setPenColor(Color color) {
        penColor = color;
        graphics.setColor(penColor);
    }
    
    private static void pixel(double x, double y) {
        graphics.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
    }
    
    public static void filledSquare(double x, double y, double r) {
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2*r);
        double hs = factorY(2*r);
        if (ws <= 1 && hs <= 1) {
        	pixel(x, y);
        } else {
        	graphics.fill(new Rectangle2D.Double(xs - ws/2, ys - hs/2, ws, hs));
        }
        draw();
    }
    
    public static void show() {
        draw();
    }
    
    private static void draw() {
        graphics.drawImage(graphicsImage, 0, 0, null);
        frame.repaint();
    }
}
