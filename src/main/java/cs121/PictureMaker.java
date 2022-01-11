package cs121;

import java.io.IOException;

import cs121.picture.*;

public class PictureMaker {
    public static void main(String[] args) {
        try {
            Picture picture = new Picture("mediasources/blueMotorcycle.jpg");

            Picture shrunk = picture.resize(picture.getWidth() / 2, picture.getHeight() / 2);

            PictureTransformer flipper = new PictureTransformer() {
                public Picture apply(Picture oldPicture) {
                    final int height = oldPicture.getHeight();
                    final int width = oldPicture.getWidth();
                    final Picture newPicture = new Picture(width, height);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            int newX = width - x - 1;
                            int newY = height - y - 1;
                            newPicture.setColor(newX, newY, oldPicture.getColor(x, y));
                        }
                    }
                    return newPicture;
                }
            };

            Picture flipped = picture.transform(flipper);
            Picture grayScale = flipped.transform(new GreyScaler());

            Picture combo = grayScale.paste(picture.getWidth() / 4, picture.getHeight() / 4, shrunk);

            combo.frame();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}