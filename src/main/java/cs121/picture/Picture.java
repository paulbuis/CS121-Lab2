package cs121.picture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;

import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class Picture implements Iterable<Pixel> {

    private final BufferedImage image;
    private final int width;
    private final int height;

    /**
     *
     * @param fileName
     * @throws IOException
     */
    public Picture(String fileName) throws IOException {
        this(ImageIO.read(new File(fileName)));
    }

    /**
     * Copy constructor
     *
     * @param picture
     */
    @SuppressWarnings("CopyConstructorMissesField")
    public Picture(Picture picture) {
        this(picture.image);
    }

    /**
     *
     * @param width
     * @param height
     */
    public Picture(int width, int height) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     *
     * @param size
     */
    public Picture(RectangleSize size) {
        this(size.width(), size.height()) ;
    }

    Picture(BufferedImage oldImage) {
        this(oldImage.getWidth(), oldImage.getHeight());
        Graphics g = image.getGraphics();
        g.drawImage(oldImage, 0, 0, null);
    }

    /**
     *
     * @return horizontal pixel count
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return vertical pixel count
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return horizontal and vertical pixel counts
     */
    public RectangleSize getSize() {
        return new RectangleSize(width, height);
    }

    // Package private, provides access to PictureShower class !!!

    /**
     *
     * @return
     */
    BufferedImage getImage() {
        return image;
    }

    /**
     *
     * @param x distance from left edge
     * @param y distance from top edge
     *
     * @return
     */
    public Pixel getPixel(int x, int y) {
        if (x < 0) {
            throw new IllegalArgumentException(
                    String.format("x=%d, must not be negative", x));
        }
        if (y < 0) {
            throw new IllegalArgumentException(
                    String.format("y=%d, must not be negative", y));
        }
        if (x >= width) {
            throw new IllegalArgumentException(
                    String.format("x=%d, width=%d, x must be less than width", x, width));
        }
        if (y >= height) {
            throw new IllegalArgumentException(
                    String.format("y=%d, height=%d, y must be less than height", y, height));
        }
        return new Pixel(x, y, image);
    }

    /**
     *
     * @param location
     * @return
     */
    public Pixel getPixel(PixelLocation location) {
        return new Pixel(location, image);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public Color getColor(int x, int y) {
        if (x < 0) {
            throw new IllegalArgumentException(
                    String.format("x=%d, must not be negative", x));
        }
        if (y < 0) {
            throw new IllegalArgumentException(
                    String.format("y=%d, must not be negative", y));
        }
        if (x >= width) {
            throw new IllegalArgumentException(
                    String.format("x=%d, width=%d, x must be less than width", x, width));
        }
        if (y >= height) {
            throw new IllegalArgumentException(
                    String.format("y=%d, height=%d, y must be less than height", y, height));
        }
        return getPixel(x, y).getColor();
    }

    /**
     *
     * @param location
     * @return
     */
    public Color getColor(PixelLocation location) {
        return getPixel(location).getColor();
    }

    /**
     *
     * @param x
     * @param y
     * @param color
     */
    public void setColor(int x, int y, Color color) {
        if (x < 0) {
            throw new IllegalArgumentException(
                    String.format("x=%d, must not be negative", x));
        }
        if (y < 0) {
            throw new IllegalArgumentException(
                    String.format("y=%d, must not be negative", y));
        }
        if (x >= width) {
            throw new IllegalArgumentException(
                    String.format("x=%d, width=%d, x must be less than width", x, width));
        }
        if (y >= height) {
            throw new IllegalArgumentException(
                    String.format("y=%d, height=%d, y must be less than height", y, height));
        }
        getPixel(x, y).setColor(color);
    }

    /**
     *
     * @param location
     * @param color
     */
    public void setColor(PixelLocation location, Color color) {
        getPixel(location).setColor(color);
    }

    /**
     *
     * @param newWidth
     * @param newHeight
     * @return
     */
    public Picture resize(int newWidth, int newHeight) {
        return transform(new Resizer(newWidth, newHeight));
    }

    /**
     *
     * @param size
     * @return
     */
    public Picture resize(RectangleSize size) {
        return transform(new Resizer(size));
    }

    /**
     *
     * @param x
     * @param y
     * @param picture
     * @return
     */
    public Picture paste(int x, int y, Picture picture) {
        return transform(new Paster(x, y, picture));
    }

    /**
     *
     * @param location
     * @param picture
     * @return
     */
    public Picture paste(PixelLocation location, Picture picture) {
        return paste(location.x(), location.y(), picture);
    }

    /**
     *
     * @param x
     * @param y
     * @param height
     * @param width
     * @return
     */
    public Picture crop(int x, int y, int width, int height) {
        return transform(new Cropper(x, y, width, height));
    }

    /**
     *
     * @param location
     * @param newSize
     * @return
     */
    public Picture crop(PixelLocation location, RectangleSize newSize) {
        return transform(new Cropper(location, newSize));
    }

    /**
     *
     * @return a PictureShower that will show this image
     */
    public PictureShower show() {
        return new PictureShower(this);
    }

    /**
     *
     */
    public void frame() {
        new PictureFrame(this);
    }

    /**
     *
     * @param transformer
     * @return
     */
    public Picture transform(PictureTransformer transformer) {
        return transformer.apply(new Picture(this));
    }

    /**
     *
     * @return
     */
    public Iterator<Pixel> iterator() {
        return new PixelIterator();
    }

    private final class PixelIterator implements Iterator<Pixel> {

        private PixelLocation location = new PixelLocation(0, 0);

        @Override
        public boolean hasNext() {
            return (location.x() < Picture.this.width) && (location.y() < Picture.this.height);
        }

        @Override
        public Pixel next() {
            Pixel nextPixel = new Pixel(location, Picture.this.image);
            if (location.x() + 1 < Picture.this.width) {
                location = new PixelLocation(location.x() + 1, location.y());
            } else if (location.y() + 1 < Picture.this.height) {
                location = new PixelLocation(0, location.y() + 1);
            } else {
                location = new PixelLocation(Picture.this.width, Picture.this.height);
            }

            return nextPixel;
        }
    }
}
