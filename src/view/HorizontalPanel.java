package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Candidate;
import model.CandidateList;

import org.apache.log4j.Logger;

import util.ImageUtil;
import controller.strategy.DrawListener;

public class HorizontalPanel extends JPanel {

	private static final long serialVersionUID = 6677235068546682813L;

	private static final String FONT_NAME = "Comic Sans MS";
	
	private static final int FONT_STYLE = Font.BOLD;
	
	private static final int FONT_SIZE = 70;
	
	private static Logger logger = Logger.getLogger(HorizontalPanel.class.getName());

	private JLabel lblRoll;
	
	private JLabel lblDraw;
	
	private JLabel lblRedraw;
	
	private ImageIcon icoDrawRoll;
	
	private ImageIcon icoDrawStop;
	
	private boolean isRolling;
	
	private Candidate winner;
	
	private MouseListener redrawMouseListener;
	
	private DrawListener drawMouseListener;
	
	private Thread drawThread;
	
	public HorizontalPanel(Dimension preferredSize) throws FileNotFoundException {
		super(true);
		this.setPreferredSize(preferredSize);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		
		initComponents();
		
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 0;
		c.gridx = 0;
		c.insets = new Insets(0, -50, 0, 70);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		this.add(lblRoll, c);
		
		c.insets = new Insets(0, 0, 0, 10);
		c.ipadx = 0;
		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = GridBagConstraints.RELATIVE;
		this.add(lblDraw, c);
		
		c.gridx = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.add(lblRedraw, c);
	}
	
	private void initComponents() throws FileNotFoundException {
		lblRoll = new JLabel();
		lblRoll.setPreferredSize(new Dimension(200, 50));
		lblRoll.setForeground(Color.DARK_GRAY);
		lblRoll.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
		lblRoll.setHorizontalAlignment(JLabel.CENTER);
//		lblRoll.setOpaque(true); //for test
//		lblRoll.setBackground(Color.ORANGE); //for test
		
		icoDrawStop = ImageUtil.loadImgIcon("draw-stop.png");
		icoDrawRoll = ImageUtil.loadImgIcon("draw-go.png");
		lblDraw = new JLabel(icoDrawRoll);
		lblDraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ImageIcon icoRedraw = ImageUtil.loadImgIcon("redraw.png");
		lblRedraw = new JLabel(icoRedraw);
		lblRedraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}

	public void removeDrawBtnMouseListener() {
		lblDraw.removeMouseListener(drawMouseListener);
		drawMouseListener = null;
	}
	
	public void setDrawBtnMouseListener(DrawListener listener) {
		drawMouseListener = listener;
		lblDraw.addMouseListener(listener);
	}
	
	public DrawListener getDrawBtnMouseListener() {
		return drawMouseListener;
	}
	
	public void setRedrawBtnMouseListener(MouseListener listener) {
		redrawMouseListener = listener;
		lblRedraw.addMouseListener(listener);
	}
	
	public void setDrawBtnImgStop() {
		this.lblDraw.setIcon(icoDrawStop);
		lblDraw.revalidate();
		lblDraw.repaint();
	}
	
	public void setDrawBtnImgRoll() {
		this.lblDraw.setIcon(icoDrawRoll);
		lblDraw.revalidate();
		lblDraw.repaint();
	}
	
	public synchronized boolean isRolling() {
		return isRolling;
	}
	
	public Candidate stopRolling() {
		this.isRolling = false;
		
		if (drawThread == null) 
			return new Candidate("", "");
		
		try {
			drawThread.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		
		return getWinner();
	}
	
	public synchronized Thread startRolling(CandidateList candidateList) {
		if (isRolling()) return null;

		drawThread = new Thread(new DrawRunner(candidateList));
		drawThread.start();
		this.isRolling = true;
		
		return drawThread;
	}
	
	public Candidate getWinner() {
		return this.winner;
	}
	
	public void clearRollingLabel() {
		this.lblRoll.setText("");
	}
	
	public void setRedrawBtnEnabled(boolean enabled) {
		setLabelEnabled(lblRedraw, redrawMouseListener, enabled);
	}
	
	public void setDrawBtnEnabled(boolean enabled) {
		setLabelEnabled(lblDraw, drawMouseListener, enabled);
	}
	
	private void setLabelEnabled(JLabel label, MouseListener l, boolean enabled) {
		label.setEnabled(enabled);
		label.removeMouseListener(l);
		if (enabled)
			label.addMouseListener(l);
	}
	
	class DrawRunner implements Runnable {
		
		private CandidateList candidateList;
		
		DrawRunner(CandidateList candidateList) {
			this.candidateList = candidateList;
		}
		
		public void run() {
		
			while(true) {
				if (!isRolling()) break;
				
				for (Candidate candidate : candidateList.getCandidateList()) {
					
					if (!isRolling()) { 
						break;
					}
					
					winner = candidate;
					String no = winner.getNo();
					lblRoll.setText(no);
					
					try {
						Thread.sleep(16);
					} catch(Exception e) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		
	}
}
