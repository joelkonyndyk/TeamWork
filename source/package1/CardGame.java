package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CardGame {
	// Where we will create the logic for the game

	private Deck deck;

	private HeartsGame hearts;

	public CardGame() {

	}

	public void init() {
		deck = new Deck();
		hearts = new HeartsGame(deck);
		hearts.init();

		// This makes the hearts game run
		hearts.setRunGame(true);
	}

	public void run() {
		if (hearts.isRunning()) {
			hearts.run();
		}
	}
	
	public void render(Graphics2D g, SpriteSheet sprites, JPanel panel){		
		if (hearts.isRunning()) {
//			hearts.run();
			hearts.render(g, sprites, panel);
		}
	}

//	public void draw(Graphics2D g, SpriteSheet sprites, JPanel panel) {
//		if (hearts.isRunning()) {
//			hearts.draw(g, sprites, panel);
//		}
//	}
}
