package cs121.picture;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JComponent;

public class PictureShower extends JComponent {
	
	@Serial
    private static final long serialVersionUID = 2747203591654717654L;
	private final Picture picture;
	
	public PictureShower(Picture picture) {
		this.picture = picture;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(picture.getImage(), 0, 0, null);
    }
 
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(picture.getWidth(), picture.getHeight());
    }
}
