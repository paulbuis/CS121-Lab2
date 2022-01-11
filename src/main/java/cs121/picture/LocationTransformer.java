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
				final int oldX = (oldLocation.x() % width + width) % width;
				final int oldY = (oldLocation.y() % height + height) % height;
				final int oldRGB = oldImage.getRGB(oldX, oldY);
				newImage.setRGB(x, y, oldRGB);
			}
		}
		return newPicture;
	}
}
