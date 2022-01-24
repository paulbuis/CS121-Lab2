package cs121.picture;

public class Resizer extends PictureTransformer {
	private final int newWidth;
	private final int newHeight;

	public Resizer(int newWidth, int newHeight) {
		if (newWidth <= 0) {
			throw new IllegalArgumentException(
				String.format("newWidth=%d, must be positive", newWidth));
		}
		if (newHeight <= 0) {
			throw new IllegalArgumentException(
					String.format("newHeight=%d, must be positive", newHeight));
		}
		this.newWidth = newWidth;
		this.newHeight = newHeight;
	}

	public Resizer(RectangleSize size) {
		this(size.width(), size.height());
	}

	@Override
	public Picture apply(Picture oldPicture) {
		final int oldHeight = oldPicture.getHeight();
		final double heightRatio = oldHeight / (double) newHeight;
		final int oldWidth = oldPicture.getWidth();
		final double widthRatio = oldWidth / (double) newWidth;
		final Picture newPicture = new Picture(newWidth, newHeight);
		for (int x = 0; x < newWidth; x++) {
			for (int y = 0; y < newHeight; y++) {
				final int oldX = (int) (x * widthRatio);
				final int oldY = (int) (y * heightRatio);
				final Color oldColor = oldPicture.getColor(oldX, oldY);
				newPicture.setColor(x, y, oldColor);
			}
		}
		return newPicture;
	}
}
