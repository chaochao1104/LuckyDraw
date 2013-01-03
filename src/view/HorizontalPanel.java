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
import util.ImageUtil;

public class HorizontalPanel extends JPanel {

	private static final long serialVersionUID = 6677235068546682813L;

	private static final String FONT_NAME = "Comic Sans MS";
	
	private static final int FONT_STYLE = Font.BOLD;
	
	private static final int FONT_SIZE = 70;

	private JLabel lblRoll;
	
	private JLabel lblDraw;
	
	private JLabel lblRedraw;
	
	private ImageIcon icoDrawGo;
	
	private ImageIcon icoDrawStop;
	
	private boolean isDrawStateGo;
	
	private boolean isGoing;
	
	private Candidate winner;
	
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
		lblRedraw.addMouseListener(listener);
	}
	
	public void flipDrawBtnState() {
		if (isDrawStateGo) 
			this.lblDraw.setIcon(icoDrawStop); 
		else 
			this.lblDraw.setIcon(icoDrawStop);
		
		isDrawStateGo = !isDrawStateGo;
	}
	
	public synchronized boolean isGoing() {
		return isGoing;
	}
	
	public synchronized void stopGoing() {
		this.isGoing = false;
	}
	
	public synchronized Thread startGoing(CandidateList candidateList) {
		if (isGoing()) return null;

		Thread ret = new Thread(new DrawRunner(candidateList));
		ret.start();
		this.isGoing = true;
		
		return ret;
	}
	
	public Candidate getWinner() {
		return this.winner;
	}
	
	public void clearRollingLabel() {
		this.lblRoll.setText("");
	}
	
	class DrawRunner implements Runnable {

		private CandidateList candidateList;
	
		DrawRunner(CandidateList candidateList) {
			this.candidateList = candidateList;
		}
	
		@Override
		public void run() {
						
			while(true) {
				if (!isGoing()) break;
				
				for (Candidate candidate : candidateList.getCandidateList()) {
					
					if (!isGoing()) { 
						break;
					}
					
					winner = candidate;
					String no = winner.getNo();
					lblRoll.setText(no);
										
					try {
						Thread.sleep(30);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
