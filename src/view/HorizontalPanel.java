package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class HorizontalPanel extends JPanel {

	private static final long serialVersionUID = 6677235068546682813L;

	public HorizontalPanel(Dimension preferredSize) {
		super();
		this.setPreferredSize(preferredSize);
		this.setOpaque(false);
		this.setLayout(new GridLayout());
	}
}
