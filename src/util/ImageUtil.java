package util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

public class ImageUtil {

	public static Image loadImg(String filename)
			throws FileNotFoundException {
		return loadImgIcon(filename, 1, 1).getImage();
	}
	
	public static ImageIcon loadImgIcon(String filename, double widthScale, double heightScale)
			throws FileNotFoundException {
		String relativeFilePath = "res/img/" + filename;
		ImageIcon ico = new ImageIcon(relativeFilePath);
		if (ico.getImageLoadStatus() != MediaTracker.COMPLETE)
			throw new FileNotFoundException("Failed to load image: "
					+ relativeFilePath);

		Image fittedImg = ico.getImage();
		Dimension scaledDimension = new Dimension();
		scaledDimension.setSize(ico.getIconWidth() * widthScale, ico.getIconHeight() * heightScale);
		fittedImg = fitSize(ico.getImage(), scaledDimension);
		
		return new ImageIcon(fittedImg);
	}

	public static Image fitSize(Image img, Dimension d) {
		if (img == null)
			throw new IllegalArgumentException("img can not be null.");
		if (d == null)
			throw new IllegalArgumentException("d can not be null.");

		return new ImageIcon(img.getScaledInstance(
				d.width, d.height, Image.SCALE_SMOOTH)).getImage();
	}
	
}
