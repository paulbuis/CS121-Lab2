# Lab 2 solutions

## Starting Point

I should have given you a starting point that
was simpler than the code in `PictureMaker.java`.

For example:
```java
package cs121;

import cs121.picture.*;
import java.io.IOException;

public class Motorcycle {

    public static void main(String[] args) {
        try {
           
            Picture picture = new Picture("media_sources/blueMotorcycle.jpg");
            Picture copiedPicture = new Picture(picture.getWidth(), oldPicture.getHeight());

            for (Pixel pixel : picture) {
                int x = pixel.getX();
                int y = pixel.getY();
                Pixel copiedPixel = copiedPicture.getPixel(x, y);
                copiedPixel.setColor(x, y, pixel.getColor());
            }

            copiedPicture.frame();

        } catch (IOException e) {
            System.err.println("OOPS!");
            e.printStackTrace();
        }

    }
}
```

## Rotate by Interchanging

If we copy-paste the above code, we can modify it
to look more like the Python JES code

```python
picture = makePicture("media_sources/blue_motorcycle.jpg")
rotated = makeEmptyPicture(picture.getHeight(), picture.getWidth())
for pixel in picture.getPixels():
  x = pixel.getX()
  y = pixel.getY()
  rotatedPixel = rotated.getPixel(y, x)
  rotatedPixel.setColor(pixel.getColor())

rotated.show()
```


```java
package cs121;

import cs121.picture.*;
import java.io.IOException;

public class InterchangeXY {

    public static void main(String[] args) {
        try {
           
            Picture picture = new Picture("media_sources/blueMotorcycle.jpg");
            Picture rotated = new Picture(picture.getHeight(), oldPicture.getWidth());

            for (Pixel pixel : picture) {
                int x = pixel.getX();
                int y = pixel.getY();
                Pixel rotatedPixel = copiedPicture.getPixel(y, x);
                rotatedPixel.setColor(x, y, pixel.getColor());
            }

            rotatedPicture.frame();

        } catch (IOException e) {
            System.err.println("OOPS!");
            e.printStackTrace();
        }

    }
}
```

Note that:

* every time a new variable is introduced, it is preceded by the type of the variable.

* every statement ends with a semicolon.

* a block of statements is contained in a set of curly braces.

* the clause following the keyword `for`
  * is surrounded by parentheses instead of ending with a `:`
  * uses a `:` instead of the keyword `in`

* the code that gets executed is found in the body 
  of a function/method named `main`. Every Java program
  must have a class with a method with the *signature* 
  `public static void main(String[] args)`.

## Replacing Red with Moon

Again, we can copy-paste the code that makes
a copy and then make it look like the Python JES
code:

```python
def reddish(color):
  red = makeColor(200, 0, 0)
  d = distance(red, color)
  return (d < 100)

moon = makePicture("media_sources/moon-surface.jpg")
red = makePicture("media_sources/red.jpg")
for pixel in red.getPixels():
    color = pixel.getColor()
    if reddish(color):
      moonPixel = moon.getPixel(pixel.getX(), pixel.getY())
      pixel.setColor(moonPixel.getColor())
      
red.show()
```

In Java, this looks like:

```java
package cs121;

import cs121.picture.*;
import java.io.IOException;

public class RedScreenMoon {

    // def reddish(color):
    public static boolean reddish(Color color) {
        // JES: red = makeColor(200, 0, 0)
        Color red = new Color(200, 0, 0);
        // JES: d = distance(red, color)
        double d = red.distance(color);
        //JES: return d < 100
        return (d < 100);
    }

    public static void main(String[] args) {
        try {

            // JES: moon = makePicture("media_sources/moon-surface.jpg")
            Picture moon = new Picture("media_sources/moon-surface.jpg");
            // JES: red = makePicture("media_sources/red.jpg")
            Picture red = new Picture("media_sources/red.jpg");
            // JES: for pixel in red.getPixels():
            for (Pixel pixel : red) {
                // JES: color = pixel.getColor()
                Color color = pixel.getColor();
                // JES: if reddish(color):
                if (reddish(color)) {
                    // JES: moonPixel = moon.getPixel(pixel.getX(), pixel.getY())
                    Pixel moonPixel = moon.getPixel(pixel.getX(), pixel.getY());
                    // JES: pixel.setColor(moonPixel.getColor())
                    pixel.setColor(moonPixel.getColor());
                }
            }

            red.frame();

        } catch (IOException e) {
            System.err.println("OOPS!");
            e.printStackTrace();
        }

    }
}
```

Here, note:

* Like with the `for`, the Boolean expression
  following the `if` is enclosed in parentheses
  rather than being terminated by a colon (`:`).
* Again, note there are a pair of curly braces (`{}`)
  surrounding the block of statements that are conditionally
  executed after the line containing the keyword `if`.
* Technically, if the block of statements consists of
  a single statement, the curly braces may be omitted, but
  I recommend using curly braces always to be clearer and just
  in case at a later point in time you want to add another statement.
* Note the `public static boolean` method. These words mean

   * `public` means that the method/function can be called from another class. To do this
      it would need to be prefixed by the name of the class that
      contains it. In this case, `reddish` could be invoked from another class
      by calling `RedScreenMoon.reddish`.
   * `static` means that the method/function is not associated
      with a particular object of type `RedScreenMoon`, but is instead
      merely contained in the object named `Class<RedScreenMoon>` that
      would be responsible for creating instances of such objects.
   * `boolean` means that the value returned by the method/function
      will be of type `boolean`.

* Again, note that in Python the code that will be initially executed is *not*
  located inside a function, but in Java, the code that is initially executed
  is inside `main()`. A Java program can have many classes and more than one
  may have a `main()` method. To start a Java program, one must say which `main`
  should be used as a starting point for execution.