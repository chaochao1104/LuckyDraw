package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JPanel;

import model.exception.InitModelError;
import model.exception.InitUIError;

public class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = -6985725399213380053L;

	private InnerDrawPanel pnlInnerDraw;
	
	private VerticalPanel pnlVertical;
	
	private HorizontalPanel pnlHorizontal;
	
	public DrawPanel(Dimension preferredSize) throws InitUIError, InitModelError {
		super();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(preferredSize);
		
		pnlInnerDraw = new InnerDrawPanel(new Dimension(preferredSize.width - 170, preferredSize.height - 70));
				
		try {
			pnlVertical = new VerticalPanel(new Dimension(170, preferredSize.height));
			pnlHorizontal = new HorizontalPanel(new Dimension(preferredSize.width, 70));
		} catch(FileNotFoundException e) {
			throw new InitUIError(e.getMessage());
		}	
		
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
