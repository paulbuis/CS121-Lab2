The assignment for this week's lab uses the `picture`
package described below.

## `picture` package

Reverse engineered from the textbook discussion of [JES](https://github.com/gatech-csl/jes):
the *Jython Environment for Students*
in *Introduction to Computing and Programming in Python: A Multimedia Approach*
by Mark Guzdial and Barbara Ericson.

### `Picture` class

A `Picture` object is an `Iterable` collection of
`Pixel` objects with a `RectangleSize` attribute.

#### `RectangleSize`

A `RectangleSize` record has two `int` attributes:
`width` and `height`.

### `Pixel` class

Each pixel has two `int` attributes: a `PixelLocation` and a `Color`.

#### `PixelLocation`

A `PixelLocation` record has two `int` attributes: `x` and `y`.

#### `Color`

A `Color` object has three `int` attributes with values ranging from 0 to 255: *red*, *green*, and *blue*.

## Examples

### Gray Scale

To destructively convert a `Picture` named `picture`
to gray scale one could use the following nested loop:

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

This function will take a `Picture` object and return a new cropped `Picture`:
```java
public static Picture crop(Picture oldPicture, PixelLocation location,  RectagleSize newSize) {
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

A simplistic resize operation can be implemented as follows:

```java
public static Picture resize(Picture oldPicture, RectangleSize newSize) {
    final int oldHeight = oldPicture.getSize().height();
    final double heightRatio = oldHeight / (double) newSize.height();
    final int oldWidth = oldPicture.getSize().width();
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

## Week 2 Lab Assignment

As in Week 1, use IntelliJ to create a new project from version control.
This week, use the URL `https://github.com/paulbuis/CS121-Lab2.git`

