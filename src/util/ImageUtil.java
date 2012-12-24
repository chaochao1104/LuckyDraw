package util;

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
	
		
}
