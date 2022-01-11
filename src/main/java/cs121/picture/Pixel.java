package cs121.picture;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public final class Pixel {
	private final PixelLocation location;
	private final BufferedImage image;

	public Pixel(int x, int y, BufferedImage image) {
		this(new PixelLocation(x, y), image);
	}

	public Pixel(PixelLocation location, BufferedImage image) {
		this.location = location;
		this.image = image;
	}

	public int getX() {
		return location.x();
	}

	public int getY() {
		return location.y();
	}

	public PixelLocation getLocation() {
		return location;
	}

	public Color getColor() {
		return new Color(image.getRGB(location.x(), location.y()));
	}

	public int getRed() {
		return getColor().getRed();
	}

	public int getGreen() {
		return getColor().getGreen();
	}

	public int getBlue() {
		return getColor().getBlue();
	}

	public void setColor(Color c) {
		image.setRGB(location.x(), location.y(), c.getRGB());
	}

	public void setRed(int red) {
		Color oldColor = getColor();
		Color newColor = new Color(red, oldColor.getGreen(), oldColor.getBlue());
		setColor(newColor);
	}

	public void setGreen(int green) {
		Color oldColor = getColor();
		Color newColor = new Color(oldColor.getRed(), green, oldColor.getBlue());
		setColor(newColor);
	}

	public void setBlue(int blue) {
		Color oldColor = getColor();
		Color newColor = new Color(oldColor.getRed(), oldColor.getGreen(), blue);
		setColor(newColor);
	}
}
