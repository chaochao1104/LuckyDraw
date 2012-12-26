package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class BtnPanel extends JPanel {

	private static final long serialVersionUID = 2724873362749474725L;

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
//		"special-prize-s2.png",
//		"1st-prize-s2.png",
//		"2nd-prize-s2.png",
//		"3nd-prize-s2.png",
//		"4th-prize-s2.png",
		"special-prize-s1.png",
		"1st-prize-s1.png",
		"2nd-prize-s1.png",
		"3rd-prize-s1.png",
		"4th-prize-s1.png",
	};
	
	private static final int INTERVAL = 20;
	
	public BtnPanel(Point p, Dimension d) throws FileNotFoundException {
		this.setBounds(p.x, p.y, d.width, d.height);
		this.setLayout(null);
		initComponents();
		for (JLabel lblPrize : lblPrizes) {
			this.add(lblPrize);
		}
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

	public JLabel[] getPrizeButtons() {
		return Arrays.copyOf(lblPrizes, lblPrizes.length);
	}
}
