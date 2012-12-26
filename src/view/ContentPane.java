package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class ContentPane extends JPanel {

	private static final long serialVersionUID = 5744529722826165904L;
	
	private PrizeDisplayPanel pnlPrizeDisplay;
	
	private DrawPanel pnlDraw;
	
	private Image imgMainBackground;
	
	private Image imgPrize;
	
	Point debugPoint;
	
	public ContentPane() {
		super();
		loadResource();
		initComponents();
		debug();
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
		pnlPrizeDisplay.setBounds(70, 310, 300, 500);
		try {
			Image imgPrizeOriginal = ImageUtil.loadImg("prize.png");
			imgPrize = ImageUtil.fitSize(imgPrizeOriginal, pnlPrizeDisplay.getSize());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(pnlPrizeDisplay);

		try {
			pnlDraw = new DrawPanel(new Point(408, 295), new Dimension(867, 685));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlDraw.setOpaque(false);

		for (JLabel lblPrize : pnlDraw.getPnlBtnPanel().getPrizeButtons())
			this.add(lblPrize);
		
		
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
		g2.drawImage(imgMainBackground, 0, 0, null);
		
		pnlPrizeDisplay.paintImg(g, imgPrize);
		
		/*debug*/
		g2.setColor(Color.BLUE);
		paintRect(g2, pnlDraw.getBounds());
		paintRect(g2, pnlPrizeDisplay.getBounds());
		paintRect(g2, pnlDraw.getPnlInnerDraw().getBounds());
		paintRect(g2, pnlDraw.getPnlBtnPanel().getBounds());
				
		g2.setColor(Color.BLACK);
		g2.setFont(Font.getFont("ËÎÌו"));
		if (debugPoint != null) {
			g2.drawString(debugPoint.toString(), 0, 30);
		}
	}
	
	//for debug
	public void paintRect(Graphics g, Rectangle rect) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
}
