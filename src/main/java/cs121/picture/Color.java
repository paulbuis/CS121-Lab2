package cs121.picture;

/* this class exists so one can import cs121.image.* and not need to
 * import java.awt.Color
 */

import java.io.Serial;

public final class Color extends java.awt.Color {

	@Serial
	private static final long serialVersionUID = -2130570556011744333L;

	public Color(int rgb) {
		super(rgb);
	}
	
	public Color(int red, int green, int blue) {
		super(red, green, blue);
	}

	public static double distance(java.awt.Color color1, java.awt.Color color2) {
		double redDiff = color1.getRed() - color2.getRed();
		double greenDiff = color1.getGreen() - color2.getGreen();
		double blueDiff = color1.getBlue() - color2.getBlue();
		return Math.sqrt(redDiff*redDiff + greenDiff*greenDiff + blueDiff*blueDiff);
	}

	public double distance(Color color) {
		if (color == null) {
			throw new IllegalArgumentException("Color.distance(color==null), color may not be null");
		}
		double redDiff = getRed() - color.getRed();
		double greenDiff = getGreen() - color.getGreen();
		double blueDiff = getBlue() - color.getBlue();
		return Math.sqrt(redDiff*redDiff + greenDiff*greenDiff + blueDiff*blueDiff);
	}
}
