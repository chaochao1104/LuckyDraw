package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class ContentPane extends JPanel {

	private static final long serialVersionUID = 5744529722826165904L;
	
	private JPanel blankBarPanel;
	
	private JPanel prizeDisplayPanel;
	
	private JPanel drawPanel;
	
	private ImageIcon mainBackgroundImg;
	
	public ContentPane() {
		super();
		loadResource();
		initComponents();
	}
	
	private void initComponents() {
		//for test
		JLabel l = new JLabel();
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLACK);
		l.setOpaque(true);
		
		JButton btn = new JButton("HGHGH");
		
		setLayout(new GridBagLayout());
		
		blankBarPanel = new JPanel();
		blankBarPanel.setSize(new Dimension(1280, 250));
		blankBarPanel.setOpaque(false);
		blankBarPanel.setLayout(new BorderLayout());
		blankBarPanel.add(btn, BorderLayout.CENTER);
		
		
		this.add(blankBarPanel, BorderLayout.NORTH);
		
	}
	
	private void loadResource() {
		try {
			mainBackgroundImg = ImageUtil.loadImg("main-background.jpg");
		} catch (FileNotFoundException e) {
			//TODO
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2.drawImage(mainBackgroundImg.getImage(), 0, 0, null);
		
	}
}
