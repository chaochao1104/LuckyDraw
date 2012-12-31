package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = -6985725399213380053L;

	private InnerDrawPanel pnlInnerDraw;
	
	private VerticalPanel pnlVertical;
	
	private HorizontalPanel pnlHorizontal;
	
	public DrawPanel(Dimension preferredSize) throws FileNotFoundException {
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(preferredSize);
		
		pnlInnerDraw = new InnerDrawPanel(new Dimension(preferredSize.width - 170, preferredSize.height - 70));
		this.add(pnlInnerDraw, BorderLayout.CENTER);
				
		pnlVertical = new VerticalPanel(new Dimension(170, preferredSize.height));
		this.add(pnlVertical, BorderLayout.EAST);
		
		pnlHorizontal = new HorizontalPanel(new Dimension(preferredSize.width, 70));
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
