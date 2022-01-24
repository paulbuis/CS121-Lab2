package cs121.picture;

public final class Cropper extends PictureTransformer {
	private final int newWidth;
	private final int newHeight;
	private final int x;
	private final int y;

	public Cropper(int x, int y, int newWidth, int newHeight) {
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
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
		int width = newWidth <= oldPicture.getWidth() ? newWidth : oldPicture.getWidth();
		int height = newHeight <= oldPicture.getHeight() ? newHeight : oldPicture.getHeight();
		final Picture newPicture = new Picture(width, height);
		for (int xOffset = 0; xOffset < width; xOffset++) {
			for (int yOffset = 0; yOffset < height; yOffset++) {
				newPicture.setColor(x, y, oldPicture.getColor(x + xOffset,y + yOffset));
			}
		}
		return newPicture;
	}
}