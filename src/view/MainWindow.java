package view;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainWindow {
	
	public MainWindow() {}
	
	public static void start() {
		JFrame frame = new JFrame("Lucky Draw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ContentPane contentPane = new ContentPane();
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);
		frame.setResizable(false);
		frame.setUndecorated(true);

		frame.setSize(1280, 1024);
		frame.setLocationRelativeTo(null);//appear in center of the screen.
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame.setVisible(true);
	}

	public static void main(String... args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
            	MainWindow.start();
            }
            
        });
	}
	
}
