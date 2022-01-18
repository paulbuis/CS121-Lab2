The assignment for this week's lab uses the `picture`
package described below.

## `picture` package

### `Picture` class

A `Picture` object is an `Iterable` collection of
`Pixel` objects with a `RectangleSize` attribute.

### `Pixel` class

Each pixel has two attributes: a `PixelLocation` and a `Color`.

#### 'PixelLocation'

A `PixelLocation` record has 2 attributes: *x* and *y*.

#### 'Color'

A `Color` object has three attributes with values ranging from 0 to 255: *red*, *green*, and *blue*.

## Examples


### Gray Scale
To destructively convert a `Picture` named `picture`
to gray scale:
```java
		final int height = picture.getSize().height();
		final int width = picture.getSize().width();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Pixel pixel = picture.getPixel(x, y);
				final Color oldColor = pixel.getColor();
				final int gray = (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
				pixel.setColor(new Color(gray, gray, gray));
			}
		}
```

Alternatively, the following non-nested loop would do the same job:
```java
		for (Pixel pixel : picture) {
			final Color oldColor = pixel.getColor();
			final int grey = (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
			pixel.setColor(new Color(grey, grey, grey));
		}
```

### Cropping
This function will take `Picture` and return a new cropped `Picture`:
```java
	public Picture crop(Picture oldPicture, PixelLocation location,  RectagleSize newSize) {
		final Picture newPicture = new Picture(newSize.width(), newSize.height());
		for (int xOffset = 0; xOffset < newSize.width(); xOffset++) {
			for (int yOffset = 0; yOffset < newSize.height(); yOffset++) {
				newPicture.setColor(x, y, oldPicture.getColor(location.x() + xOffset, location.y() + yOffset));
			}
		}
		return newPicture;
	}
```

### Resizing

A simplistic resizer can be implemented as follows:

```java
	public Picture resize(Picture oldPicture, RectangleSize newSize) {
		final int oldHeight = oldPicture.getSize().height();
		final double heightRatio = oldHeight / (double) newSize.height();
		final int oldWidth = oldPicture.getWidth();
		final double widthRatio = oldWidth / (double) newSize.width();
		final Picture newPicture = new Picture(newSize);
		for (int x = 0; x < newSize.width(); x++) {
			for (int y = 0; y < newSize.height(); y++) {
				final int oldX = (int) (x * widthRatio);
				final int oldY = (int) (y * heightRatio);
				final Color oldColor = oldPicture.getColor(oldX, oldY);
				newPicture.setColor(x, y, oldColor);
			}
		}
		return newPicture;
	}
```

## License issues:

The `media_sources` directory contains a subset of the files
from [http://coweb.cc.gatech.edu/mediaComp-teach/uploads/1/mediasources-4ed.zip](http://coweb.cc.gatech.edu/mediaComp-teach/uploads/1/mediasources-4ed.zip)
licensed under the Creative Commons License
[Creative Commons Attribution 3.0 United States License](http://creativecommons.org/licenses/by/3.0/us/)
distributed for use with *Introduction to Computing and Programming in Python: A Multimedia Approach* by Mark Guzdial and Barbara Ericson.

This work is available for use with the same license.