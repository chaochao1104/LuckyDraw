package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	//for test
	public static void main(String[] args) {
		JFrame f = new JFrame("GridBagLayout");
		f.setLayout(new GridBagLayout());
		JButton btn = new JButton("first");
		GridBagConstraints gbc = new GridBagConstraints();
		// 设定第一个单元格的属性值
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		f.add(btn, gbc);

		// 设定第二个单元格属性值
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.NONE;
		gbc.gridheight = GridBagConstraints.NONE;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		btn = new JButton("second");
		f.add(btn, gbc);

		// 设定第三个单元格属性值
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.ipadx = 10;
		gbc.ipady = 10;
		btn = new JButton("three");
		f.add(btn, gbc);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
