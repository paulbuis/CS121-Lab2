package cs121.picture;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public final class Pixel {
	private final PixelLocation location;
	private final BufferedImage image;


	Pixel(int x, int y, BufferedImage image) {
		this(new PixelLocation(x, y), image);
	}


	Pixel(PixelLocation location, BufferedImage image) {
		if (location.x() < 0) {
			throw new IllegalArgumentException(
					String.format("location.x()=%d, must not be negative",
							location.x()));
		}
		if (location.y() < 0) {
			throw new IllegalArgumentException(
					String.format("location.y()=%d, must not be negative",
							location.y()));
		}
		if (location.x() >= image.getWidth()) {
			throw new IllegalArgumentException(
					String.format("location.x()=%d, image width=%d, location.x() must be less than width",
							location.x(), image.getWidth()));
		}
		if (location.y() >= image.getHeight()) {
			throw new IllegalArgumentException(
					String.format("location.y()=%d, image height=%d, location.y() must be less than height",
							location.y(), image.getHeight()));
		}
		this.location = location;
		this.image = image;
	}

	/**
	 *
	 * @return distance, in pixels, from left edge
	 */
	public int getX() {
		return location.x();
	}

	/**
	 *
	 * @return distance, in pixels, from top edge
	 */
	public int getY() {
		return location.y();
	}

	/**
	 *
	 * @return the location of this pixel
	 */
	public PixelLocation getLocation() {
		return location;
	}

	/**
	 *
	 * @return the color of this pixel
	 */
	public Color getColor() {
		return new Color(image.getRGB(location.x(), location.y()));
	}

	/**
	 *
	 * @return red component of pixel color
	 */
	public int getRed() {
		return getColor().getRed();
	}

	/**
	 *
	 * @return green component of pixel color
	 */
	public int getGreen() {
		return getColor().getGreen();
	}

	/**
	 *
	 * @return blue component of pixel color
	 */
	public int getBlue() {
		return getColor().getBlue();
	}

	/**
	 *
	 * @param c new color for pixel
	 */
	public void setColor(Color c) {
		image.setRGB(location.x(), location.y(), c.getRGB());
	}

	/**
	 * Sets the pixel to a new color, does not change green and blue components of color
	 * @param red new value for red component of color
	 */
	public void setRed(int red) {
		Color oldColor = getColor();
		setColor(new Color(red, oldColor.getGreen(), oldColor.getBlue()));

	}

	/**
	 * Sets the pixel to a new color, does not change red and blue components of color
	 * @param green new value for green component of color
	 */
	public void setGreen(int green) {
		Color oldColor = getColor();
		setColor(new Color(oldColor.getRed(), green, oldColor.getBlue()));
	}

	/**
	 * Sets the pixel to a new color, does not change red and green components of color
	 * @param blue new value for green component of color
	 */
	public void setBlue(int blue) {
		Color oldColor = getColor();
		setColor(new Color(oldColor.getRed(), oldColor.getGreen(), blue));
	}
}
