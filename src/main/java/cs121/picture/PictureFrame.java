package cs121.picture;

import javax.swing.JFrame;
import java.io.Serial;

public class PictureFrame extends JFrame {

	@Serial
	private static final long serialVersionUID = -5965061700815416688L;

	public PictureFrame(Picture picture) {
		PictureShower ps = new PictureShower(picture);
		getContentPane().add(ps);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
}
