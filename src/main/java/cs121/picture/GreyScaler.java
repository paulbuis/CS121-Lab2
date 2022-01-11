package cs121.picture;

public final class GreyScaler extends PictureTransformer {

	@Override
	public Picture apply(Picture picture) {
		for (Pixel pixel : picture) {
			final Color oldColor = pixel.getColor();
			final int grey = (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
			pixel.setColor(new Color(grey, grey, grey));
		}
		return picture;
	}
}
