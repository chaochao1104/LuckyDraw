package util;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

public class ImageUtil {

	public static ImageIcon loadImg(String filename)
			throws FileNotFoundException {
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

	public static BufferedImage resizeByContainer(BufferedImage srcImage, Dimension container) {
		double scale = Math.min(container.getHeight() / srcImage.getHeight(), 
				container.getWidth() / srcImage.getHeight());
		int width = (int) ((double) srcImage.getWidth() * scale);
		int height = (int) ((double) srcImage.getHeight() * scale);

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.scale(scale, scale);

		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		AffineTransformOp affineTransformOp = new AffineTransformOp(
				affineTransform, hints);

		BufferedImage dstImage = new BufferedImage(width, height, srcImage.getType());

		return affineTransformOp.filter(srcImage, dstImage);
	}
	
}
