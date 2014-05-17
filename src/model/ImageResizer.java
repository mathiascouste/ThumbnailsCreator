package model;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageResizer {
	BufferedImage image;
	File file;
	double width,height;
	
	public ImageResizer(File file) {
		this.file = file;
	}
	
	public boolean loadImage() {
		try {
			BufferedImage img = ImageIO.read(file);
			height = img.getHeight();
			width = img.getWidth();
			image = img;
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void resizeImage(int maxWidth,int maxHeight) {
		double fheight = 0;
		double fwidth = 0;
		if(height > width) {
			fheight = maxHeight;
			fwidth = fheight*width/height;
		} else if(height < width) {
			fwidth = maxWidth;
			fheight = fwidth*height/width;
		} else if (height==width){
			fwidth = maxWidth;
			fheight = maxWidth;
		}
		image = toBufferedImage(image.getScaledInstance((int)fwidth, (int)fheight, Image.SCALE_DEFAULT));
	}
	
	public void saveImage(String path) {
		try {
		    // retrieve image
		    BufferedImage bi = (BufferedImage)image;
		    File outputfile = new File(path);
		    ImageIO.write(bi, "jpg", outputfile);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public BufferedImage toBufferedImage(Image img) {
		int scaleX = (int) (img.getWidth(null));
		int scaleY = (int) (img.getHeight(null));
		Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
		BufferedImage buffered = new BufferedImage(scaleX, scaleY, Image.SCALE_DEFAULT);
		buffered.getGraphics().drawImage(image, 0, 0 , null);
		return buffered;
	}
}
