package view;

import java.awt.Cursor;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class BtnPanel extends JPanel {

	private static final long serialVersionUID = 2724873362749474725L;

	private JLabel lblSpecialPrize;
	
	private ImageIcon icoSpecialPrizeState1;
	
	private JLabel lbl1stPrize;
	
	private JLabel lbl2ndPrize;
	
	private JLabel lbl3rdPrize;
	
	private JLabel lbl4thPrize;

	public BtnPanel() throws FileNotFoundException {
		this.setLayout(null);
		icoSpecialPrizeState1 = ImageUtil.loadImgIcon("special_prize_s1.png");
		lblSpecialPrize = new JLabel(icoSpecialPrizeState1);
		lblSpecialPrize.setBounds(1105, 400, icoSpecialPrizeState1.getIconWidth(), icoSpecialPrizeState1.getIconHeight());
		lblSpecialPrize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSpecialPrize.setOpaque(true);
		this.add(lblSpecialPrize);
	}

	public JLabel getLblSpecialPrize() {
		return lblSpecialPrize;
	}

	public JLabel getLbl1stPrize() {
		return lbl1stPrize;
	}

	public JLabel getLbl2ndPrize() {
		return lbl2ndPrize;
	}

	public JLabel getLbl3rdPrize() {
		return lbl3rdPrize;
	}

	public JLabel getLbl4thPrize() {
		return lbl4thPrize;
	}
	
//	@Override
//	public void paintComponent(Graphics g) {
//		
//	}
}
