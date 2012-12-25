package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class ContentPane extends JPanel {

	private static final long serialVersionUID = 5744529722826165904L;
	
	private PrizeDisplayPanel pnlPrizeDisplay;
	
	private JPanel pnlDraw;
	
	private ImageIcon imgMainBackground;
	
	private Image imgPrize;
	
	Point debugPoint;
	
	public ContentPane() {
		super();
		loadResource();
		initComponents();
		debug();
		
		JLabel l = new JLabel("Œ“ «ÀÔ≥¨");
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLACK);
		l.setOpaque(true);
		l.setVisible(true);
		
		this.add(l);
	}
	
	private void debug() {
		this.addMouseMotionListener(new MouseAdapter() {
			
			public void mouseMoved(MouseEvent e) {
				if (e.isControlDown()) {
					debugPoint = e.getPoint();
					repaint();
				} else {
					debugPoint = null;
				}
			}
			
		});
	}
	
	private void initComponents() {
	
		setLayout(null);
		
		pnlPrizeDisplay = new PrizeDisplayPanel();
		pnlPrizeDisplay.setBounds(80, 350, 300, 500);
		try {
			ImageIcon iconPrize = ImageUtil.loadImg("1.png");
			ImageIcon fittedImageIcon = 
					ImageUtil.resizeByContainer(iconPrize, new Dimension(pnlPrizeDisplay.getBounds().getSize()));
			pnlPrizeDisplay.setImg(fittedImageIcon);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//prizeDisplayPanel.add(new JButton(), BorderLayout.CENTER);
		
		add(pnlPrizeDisplay);
	}
	
	private void loadResource() {
		try {
			imgMainBackground = ImageUtil.loadImg("main-background.jpg");
		} catch (FileNotFoundException e) {
			//TODO
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(imgMainBackground.getImage(), 0, 0, null);
		
		pnlPrizeDisplay.showImg(g, imgPrize);
		
		/*debug*/
		g2.setColor(Color.BLACK);
		g2.setFont(Font.getFont("ÀŒÃÂ"));
		if (debugPoint != null) {
			g2.drawString(debugPoint.toString(), 0, 30);
		}
	}
	
}
