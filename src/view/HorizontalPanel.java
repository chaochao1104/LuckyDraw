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

public class HorizontalPanel extends JPanel {

	private static final long serialVersionUID = 6677235068546682813L;

	private static final String FONT_NAME = "Comic Sans MS";
	
	private static final int FONT_STYLE = Font.BOLD;
	
	private static final int FONT_SIZE = 70;
	
	private static Logger logger = Logger.getLogger(HorizontalPanel.class.getName());

	private JLabel lblRoll;
	
	private JLabel lblDraw;
	
	private JLabel lblRedraw;
	
	private ImageIcon icoDrawGo;
	
	private ImageIcon icoDrawStop;
	
	private boolean isDrawStateGo = true;
	
	private boolean isRolling;
	
	private Candidate winner;
	
	private MouseListener redrawMouseListener;
	
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
		icoDrawGo = ImageUtil.loadImgIcon("draw-go.png");
		lblDraw = new JLabel(icoDrawGo);
		lblDraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ImageIcon icoRedraw = ImageUtil.loadImgIcon("redraw.png");
		lblRedraw = new JLabel(icoRedraw);
		lblRedraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
	}

	public void setDrawButtonMouseListener(MouseListener listener) {
		lblDraw.addMouseListener(listener);
	}
	
	public void setRedrawButtonMouseListener(MouseListener listener) {
		redrawMouseListener = listener;
		lblRedraw.addMouseListener(listener);
	}
	
	public void flipDrawBtnState() {
		if (isDrawStateGo) 
			this.lblDraw.setIcon(icoDrawStop); 
		else 
			this.lblDraw.setIcon(icoDrawGo);
		
		lblDraw.revalidate();
		lblDraw.repaint();
		isDrawStateGo = !isDrawStateGo;
	}
	
	public synchronized boolean isRolling() {
		return isRolling;
	}
	
	public synchronized void stopRolling() {
		this.isRolling = false;
	}
	
	public synchronized Thread startRolling(CandidateList candidateList) {
		if (isRolling()) return null;

		Thread ret = new Thread(new DrawRunner(candidateList));
		ret.start();
		this.isRolling = true;
		
		return ret;
	}
	
	public Candidate getWinner() {
		return this.winner;
	}
	
	public void clearRollingLabel() {
		this.lblRoll.setText("");
	}
	
	public void setRedrawBtnEnabled(boolean visible) {
		lblRedraw.setEnabled(visible);
		lblRedraw.removeMouseListener(redrawMouseListener);
		if (visible)
			lblRedraw.addMouseListener(redrawMouseListener);
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
