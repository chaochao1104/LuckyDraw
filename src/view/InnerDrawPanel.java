package view;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class InnerDrawPanel extends JPanel {
	
	private static final long serialVersionUID = 5079948721745142011L;
	
	private JPanel[] pnlDrawWinners = new JPanel[5];
	
	public InnerDrawPanel() {
		super();
		this.setLayout(new CardLayout());
		this.setOpaque(false);
	}
	
}
