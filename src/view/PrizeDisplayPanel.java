package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrizeDisplayPanel extends JPanel {

	private static final long serialVersionUID = 8775862004620045939L;
	
	private JLabel lblDisplay = new JLabel();
	
	public PrizeDisplayPanel() {
		super();
		this.setOpaque(false);
//		this.setLayout(new BorderLayout());
		
		this.add(lblDisplay);
		//this.add(lblDisplay, BorderLayout.CENTER);
	}
	
	private void resize() {
		lblDisplay.setSize(this.getSize());
	}
	
	public void setImg(ImageIcon img) {
//		if (img == null)
//			return;
//		resize();
//		this.lblDisplay.setIcon(img);
	}
	
	@Override	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		lblDisplay.setBounds(x, y, width, height);
	}
	
	public void showImg(Graphics g, Image img) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(img, this.getX(), this.getY(), null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2.drawImage(null, 0, 0, null);
		g2.setColor(Color.WHITE);
		g2.drawRect(lblDisplay.getX(), lblDisplay.getY(), lblDisplay.getWidth(), lblDisplay.getHeight());
		g2.setColor(Color.BLACK);
		g2.fillRect(lblDisplay.getX(), lblDisplay.getY(), lblDisplay.getWidth(), lblDisplay.getHeight());
	}

}
