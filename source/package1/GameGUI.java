package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class GameGUI extends JPanel {

	private SpriteSheet sprites = new SpriteSheet();
	private CardGame game;

	public GameGUI(CardGame game) {

		this.game = game;

		sprites.loadImage();
		sprites.storeImages();

	}

	public void init() {

	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		game.render(g2d);
	}

	public void tick() {
		game.tick();
	}

	public void run() {

	}

	public void mouseClicked(Point p) {
		game.mouseClicked(p);
	}

	// do we need to override this?
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
	}

}
