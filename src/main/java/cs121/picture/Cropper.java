package cs121.picture;

public final class Cropper extends PictureTransformer {
	private final int newWidth;
	private final int newHeight;
	private final int x;
	private final int y;

	public Cropper(int x, int y, int newWidth, int newHeight) {
		this.x = x;
		this.y = y;
		this.newWidth = newWidth;
		this.newHeight = newHeight;
	}

	public Cropper(PixelLocation location, RectangleSize size) {
		this(location.x(), location.y(), size.width(), size.height());
	}

	@Override
	public Picture apply(Picture oldPicture) {
		final Picture newPicture = new Picture(newWidth, newHeight);
		for (int xOffset = 0; xOffset < newWidth; xOffset++) {
			for (int yOffset = 0; yOffset < newHeight; yOffset++) {
				newPicture.setColor(x, y, oldPicture.getColor(x + xOffset,y + yOffset));
			}
		}
		return newPicture;
	}
}