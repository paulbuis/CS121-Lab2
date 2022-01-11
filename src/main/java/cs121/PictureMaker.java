package cs121;

import java.io.IOException;

import cs121.picture.*;

public class PictureMaker {

    public static Picture flip(Picture picture) {
        final int height = picture.getHeight();
        final int width = picture.getWidth();
        final Picture flipped = new Picture(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int newX = width - x - 1;
                int newY = height - y - 1;
                flipped.setColor(newX, newY, picture.getColor(x, y));
            }
        }
        return flipped;
    }

    public static Picture gray(Picture picture) {
        for (Pixel pixel : picture) {
            final Color oldColor = pixel.getColor();
            final int grey = (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
            pixel.setColor(new Color(grey, grey, grey));
        }
        return picture;
    }

    public static void main(String[] args) {
        try {
            final Picture picture = new Picture("media_sources/blueMotorcycle.jpg");

            final Picture flipped = flip(picture);
            final Picture grayScale = gray(flipped);

            final Picture shrunk = picture.resize(picture.getWidth() / 2, picture.getHeight() / 2);
            final Picture combo = grayScale.paste(picture.getWidth() / 4, picture.getHeight() / 4, shrunk);

            combo.frame();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}