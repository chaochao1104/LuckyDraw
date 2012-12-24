package util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

public class ImageUtil {

	public static ImageIcon loadImg(String filename) throws FileNotFoundException {
		String relativeFilePath = "res/img/" + filename;
		ImageIcon ret = new ImageIcon(relativeFilePath);
		if (ret.getImageLoadStatus() == MediaTracker.COMPLETE)
			return ret;

		throw new FileNotFoundException("Failed to load image: "
				+ relativeFilePath);
	}
	
	public static ImageIcon fitSize(ImageIcon img, Dimension d) {
		if (img == null)
			throw new IllegalArgumentException("img can not be null.");
		if (d == null)
			throw new IllegalArgumentException("d can not be null.");
		
		Image image = img.getImage();
        image = image.getScaledInstance(d.height, d.width, Image.SCALE_SMOOTH);
		
		return new ImageIcon(image);
	}
}
