package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PrizeDisplayPanel extends JPanel {

	private static final long serialVersionUID = 8775862004620045939L;
	
	public PrizeDisplayPanel() {
		super();
		this.setOpaque(false);
	}
		
	public void paintImg(Graphics g, Image img) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(img, this.getX(), this.getY(), null);
	}

}
