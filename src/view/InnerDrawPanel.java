package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;

import model.Prize;

public class InnerDrawPanel extends JPanel {
	
	private static final long serialVersionUID = 5079948721745142011L;
	
	private WinnerBoard[] pnlDrawWinners = new WinnerBoard[VerticalPanel.PRIZE_CATEGORY_QTY];
	
	private JPanel pnlCard = new JPanel(true);
	
	private int currentCardIdx = 0;
	
	private List<Prize> prizes; 
	
	public InnerDrawPanel(Dimension preferredSize, List<Prize> prizes) {
		super(true);
		this.prizes = prizes;
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

		this.add(pnlCard, BorderLayout.CENTER);
		
		for (int i = 0; i < VerticalPanel.PRIZE_CATEGORY_QTY; i++) {
			WinnerBoard panel = new WinnerBoard(prizes.get(i).getDisplayBlockQty());
			panel.setOpaque(false);
			//panel.setLayout();
			panel.setPreferredSize(new Dimension(preferredSize.width, 0));
			
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
		pnlDrawWinners[currentCardIdx].addWinner(displayTxt, tip, font, color);
	}
	
	public String removeLastWinner() {
		return pnlDrawWinners[currentCardIdx].removeLastWinner();
	}
	
	public JPanel getCardPanel() {
		return pnlCard;
	}
}
