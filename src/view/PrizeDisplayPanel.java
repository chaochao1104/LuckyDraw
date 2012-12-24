package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrizeDisplayPanel extends JPanel {

	private static final long serialVersionUID = 8775862004620045939L;
	
	private JLabel lblDisplay = new JLabel();
	
	public PrizeDisplayPanel() {
		super();
		this.setOpaque(false);
//		this.setLayout(new BorderLayout());
		
		this.add(lblDisplay);
		//this.add(lblDisplay, BorderLayout.CENTER);
	}
	
	private void resize() {
		lblDisplay.setSize(this.getSize());
	}
	
	public void setImg(ImageIcon img) {
		if (img == null)
			return;
		resize();
		this.lblDisplay.setIcon(img);
	}

}
