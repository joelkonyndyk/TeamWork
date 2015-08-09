package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameGUI extends JPanel {

	private SpriteSheet sprites = new SpriteSheet();
	private CardGame game;

	public GameGUI(CardGame game) {

		this.game = game;

		sprites.loadImage();
		sprites.storeImages();

	}

	public void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		game.draw(g2d, sprites, this);

		repaint();
	}

	// do we need to override this?
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}
