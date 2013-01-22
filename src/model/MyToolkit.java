package model;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MyToolkit {
	
	public static final int DEFAULT_WINDOW_WIDTH = 1280;
	
	public static final int DEFAULT_WINDOW_HEIGHT = 1024;
	
	public static final Dimension SCREEN_SZIE = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final double HEIGHT_SCALE = SCREEN_SZIE.getHeight() / DEFAULT_WINDOW_HEIGHT;
	
	public static final double WIDTH_SCALE = SCREEN_SZIE.getWidth() / DEFAULT_WINDOW_WIDTH;
}
