package cs121.picture;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.function.Function;

@SuppressWarnings("unused")
public class ColorTransformer extends PictureTransformer {

	private final Function<Color, Color> colorFunction;

	public ColorTransformer(Function<Color, Color> colorFunction) {
		this.colorFunction = colorFunction;
	}

	@Override
	public Picture apply(Picture picture) {
		final BufferedImage image = picture.getImage();
		final int height = picture.getHeight();
		final int width = picture.getWidth();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final int rgb = image.getRGB(x, y);
				final Color oldColor = new Color(rgb);
				final Color newColor = colorFunction.apply(oldColor);
				image.setRGB(x, y, newColor.getRGB());
			}
		}
		return picture;
	}
}
