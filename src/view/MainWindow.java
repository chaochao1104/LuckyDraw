package view;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import util.ExceptionUtil;

public class MainWindow {
	
	private static Logger logger = Logger.getLogger(MainWindow.class.getName());
	
	public MainWindow() {}
	
	public static void start() {
		JFrame frame = new JFrame("Lucky Draw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ContentPane contentPane = null;
		
		try {
			contentPane = new ContentPane();
		} catch (Exception e) {
			logger.error(ExceptionUtil.getStackTrace(e));
		}
		
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);
		frame.setResizable(false);
		frame.setUndecorated(true);

		frame.setSize(1280, 1024);
		frame.setLocationRelativeTo(null);//appear in center of the screen.
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getStackTrace(e));
		}
		
		frame.setVisible(true);
	}

	public static void main(String... args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
            	try {
            		MainWindow.start();	
            	} catch (Exception e) {
            		logger.error(ExceptionUtil.getStackTrace(e));
            	}
            	
            }
            
        });
	}
	
}
