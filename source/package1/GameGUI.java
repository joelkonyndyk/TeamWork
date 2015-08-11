package package1;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

	// public void doDrawing(Graphics g) {
	// Graphics2D g2d = (Graphics2D) g;
	// game.draw(g2d, sprites, this);
	//
	// repaint();
	// }

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		game.render(g2d, sprites, this);

//		repaint();
	}

	public void tick() {

	}

	public void run() {

	}

	// do we need to override this?
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
		// doDrawing(g);
	}

}
