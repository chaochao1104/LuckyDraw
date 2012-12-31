package view;

import java.awt.BorderLayout;
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

import javax.swing.JPanel;

import util.ImageUtil;

public class ContentPane extends JPanel {

	private static final long serialVersionUID = 5744529722826165904L;
	
	private PrizeDisplayPanel pnlPrizeDisplay;
	
	private DrawPanel pnlDraw;
	
	private JPanel pnlUpperBlankBar;
	
	private JPanel pnlBottomBlankBar;
	
	private Image imgMainBackground;
	
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
	
		setLayout(new BorderLayout(10, 20));
		
		pnlUpperBlankBar = new JPanel();
		pnlUpperBlankBar.setPreferredSize(new Dimension(1280, 274));
		pnlUpperBlankBar.setOpaque(false);
		this.add(pnlUpperBlankBar, BorderLayout.NORTH);
		
		pnlBottomBlankBar = new JPanel();
		pnlBottomBlankBar.setPreferredSize(new Dimension(1280, 22));
		pnlBottomBlankBar.setOpaque(false);
		this.add(pnlBottomBlankBar, BorderLayout.SOUTH);
		
		pnlPrizeDisplay = new PrizeDisplayPanel(new Dimension(400, 600));
		try {
			Image imgPrize = ImageUtil.loadImg("prize.png");
			pnlPrizeDisplay.setImg(imgPrize);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		this.add(pnlPrizeDisplay, BorderLayout.WEST);

		try {
			pnlDraw = new DrawPanel(new Dimension(867, 600));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlDraw.setOpaque(false);
		MouseAdapter[] verticalMouseAdapters = new MouseAdapter[VerticalPanel.PRIZE_QTY];
		for (int i = 0; i < VerticalPanel.PRIZE_QTY; i++)
			verticalMouseAdapters[i] = new VerticalMouseListener(i);
		
		pnlDraw.getPnlVertical().setMouseListeners(verticalMouseAdapters);
		
		pnlDraw.getPnlHorizontal().setDrawButtonMouseListener(new DrawButtonMouseListener());
		pnlDraw.getPnlHorizontal().setRedrawButtonMouseListener(new RedrawButtonMouseListener());
		this.add(pnlDraw, BorderLayout.CENTER);
		
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
		
		/*debug*/
		g2.setColor(Color.CYAN);
		paintRect(g2, pnlBottomBlankBar.getBounds());
		paintRect(g2, pnlUpperBlankBar.getBounds());
		paintRect(g2, pnlDraw.getBounds());
		paintRect(g2, pnlPrizeDisplay.getBounds());
		
		g2.translate(pnlDraw.getX(), pnlDraw.getY());
		paintRect(g2, pnlDraw.getPnlInnerDraw().getBounds());
		paintRect(g2, pnlDraw.getPnlVertical().getBounds());
		paintRect(g2, pnlDraw.getPnlHorizontal().getBounds());
		paintRect(g2, pnlDraw.getPnlInnerDraw().getCardPanel().getBounds());
		g2.translate(-pnlDraw.getX(), -pnlDraw.getY());
		
		g2.setColor(Color.BLACK);
		g2.setFont(Font.getFont("ËÎÌו"));
		if (debugPoint != null) {
			g2.drawString(debugPoint.toString(), 0, 30);
		}
	}
	
	class VerticalMouseListener extends MouseAdapter {
	    
		private int idx;
		
		public VerticalMouseListener(int idx) {
			super();
			this.idx = idx;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			VerticalPanel pnlVertical = pnlDraw.getPnlVertical();
			pnlVertical.setButtonDown(idx);
			
			pnlDraw.getPnlInnerDraw().show(idx);
			//TODO: set cardlayout
	    }
		
	}
	
	class DrawButtonMouseListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			//TODO: add more logic
	    }
	}
	
	class RedrawButtonMouseListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			//TODO: add more logic
	    }
	}
	
	//for debug
	public void paintRect(Graphics g, Rectangle rect) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(rect.x + 1, rect.y + 1, rect.width - 3, rect.height - 3);
	}
	

}
