package cs121.picture;

@SuppressWarnings("unused")
public final class GrayScaler extends PictureTransformer {

	@Override
	public Picture apply(Picture picture) {
		final int height = picture.getHeight();
		final int width = picture.getWidth();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Pixel pixel = picture.getPixel(x, y);
				final Color oldColor = pixel.getColor();
				final int gray = (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
				pixel.setColor(new Color(gray, gray, gray));
			}
		}
		return picture;
	}

}
