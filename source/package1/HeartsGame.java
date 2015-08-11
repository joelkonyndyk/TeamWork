package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class HeartsGame {
	// This is where we will create the Hearts game logic

	private RandomName names;
	private String plyrName;
	private Deck deck;
	private Player player;
	private ComputerPlayer comp1, comp2, comp3;

	Sprite sprite;

	private boolean runGame = false;

	public HeartsGame(Deck deck) {
		this.deck = deck;
	}

	public void init() {
		names = new RandomName();

		deck.ShuffleDeck();

		player = new Player(plyrName, deck);
		comp1 = new ComputerPlayer(names.getName(), deck, 1);
		comp2 = new ComputerPlayer(names.getName(), deck, 2);
		comp3 = new ComputerPlayer(names.getName(), deck, 3);

		player.setTurn(true);
	}

	public void run() {

	}

	public void render(Graphics2D g, SpriteSheet sprites, JPanel panel) {
		sprite = new Sprite(sprites.getTile(player.getHand()[1].getCardPlace()));
		sprite.setPosition(200, 200);
		sprite.paint(g);

		int location = 50;

		for (int i = 0; i < player.getHand().length; i++) {
			sprite = new Sprite(sprites.getTile(player.getHand()[i]
					.getCardPlace()));
			sprite.setPosition(location, 400);
			sprite.paint(g);

			// g.drawImage(sprites.getTile(player.getHand()[i].getCardPlace()),
			// location, 400, panel);

			location += 50;
		}
	}

	// public void draw(Graphics2D g, SpriteSheet sprites, JPanel panel) {
	//
	// sprite = new Sprite(sprites.getTile(player.getHand()[1].getCardPlace()));
	// sprite.setPosition(200, 200);
	// sprite.paint(g);
	//
	// int location = 50;
	//
	// for (int i = 0; i < player.getHand().length; i++) {
	// sprite = new Sprite(sprites.getTile(player.getHand()[i]
	// .getCardPlace()));
	// sprite.setPosition(location, 400);
	// sprite.paint(g);
	//
	// // g.drawImage(sprites.getTile(player.getHand()[i].getCardPlace()),
	// // location, 400, panel);
	//
	// location += 50;
	// }
	// }

	// Getters and Setters
	public boolean isRunning() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}
}
