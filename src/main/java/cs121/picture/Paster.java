package cs121.picture;

public final class Paster extends PictureTransformer {

	private final int xOffset;
	private final int yOffset;
	private final Picture pastePicture;

	public Paster(int xOffset, int yOffset, Picture picture) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.pastePicture = picture;
	}

	@Override
	public Picture apply(Picture picture) {
		for (Pixel pastePixel : pastePicture) {
			int x = xOffset + pastePixel.getX();
			int y = yOffset + pastePixel.getY();
			if (x < picture.getWidth() && y < picture.getHeight()) {
				picture.setColor(x, y, pastePixel.getColor());
			}
		}
		return picture;
	}
}
