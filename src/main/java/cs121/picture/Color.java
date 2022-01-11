package cs121.picture;

/* this class exists so one can import cs121.image.* and not need to
 * import java.awt.Color
 */

import java.io.Serial;

public class Color extends java.awt.Color {

	@Serial
	private static final long serialVersionUID = -2130570556011744333L;

	public Color(int rgb) {
		super(rgb);
	}
	
	public Color(int red, int green, int blue) {
		super(red, green, blue);
	}
}
