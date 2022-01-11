package cs121.picture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;

import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class Picture implements Iterable<Pixel> {

	private final BufferedImage image;
	private final int width;
	private final int height;

	public Picture(String fileName) throws IOException {

		this(ImageIO.read(new File(fileName)));
	}

	/* a "copy constructor" */
	@SuppressWarnings("CopyConstructorMissesField")
	public Picture(Picture picture) {
		this(picture.image);
	}

	public Picture(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public Picture(BufferedImage oldImage) {
		this(oldImage.getWidth(), oldImage.getHeight());
		Graphics g = image.getGraphics();
		g.drawImage(oldImage, 0, 0, null);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// Package private, provides access to PictureShower class !!!
	BufferedImage getImage() {
		return image;
	}

	public Pixel getPixel(int x, int y) {
		return new Pixel(x, y, image);
	}

	public Pixel getPixel(PixelLocation location) {
		return new Pixel(location, image);
	}

	public Color getColor(int x, int y) {
		return getPixel(x, y).getColor();
	}

	public Color getColor(PixelLocation location) {
		return getPixel(location).getColor();
	}

	public void setColor(int x, int y, Color color) {
		getPixel(x, y).setColor(color);
	}

	public void setColor(PixelLocation location, Color color) {
		getPixel(location).setColor(color);
	}
	
	public Picture resize(int newWidth, int newHeight) {
		return transform(new Resizer(newWidth, newHeight));
	}
	
	public Picture paste(int x, int y, Picture picture) {
		return transform(new Paster(x, y, picture));
	}

	public PictureShower show() {
		return new PictureShower(this);
	}

	public PictureFrame frame() {
		return new PictureFrame(this);
	}

	public Picture transform(PictureTransformer transformer) {
		return transformer.apply(new Picture(this));
	}

	public Iterator<Pixel> iterator() {
		return new PixelIterator();
	}

	private final class PixelIterator implements Iterator<Pixel> {

		private PixelLocation location = new PixelLocation(0, 0);

		@Override
		public boolean hasNext() {
			return (location.x() < Picture.this.width) && (location.y() < Picture.this.height);
		}

		@Override
		public Pixel next() {
			Pixel nextPixel = new Pixel(location, Picture.this.image);
			if (location.x() + 1 < Picture.this.width) {
				location = new PixelLocation(location.x() + 1, location.y());
			} else if (location.y() + 1 < Picture.this.height) {
				location = new PixelLocation(0, location.y() + 1);
			} else {
				location = new PixelLocation(Picture.this.width, Picture.this.height);
			}

			return nextPixel;
		}
	}
}
