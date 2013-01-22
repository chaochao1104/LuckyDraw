package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

import model.MyToolkit;

public class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = -6985725399213380053L;

	private InnerDrawPanel pnlInnerDraw;
	
	private VerticalPanel pnlVertical;
	
	private HorizontalPanel pnlHorizontal;
	
	public DrawPanel(Dimension preferredSize) throws FileNotFoundException {
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(preferredSize);
		
		pnlInnerDraw = new InnerDrawPanel(
				new Dimension(preferredSize.width - (int)(170 * MyToolkit.WIDTH_SCALE), 
						preferredSize.height - (int)(70 * MyToolkit.HEIGHT_SCALE)));
		
		pnlVertical = new VerticalPanel(
				new Dimension((int)(170 * MyToolkit.WIDTH_SCALE), 
						      preferredSize.height));
		pnlHorizontal = new HorizontalPanel(
				new Dimension(preferredSize.width, 
				              (int)(70 * MyToolkit.HEIGHT_SCALE)));
		
		this.add(pnlInnerDraw, BorderLayout.CENTER);
		this.add(pnlVertical, BorderLayout.EAST);
		this.add(pnlHorizontal, BorderLayout.SOUTH);
	}
	
	public InnerDrawPanel getPnlInnerDraw() {
		return pnlInnerDraw;
	}

	public VerticalPanel getPnlVertical() {
		return pnlVertical;
	}

	public HorizontalPanel getPnlHorizontal() {
		return pnlHorizontal;
	}

}
