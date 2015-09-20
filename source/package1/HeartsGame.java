package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class HeartsGame {
	// This is where we will create the Hearts game logic

	// fixed a bug here

	int x = 0;
	private boolean leadSuitPlayer = true;
	private boolean trumpPlayed = false;
	private boolean passingCards = true;
	private int roundNumber = 1;
	private int passingThreeCardsCounter = 0;
	private int[] cardsHaveBeenPassed = { -1, -1, -1 };

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

	private AnimationController anim;

	SpriteSheet spriteSheet;

	private boolean runGame = false;

	Font font;

	public HeartsGame(Deck deck) {
		this.deck = deck;
		spriteSheet = deck.getSpriteSheet();
	}

	public void init() {

		names = new RandomName();

		anim = new AnimationController();

		deck.ShuffleDeck();

		player = new Player(plyrName, deck);
		comp1 = new ComputerPlayer(names.getName(), deck, 1);
		comp2 = new ComputerPlayer(names.getName(), deck, 2);
		comp3 = new ComputerPlayer(names.getName(), deck, 3);

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
		spriteTest.setPosition(175, 175);

	}

	public void run() {

	}

	// move these to the bottom when finished with game logic
	public void checkCardAlreadyPassed(int z) {

		for (int i = 0; i < 3; i++) {
			if (cardsHaveBeenPassed[i] == z) {
				passingThreeCardsCounter--;
				i = 3;

			}

		}

	}

	public void addCardToAlreadyPassed(int z) {
		for (int i = 0; i < 3; i++) {
			if (cardsHaveBeenPassed[i] == -1 && cardsHaveBeenPassed[0] != z
					&& cardsHaveBeenPassed[1] != z
					&& cardsHaveBeenPassed[2] != z) {
				cardsHaveBeenPassed[i] = z;
				break;

			}

		}

	}

	public void twoOfClubsStarts() {
		for (int i = 0; i < 12; i++) {
			if (player.getHand()[i].getCardAndSuitNumber() == 6) {
				player.setTurn(true);
			} else if (comp1.getHand()[i].getCardAndSuitNumber() == 6) {
				comp1.setTurn(true);
			} else if (comp2.getHand()[i].getCardAndSuitNumber() == 6) {
				comp2.setTurn(true);
			}
			comp3.setTurn(true);
		}
	}

	public void tick() {

		// used for testing purposes. Keep in code for now
		if (mouseClicked) {

			if (spriteTest.getBounds().contains(pointClicked)) {

				// creates a new animation when the card is clicked
				anim.createAnimation(new Animation(anim, spriteTest, new Point(
						550, 300), 50));

			}
			mouseClicked = false;
		}

		// passingCards *select 3 cards* then turn off passing cards
		if (mouseClicked && passingCards == true) {

			for (int i = 0; i < player.getHand().length; i++) {

				if (player.getHand()[i].getSprite().getVisibleBounds(i)
						.contains(pointClicked)
						&& passingThreeCardsCounter < 3) {

					checkCardAlreadyPassed(player.getHand()[i]
							.getCardAndSuitNumber());
					addCardToAlreadyPassed(player.getHand()[i]
							.getCardAndSuitNumber());
					passingThreeCardsCounter++;

					System.out.println(cardsHaveBeenPassed[0]);
					System.out.println(cardsHaveBeenPassed[1]);
					System.out.println(cardsHaveBeenPassed[2]);

					System.out.println(passingThreeCardsCounter
							+ " Card Counter");
				}
			}

			if (passingThreeCardsCounter == 3) {
				passingThreeCardsCounter = 0;
				passingCards = false;
				System.out.println("Passing Cards Complete");

				// if (spriteTest.getBounds().contains(pointClicked)) {
				//
				// // creates a new animation when the card is clicked
				// anim.createAnimation(new Animation(anim, spriteTest,
				// new Point(550, 300), 50));
				//
				// // spriteTest.rotateImage90();
				// // spriteTest.setPosition(spriteTest.getX() + 10,
				// // spriteTest.getY());
				//
				// }

				mouseClicked = false;
				twoOfClubsStarts();
			}

			// Game play after cards have been passed
			if (mouseClicked && player.isTurn() == true
					&& passingCards == false) {

				for (int i = 0; i < player.getHand().length; i++) {
					if (player.getHand()[i].getSprite().getVisibleBounds(i)
							.contains(pointClicked)
							&& player.getHand()[i].getCardNumber() != -1) {

						System.out.println(player.getHand()[i].getCardNumber());
						System.out.println(player.isTurn());

						// *********How do I make the cards reprint when I
						// change
						// how the sprite looks?*******
						// comp1.getHand()[i].setShowBack(false);
						// comp1.getHand()[i].getSprite().setPosition(400, 175);
					}

				}

				mouseClicked = false;
				player.setTurn(false);

			}
		}
		anim.tick();

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

		// System.out.println(player.getHand()[2].getCardNumber());

		// System.out.println("");

		player.setHand(0, 1);
		player.setHand(1, 2);
		player.setHand(3, 2);
		player.setHand(4, 2);

		// System.out.println(player.getHand()[2].getCardNumber());
		// System.out.println(player.getHand()[2]);

	}

	// Getters and Setters
	public boolean isRunning() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}
}
