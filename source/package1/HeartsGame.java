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
	private Card[] cardsHaveBeenPassed = { null, null, null };

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
	private Sprite spriteTest1;

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
		int locationSides = 70;

		player.sortHand();

		for (int i = 0; i < player.getHand().length; i++) {
			player.getHand()[i].getSprite().setPosition(location, 445);
			// player.getHand()[i].setShowBack(false);

			comp2.getHand()[i].getSprite().setPosition(locationTop, 30);
			comp1.getHand()[i].getSprite().setPosition(30, locationSides);
			comp3.getHand()[i].getSprite().setPosition(780, locationSides);

			// comp1.getHand()[i].setShowBack(true);
			// comp2.getHand()[i].setShowBack(true);
			// comp3.getHand()[i].setShowBack(true);

			location += 50;
			locationTop += 20;
			locationSides += 20;
		}

		// Draws the test card on the screen
		spriteTest = deck.getCard(0).getSprite().clone();
		spriteTest.setPosition(175, 175);
		spriteTest1 = deck.getCard(1).getSprite().clone();
		spriteTest1.setPosition(200, 200);

	}

	public void run() {

	}

	public void addCardToAlreadyPassed(Card card) {
		for (int i = 0; i < 3; i++) {
			if (cardsHaveBeenPassed[i] == null
					&& cardsHaveBeenPassed[0] != card
					&& cardsHaveBeenPassed[1] != card
					&& cardsHaveBeenPassed[2] != card) {
				cardsHaveBeenPassed[i] = card;
				break;

			}

			if(cardsHaveBeenPassed[i] == card){
				passingThreeCardsCounter = i-1;
				cardsHaveBeenPassed[i] = null;	
				i=3;
			}
		}

	}

	//commented out pass left cards b/c this currently isnt working
	
	//testing passing cards: only grabbing comp1's 3rd, 5th, and 7th card
