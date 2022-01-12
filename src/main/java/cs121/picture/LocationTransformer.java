package cs121.picture;

import java.awt.image.BufferedImage;
import java.util.function.Function;

@SuppressWarnings("unused")
public class LocationTransformer extends PictureTransformer {
	private final Function<PixelLocation, PixelLocation> locationFunction;

	public LocationTransformer(Function<PixelLocation, PixelLocation> locationFunction) {
		this.locationFunction = locationFunction;
	}

	@Override
	public Picture apply(Picture oldPicture) {
		final BufferedImage oldImage = oldPicture.getImage();
		final int height = oldPicture.getHeight();
		final int width = oldPicture.getWidth();
		final Picture newPicture = new Picture(width, height);
		final BufferedImage newImage = newPicture.getImage();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final PixelLocation oldLocation = locationFunction.apply(new PixelLocation(x, y));
				// allow for negative values of x and y in oldLocation, also
				// allow for values of x and y greater than width and height
				final int oldX = (oldLocation.x() % width + width) % width;
				final int oldY = (oldLocation.y() % height + height) % height;
				newImage.setRGB(x, y, oldImage.getRGB(oldX, oldY));
			}
		}
		return newPicture;
	}
}
