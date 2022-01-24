package cs121;

import java.io.IOException;

import cs121.picture.*;

public class PictureMaker {

    public static Picture flip(Picture picture) {

        int height = picture.getHeight();
        int width = picture.getWidth();
        Picture flipped = new Picture(width, height);

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
            Color oldColor = pixel.getColor();
            int grey = (oldColor.getRed() + oldColor.getGreen() + oldColor.getBlue()) / 3;
            pixel.setColor(new Color(grey, grey, grey));
        }
        return picture;
    }

    public static void main(String[] args) {
        try {
            final Picture picture = new Picture("media_sources/blueMotorcycle.jpg");

            final Picture flipped = flip(picture);
            final Picture grayScale = gray(flipped);

            final Picture shrunk = picture.resize(
                    picture.getWidth() / 2, picture.getHeight() / 2);
            final Picture combo = grayScale.paste(
                    picture.getWidth() / 4, picture.getHeight() / 4, shrunk);

            flipped.frame();
            shrunk.frame();
            combo.frame();

        } catch (IOException e) {
            System.err.println("OOPS!");
            e.printStackTrace();
        }

    }
}