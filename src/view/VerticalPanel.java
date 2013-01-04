package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class VerticalPanel extends JPanel {

	private static final long serialVersionUID = 2724873362749474725L;

	public static final int PRIZE_QTY = 5;
	
	private JLabel[] lblPrizes;
	
	private ImageIcon[] imgIconState1sts;
	
	private ImageIcon[] imgIconState2nds;

	private static final String[] state1stImgNames = {
		"special-prize-s1.png",
		"1st-prize-s1.png",
		"2nd-prize-s1.png",
		"3rd-prize-s1.png",
		"4th-prize-s1.png",
	};
	
	private static final String[] state2ndImgNames = {
		"special-prize-s2.png",
		"1st-prize-s2.png",
		"2nd-prize-s2.png",
		"3rd-prize-s2.png",
		"4th-prize-s2.png",
	};
	
	private static final int INTERVAL = 20;
	
	public VerticalPanel(Dimension preferredSize) throws FileNotFoundException {
		this.setPreferredSize(preferredSize);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		
		initComponents();
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.ipady = 8;
		for (JLabel lblPrize : lblPrizes) {
			this.add(lblPrize, c);
			c.gridy++;
		}
		
		setOnlyButtonDown(0);
	}
	
	private void initComponents() throws FileNotFoundException {
		final int btnCount = state1stImgNames.length;
				
		lblPrizes = new JLabel[btnCount];
		imgIconState1sts = new ImageIcon[btnCount];
		imgIconState2nds = new ImageIcon[btnCount];
		
		int tllHeight = 0;
		for (int i = 0; i < btnCount; i++) {
			imgIconState1sts[i] = ImageUtil.loadImgIcon(state1stImgNames[i]);
			imgIconState2nds[i] = ImageUtil.loadImgIcon(state2ndImgNames[i]);
			tllHeight += imgIconState1sts[i].getIconHeight();
		}
		
		tllHeight += (btnCount - 1) * INTERVAL;
		
		for (int i = 0; i < btnCount; i++) {
			lblPrizes[i] = new JLabel(imgIconState1sts[i]);
			lblPrizes[i].setBounds(this.getX() + (this.getWidth() - imgIconState1sts[i].getIconWidth()) / 2, 
								   this.getY() + (this.getHeight() - tllHeight) / 2 + i * (imgIconState1sts[i].getIconHeight() + INTERVAL),
								   imgIconState1sts[i].getIconWidth(), 
								   imgIconState1sts[i].getIconHeight());
			lblPrizes[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

	}
	
	public void setMouseListeners(MouseListener[] mouseListeners) {
		if (mouseListeners.length != PRIZE_QTY)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < mouseListeners.length; i++) {
			lblPrizes[i].addMouseListener(mouseListeners[i]);
		}
	}
	
	public void setOnlyButtonDown(int idx) {
		if (idx < 0 || idx > PRIZE_QTY - 1)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < lblPrizes.length; i++)
			lblPrizes[i].setIcon(imgIconState1sts[i]);
		
		lblPrizes[idx].setIcon(imgIconState2nds[idx]);
	}

}
