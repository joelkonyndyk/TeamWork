package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Scanner;

public class HeartsGame {
	// This is where we will create the Hearts game logic

	boolean leadSuitPlayer = true;
	boolean trumpPlayed = false;
	boolean passingCards = false;
	int roundNumber = 1;
	int x = 0;
	
	private RandomName names;
	private String plyrName = "JOEL";
	private Deck deck;
	private Player player;
	private ComputerPlayer comp1, comp2, comp3;

	private boolean mouseClicked = false;
	private Point pointClicked;

	// private Sprite sprite;
	// private Sprite cardBack;
	private Sprite spriteTest;

	SpriteSheet spriteSheet;

	private boolean runGame = false;

	Font font;

	public HeartsGame(Deck deck) {
		this.deck = deck;
		spriteSheet = deck.getSpriteSheet();
	}

	public void init() {

		names = new RandomName();

		deck.ShuffleDeck();

		player = new Player(plyrName, deck);
		comp1 = new ComputerPlayer(names.getName(), deck, 1);
		comp2 = new ComputerPlayer(names.getName(), deck, 2);
		comp3 = new ComputerPlayer(names.getName(), deck, 3);

		player.setTurn(true);

		initHands();

		font = new Font("SansSerif", Font.BOLD, 14);

	}

	public void initHands() {
		// Sets the location of the cards in the players hand
		int location = 100;
		int locationTop = 280;
		int locationSides = 80;

		player.sortHand();

		for (int i = 0; i < player.getHand().length; i++) {
			player.getHand()[i].getSprite().setPosition(location, 445);
			// player.getHand()[i].setShowBack(false);

			comp2.getHand()[i].getSprite().setPosition(locationTop, 30);
			comp1.getHand()[i].getSprite().setPosition(30, locationSides);
			comp3.getHand()[i].getSprite().setPosition(780, locationSides);

			comp1.getHand()[i].setShowBack(true);
			comp2.getHand()[i].setShowBack(true);
			comp3.getHand()[i].setShowBack(true);

			location += 50;
			locationTop += 20;
			locationSides += 20;
		}

		// Draws the test card on the screen
		spriteTest = deck.getCard(0).getSprite().clone();
		spriteTest.setPosition(200, 200);
	}

	public void run() {

	}

	public void tick() {

		if (mouseClicked) {
			if (spriteTest.getBounds().contains(pointClicked)) {
				spriteTest.rotateImage90();
				spriteTest.setPosition(spriteTest.getX() + 10,
						spriteTest.getY());
			}
			mouseClicked = false;
		}

	}

	public void render(Graphics2D g) {

		g.setFont(font);

		// Draws the players names on the screen
		DrawOutline(player.getName(), 280, 410, g);
		DrawOutline(comp1.getName(), 30, 70, g);
		DrawOutline(comp2.getName(), 280, 20, g);
		DrawOutline(comp3.getName(), 780, 70, g);

		spriteTest.paint(g);

		for (int i = 0; i < player.getHand().length; i++) {
			player.getHand()[i].getSprite().paint(g);
			comp1.getHand()[i].getSprite().paint(g);
			comp2.getHand()[i].getSprite().paint(g);
			comp3.getHand()[i].getSprite().paint(g);
		}
	}

	public void DrawOutline(String str, int x, int y, Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawString(str, x - 1, y);
		g2d.drawString(str, x + 1, y);
		g2d.drawString(str, x, y - 1);
		g2d.drawString(str, x, y + 1);

		g2d.setColor(Color.WHITE);
		g2d.drawString(str, x, y);
	}

	public void mouseClicked(Point p) {
		mouseClicked = true;
		pointClicked = p;

		
//playing with drawing new cards
		
		player.getHand()[0].getSprite().setPosition(player.getP1CardLocX()[1], 445/2);
		
		//card place Bottom
		player.getHand()[1].getSprite().setPosition(400, 300);
		//card place TOP
		player.getHand()[2].getSprite().setPosition(400, 175);
		
		
	}

//////////////////////// Game Logic and rules //////////////////////////
		
	// Pass three cards @ beg. of each round

////////////////////////////////////////////////////////////////////////////////////

	// Getters and Setters
	public boolean isRunning() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}
}
