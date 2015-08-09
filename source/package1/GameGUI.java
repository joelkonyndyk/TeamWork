package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameGUI extends JPanel {

	SpriteSheet sprites = new SpriteSheet();
	
	public void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		//need to fix this... loading every time i run the doMethod... inefficient
		sprites.loadImage();
		sprites.storeImages();
		
		g2d.drawImage(sprites.getTile(6), 50, 50, this);
		g2d.drawImage(sprites.getTile(7), 150, 50, this);
		g2d.drawImage(sprites.getTile(60), 250, 50, this);
		repaint();

	}

	//do we need to override this?
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}
	

