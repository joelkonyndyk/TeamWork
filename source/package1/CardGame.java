package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

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

		// This makes the hearts game run. Will have to switch this when there
		// are multiple game options
		hearts.setRunGame(true);
	}

	public void run() {
		if (hearts.isRunning()) {
			hearts.run();
		}
	}

	public void tick() {
		if (hearts.isRunning()) {
			hearts.tick();
		}
	}

	public void render(Graphics2D g) {
		if (hearts.isRunning()) {
			hearts.render(g);
		}

	}

	public void mouseClicked(Point p) {
		if (hearts.isRunning()) {
			hearts.mouseClicked(p);
		}
	}
}
