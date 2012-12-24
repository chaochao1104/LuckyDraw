package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow {
	
	public MainWindow() {}
	
	public void start() {
		JFrame frame = new JFrame("Lucky Draw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ContentPane contentPane = new ContentPane();
		contentPane.setOpaque(true);
		frame.setContentPane(contentPane);
		frame.setResizable(false);
		frame.setUndecorated(false);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
//		frame.setSize(screenWidth, screenHeight);
		frame.setSize(1280, 1024);
		frame.setLocationRelativeTo(null);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setVisible(true);
	}

	public static void main(String... args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.start();
	}
	
}
