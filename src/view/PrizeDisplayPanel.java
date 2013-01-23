package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import util.ImageUtil;

public class PrizeDisplayPanel extends JPanel {

	private static final long serialVersionUID = 8775862004620045939L;
	
	private Image img;
	
	public PrizeDisplayPanel(Dimension preferredSize) {
		super();
		this.setPreferredSize(preferredSize);
		this.setOpaque(false);
	}
	
	public void setImg(Image img) {
		this.img = img;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension imgSize = this.getSize();
		imgSize.setSize(imgSize.getWidth() * 0.9, imgSize.getHeight() * 0.8);
		Image tempImg = ImageUtil.fitSizeInSameRatio(img, imgSize);
		g2.drawImage(tempImg, 
				    (int) ((this.getWidth() - tempImg.getWidth(null)) / 2 ), 
				    (int) ((this.getHeight() * 0.8 - tempImg.getHeight(null)) / 2), 
				    null);
		
	}

}
