package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class HorizontalPanel extends JPanel {

	private static final long serialVersionUID = 6677235068546682813L;

	private JLabel lblRoll;
	
	private JLabel lblDraw;
	
	private JLabel lblRedraw;
	
	public HorizontalPanel(Dimension preferredSize) throws FileNotFoundException {
		super();
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
		lblRoll.setOpaque(true); //for test
		lblRoll.setBackground(Color.ORANGE); //for test
			
		ImageIcon icoDraw = ImageUtil.loadImgIcon("draw.png");
		lblDraw = new JLabel(icoDraw);
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
	
}
