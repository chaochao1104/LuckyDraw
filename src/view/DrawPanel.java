package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class DrawPanel extends JPanel {
	
	private static final long serialVersionUID = -6985725399213380053L;

	private InnerDrawPanel pnlInnerDraw;
	
	private BtnPanel pnlBtnPanel;
	
	public DrawPanel(Point p, Dimension d) throws FileNotFoundException {
		super();
		this.setLayout(null);
		this.setBounds(new Rectangle(p, d));
		
		pnlInnerDraw = new InnerDrawPanel();
		pnlInnerDraw.setBounds(this.getX(), this.getY(), this.getWidth() - 200, this.getHeight() - 68);
		pnlInnerDraw.setOpaque(false);
		this.add(pnlInnerDraw);
		
		pnlBtnPanel = new BtnPanel();
		pnlBtnPanel.setBounds(1100, this.getY(), 170, this.getHeight());
		pnlInnerDraw.setOpaque(false);
		this.add(pnlBtnPanel);
		
		ImageIcon icoSpecialPrizeState1 = null;
		try {
			icoSpecialPrizeState1 = ImageUtil.loadImgIcon("special_prize_s1.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lblSpecialPrize = new JLabel(icoSpecialPrizeState1);
		lblSpecialPrize.setBounds(1105, 400, icoSpecialPrizeState1.getIconWidth(), icoSpecialPrizeState1.getIconHeight());
		lblSpecialPrize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSpecialPrize.setOpaque(true);
		this.add(lblSpecialPrize);
	}

	public InnerDrawPanel getPnlInnerDraw() {
		return pnlInnerDraw;
	}

	public BtnPanel getPnlBtnPanel() {
		return pnlBtnPanel;
	}
		
}
