package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = -6985725399213380053L;

	private InnerDrawPanel pnlInnerDraw;
	
	private BtnPanel pnlBtnPanel;
	
	public DrawPanel(Point p, Dimension d) throws FileNotFoundException {
		super();
		this.setLayout(null);
		this.setBounds(new Rectangle(p, d));
		
		pnlInnerDraw = new InnerDrawPanel();
		pnlInnerDraw.setBounds(this.getX(), this.getY(), this.getWidth() - 200, this.getHeight() - 68);
		pnlInnerDraw.setOpaque(false);
		this.add(pnlInnerDraw);
		
		pnlBtnPanel = new BtnPanel(new Point(1100, this.getY() - 30), new Dimension(170, this.getHeight()));
		
		
		this.add(pnlBtnPanel);
		
	}

	public InnerDrawPanel getPnlInnerDraw() {
		return pnlInnerDraw;
	}

	public BtnPanel getPnlBtnPanel() {
		return pnlBtnPanel;
	}

}
