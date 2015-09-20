package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class HeartsGame {
	// This is where we will create the Hearts game logic

	private boolean leadSuitPlayer = true;
	private boolean trumpPlayed = false;

	private boolean passingCards = false;
	private int roundNumber = 1;

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

	private Animation anim;

	SpriteSheet spriteSheet;

	private boolean runGame = false;

	Font font;

	public HeartsGame(Deck deck) {
		this.deck = deck;
		spriteSheet = deck.getSpriteSheet();
	}

	public void init() {

		names = new RandomName();

		// anim = new Animation();

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

		// long startingTime = System.currentTimeMillis();
		// long cumTime = startingTime;
		//
		// while(cumTime - startingTime < 5000) {
		// long timePassed = System.currentTimeMillis() - cumTime;
		// cumTime += timePassed;
		// update(timePassed);

		if (mouseClicked) {

			for (int i = 0; i < player.getHand().length; i++) {
				if (player.getHand()[i].getSprite().getBounds()
						.contains(pointClicked)) {
					player.getHand()[i].getSprite().rotateImage90();
					player.getHand()[i].getSprite().setPosition(
							player.getHand()[i].getSprite().getX() + 10,
							player.getHand()[i].getSprite().getY());
				}
			}

			if (spriteTest.getBounds().contains(pointClicked)) {
				
				float i = ((float)32/7);
				System.out.println(i);

				// anim.addScene(spriteTest, 50);

				// spriteTest.rotateImage90();
				// spriteTest.setPosition(spriteTest.getX() + 10,
				// spriteTest.getY());
			}
			mouseClicked = false;
		}

		// anim.update(timePassed);

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

		// playing with drawing new cards

//		System.out.println(player.getHand()[2].getCardNumber());

//		System.out.println("");

		player.setHand(0, 1);
		player.setHand(1, 2);
		player.setHand(3, 2);
		player.setHand(4, 2);

//		System.out.println(player.getHand()[2].getCardNumber());
//		System.out.println(player.getHand()[2]);

	}

	// ////////////////////// Game Logic and rules //////////////////////////

	// Pass three cards @ beg. of each round

	// //////////////////////////////////////////////////////////////////////////////////

	// Getters and Setters
	public boolean isRunning() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}
}