//	public void passLeft() {
//		
//		for (int i = 0; i < 12; i++)
//			if (player.getHand()[i] == cardsHaveBeenPassed[0]) {
//				player.setHand(comp1.getHand()[3], i);
//				
//			} else if (player.getHand()[i] == cardsHaveBeenPassed[1]) {
//				player.setHand(comp1.getHand()[5], i);
//				
//			} else if (player.getHand()[i] == cardsHaveBeenPassed[2]) {
//				player.setHand(comp1.getHand()[7], i);
//				
//			}
//		
//		
//		//used 1 as the starting point to get the correct positions for the cards...
//		if(true){
//			for(int i = 0; i < 12; i++)
//			player.getHand()[i].getSprite().setPosition(100 + 50 * i, 445);
//		}
//	
//	
//	}

	// Checks to see who holds the 2 of clubs at the start of the game
	public void twoOfClubsStarts() {


		for (int i = 0; i < 12; i++) {
			
			if (player.getHand()[i].getSuitNumber() == 2
					&& player.getHand()[i].getCardNumber() == 2) {
				player.setTurn(true);
				System.out.println("P1");
				break;
			} else if (comp1.getHand()[i].getSuitNumber() == 2
					&& comp1.getHand()[i].getCardNumber() == 2) {
				comp1.setTurn(true);
				System.out.println("C1");
				break;
			} else if (comp2.getHand()[i].getSuitNumber() == 2
					&& comp2.getHand()[i].getCardNumber() == 2) {
				comp2.setTurn(true);
				System.out.println("C2");
				break;
			} else if (comp3.getHand()[i].getSuitNumber() == 2
					&& comp3.getHand()[i].getCardNumber() == 2) {
				comp3.setTurn(true);
				System.out.println("C3");
				break;

		 }

		 }
	}

	public void tick() {

		// used for testing purposes. Keep in code for now
		if (mouseClicked) {

			if (spriteTest.getBounds().contains(pointClicked)) {
				if (!spriteTest1.getBounds().contains(pointClicked)) {
					// creates a new animation when the card is clicked
					if (spriteTest.getX() == 175 && spriteTest.getY() == 175) {
						anim.createAnimation(new Animation(anim, spriteTest,
								new Point(550, 300), 100));
					} else if (spriteTest.getX() == 550
							&& spriteTest.getY() == 300) {
						anim.createAnimation(new Animation(anim, spriteTest,
								new Point(175, 175), 50));
					}
				}
			}
			if (spriteTest1.getBounds().contains(pointClicked)) {
				// creates a new animation when the card is clicked
				if (spriteTest1.getX() == 200 && spriteTest1.getY() == 200) {
					anim.createAnimation(new Animation(anim, spriteTest1,
							new Point(575, 325), 100));
				} else if (spriteTest1.getX() == 575
						&& spriteTest1.getY() == 325) {
					anim.createAnimation(new Animation(anim, spriteTest1,
							new Point(200, 200), 50));
				}
			}

			// This for loop allows the player to animate the cards in their
			// hand
			for (int i = 0; i < player.getHand().length; i++) {

				// Makes click only apply to the visible part of the card
				// clicked
				if (player.getHand()[i].getSprite().getBounds()
						.contains(pointClicked)) {
					if (i + 1 < player.getHand().length
							&& player.getHand()[i + 1].getSprite().getBounds()
									.contains(pointClicked)) {
					} else {
						// Animates the cards up and down accordingly
						if (player.getHand()[i].getSprite().getY() == 445) {
							anim.createAnimation(new Animation(anim, player
									.getHand()[i].getSprite(), new Point(Math
									.round(player.getHand()[i].getSprite()
											.getX()), 415), 10));
						} else if (Math.round(player.getHand()[i].getSprite()
								.getY()) == 415) {
							anim.createAnimation(new Animation(anim, player
									.getHand()[i].getSprite(), new Point(Math
									.round(player.getHand()[i].getSprite()
											.getX()), 445), 10));
						}
					}
				}
			}

			// passingCards *select 3 cards* then turn off passing cards
			if (passingCards) {

				for (int i = 0; i < player.getHand().length; i++) {

					if (player.getHand()[i].getSprite().getVisibleBounds(i)
							.contains(pointClicked)
							&& passingThreeCardsCounter < 3) {

						addCardToAlreadyPassed(player.getHand()[i]);
						passingThreeCardsCounter++;

						 System.out.println(cardsHaveBeenPassed[0]);
						 System.out.println(cardsHaveBeenPassed[1]);
						 System.out.println(cardsHaveBeenPassed[2]);
						
						 System.out.println(passingThreeCardsCounter
						 + " Card Counter");
					}
					
					//this is currently making the cards disappear
					
//					passLeft();
					
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
			}

			// Game play after cards have been passed
			if (player.isTurn() && !passingCards) {

				// for (int i = 0; i < player.getHand().length; i++) {
				// if (player.getHand()[i].getSprite().getVisibleBounds(i)
				// .contains(pointClicked)
				// && player.getHand()[i].getCardNumber() != -1) {
				//
				// System.out.println(player.getHand()[i].getCardNumber());
				// System.out.println(player.isTurn());
				//
				// // *********How do I make the cards reprint when I
				// // change
				// // how the sprite looks?*******
				// // comp1.getHand()[i].setShowBack(false);
				// // comp1.getHand()[i].getSprite().setPosition(400, 175);
				// }
				//
				// }
				//
				// mouseClicked = false;
				// player.setTurn(false);

			}

			mouseClicked = false;
		}

		anim.tick();

	}

	public void render(Graphics2D g) {

		g.setFont(font);

		// Draws the players names on the screen
		DrawOutline(player.getName(), 280, 410, g);
		DrawOutline(comp1.getName(), 30, 60, g);
		DrawOutline(comp2.getName(), 280, 20, g);
		DrawOutline(comp3.getName(), 780, 60, g);

		// Draws the test cards on the screen
		spriteTest.paint(g);
		spriteTest1.paint(g);

		// spriteTest.getImage()

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

	}

	// Getters and Setters
	public boolean isRunning() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}
}
