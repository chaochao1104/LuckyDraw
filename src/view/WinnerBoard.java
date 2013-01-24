package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinnerBoard extends JPanel {

	private static final long serialVersionUID = -5559089739286784218L;
	
	private JPanel[] panels;
	
	private int idx = 0;
	
	private int next() {	
		if (idx == panels.length - 1) {
			int temp = idx;
			idx = 0;
			return temp;
		}
		
		return idx++;
	}
	
	private int previous() {
		if (idx == 0)
			return idx = panels.length - 1;
		return --idx;
	}
	
	public WinnerBoard(int blocks) {
		super();
		this.setOpaque(false);
		this.setLayout(new GridLayout(0, blocks));
		
		panels = new JPanel[blocks];
		
		for (int i = 0; i < panels.length; i++) {
			JPanel panel = new JPanel();
			panel = new JPanel();
			panel.setOpaque(false);
			//panel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5));
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			this.add(panels[i] = panel);
		}
		
	}
	
	public void addWinner(String displayTxt, String tip, Font font, Color color) {
		JLabel displayLabel = new JLabel();
		displayLabel.setFont(font);

		displayLabel.setForeground(color);
		displayLabel.setText(displayTxt);
		displayLabel.setToolTipText(tip);
		
		panels[next()].add(displayLabel);
		displayLabel.revalidate(); //to show immediately.
		displayLabel.repaint();
		
	}
	
	public String removeLastWinner() {
		int idx = previous();
		JPanel pnlPrevious = panels[idx];
		
		final int lastCompIdx = pnlPrevious.getComponentCount() - 1;
		
		if (lastCompIdx < 0) {
			this.idx = 0;
			return "";
		}
		
		JLabel lblLastWinner = (JLabel) pnlPrevious.getComponent(lastCompIdx);
		pnlPrevious.remove(lastCompIdx);
		
		pnlPrevious.revalidate();
		pnlPrevious.repaint();
		
		return lblLastWinner.getText().trim();
	}
	
}
