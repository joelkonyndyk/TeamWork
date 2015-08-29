package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class HeartsGame {
	// This is where we will create the Hearts game logic

	private RandomName names;
	private String plyrName;
	private Deck deck;
	private Player player;
	private ComputerPlayer comp1, comp2, comp3;

	private boolean mouseClicked = false;
	private Point pointClicked;

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

	public void tick(SpriteSheet sprites) {

		// Sprite tempSprite;
		//
		// if (mouseClicked) {
		//
		// for (int i = 0; i < player.getHand().length; i++) {
		// tempSprite = new Sprite(sprites.getTile(player.getHand()[i]
		// .getCardPlace()));
		//
		// if (tempSprite.getBounds().contains(pointClicked)) {
		// // sprites.getTile(player.getHand()[i]
		// // .getCardPlace())
		// // tempSprite.rotateImage90();
		// // sprite.paint(g);
		// }
		// mouseClicked = false;
		//
		// }
		// }

	}

	public void render(Graphics2D g, SpriteSheet sprites, JPanel panel) {

		// Draws single test card on the screen
		sprite = new Sprite(sprites.getTile(player.getHand()[1].getCardPlace()));
		sprite.setPosition(200, 200);
		
		if (mouseClicked) {
			if (sprite.getBounds().contains(pointClicked)) {
				System.out.println("Clicked");
				sprite.rotateImage90();
				// sprite.paint(g);
			}
			mouseClicked = false;
		}
		
		// sprite.rotateImage90();
		sprite.paint(g);

		int location = 50;

		for (int i = 0; i < player.getHand().length; i++) {
			sprite = new Sprite(sprites.getTile(player.getHand()[i]
					.getCardPlace()));
			sprite.setPosition(location, 400);
			// sprite.paint(g);

//			if (mouseClicked) {
//				if (sprite.getBounds().contains(pointClicked)) {
//					System.out.println("Clicked");
//					sprite.rotateImage90();
//					// sprite.paint(g);
//				}
//				mouseClicked = false;
//			}

			sprite.paint(g);

			// g.drawImage(sprites.getTile(player.getHand()[i].getCardPlace()),
			// location, 400, panel);

			location += 50;
		}

		// if (mouseClicked) {
		// if (sprite.getBounds().contains(pointClicked)) {
		// sprite.rotateImage90();
		// sprite.paint(g);
		// }
		// mouseClicked = false;
		// }
	}

	public void mouseClicked(Point p) {
		mouseClicked = true;
		pointClicked = p;
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
