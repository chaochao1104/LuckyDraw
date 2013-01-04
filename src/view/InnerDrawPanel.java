package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InnerDrawPanel extends JPanel {
	
	private static final long serialVersionUID = 5079948721745142011L;
	
	private JPanel[] pnlDrawWinners = new JPanel[VerticalPanel.PRIZE_CATEGORY_QTY];
	
	private JPanel pnlCard = new JPanel(true);
	
	private int currentCardIdx = 0;
		
	public InnerDrawPanel(Dimension preferredSize) {
		super(true);
		this.setPreferredSize(preferredSize);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		initComponent();
		
		this.show(currentCardIdx);
	}
	
	private void initComponent() {
		Dimension preferredSize = getPreferredSize();
		preferredSize.height -= 10; 
		preferredSize.width -= 10; 
		pnlCard.setPreferredSize(preferredSize);
		pnlCard.setOpaque(false);
		pnlCard.setLayout(new CardLayout());

		this.add(pnlCard);
		
		for (int i = 0; i < VerticalPanel.PRIZE_CATEGORY_QTY; i++) {
			JPanel panel = new JPanel(true);
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 15));

			pnlDrawWinners[i] = panel;
			pnlCard.add(panel, Integer.toString(i));
		}
		
	}
	
	public void show(int idx) {
		if (idx < 0 || idx > VerticalPanel.PRIZE_CATEGORY_QTY) 
			throw new IllegalArgumentException();
		
		CardLayout cardLayout = (CardLayout) this.pnlCard.getLayout();
		cardLayout.show(this.pnlCard, Integer.toString(idx));
		this.currentCardIdx = idx;
	}
	
	public void addWinnerToBoard(String displayTxt, String tip, Font font, Color color) {
		JLabel displayLabel = new JLabel();
		displayLabel.setFont(font);
		displayLabel.setForeground(color);
		displayLabel.setText(displayTxt);
		displayLabel.setToolTipText(tip);
		pnlDrawWinners[currentCardIdx].add(displayLabel);
		displayLabel.revalidate(); //to show immediately.
		displayLabel.repaint();
	}
	
	public String removeLastWinner() {
		JPanel pnl = pnlDrawWinners[currentCardIdx];
		final int lastCompIdx = pnl.getComponentCount() - 1;
		
		if (lastCompIdx < 0) return "";
		
		JLabel lblLastWinner = (JLabel) pnl.getComponent(lastCompIdx);
		pnl.remove(lastCompIdx);
		
		pnl.revalidate();
		pnl.repaint();
		return lblLastWinner.getText();
	}
	
	public JPanel getCardPanel() {
		return pnlCard;
	}
}
