package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Stack;

public class HeartsGame {

	int x = 0;
	private boolean leadSuitPlayer = true;
	private boolean trumpPlayed = false;
	private boolean passingCards = true;
	private int roundNumber = 1;
	private int passingThreeCardsCounter = 0;
	private Card[] cardsHaveBeenPassed = { null, null, null };

	private boolean playerTurn = false;

	private RandomName names;
	private String plyrName = "JOEL";
	private Deck deck;
	private Player player;
	private ComputerPlayer comp1, comp2, comp3;

	private boolean mouseClicked = false;
	private Point pointClicked;

	private Card blankCard;

	private AnimationController anim;

	private StopWatch time = new StopWatch();

	private Random rand = new Random();

	SpriteSheet spriteSheet;

	private boolean runGame = false;

	private boolean compHandSelected = false;

	private boolean readyToPass = false;

	private boolean passLeft = false;
	private boolean passRight = false;
	private boolean passAcross = false;
	private boolean hold = false;

	private boolean arrowBtnEnabled = true;
	private boolean arrowBtnVisible = true;
	private boolean arrowBtnPressed = false;

	private boolean cardsInMiddle = false;
	private boolean cardClicked = false;

	private boolean drawMiddleCards = false;

	private BufferedImage ArrowButton = null;
	private BufferedImage ArrowButtonDisabled = null;

	private Sprite ArrowBtn;
	private Sprite ArrowBtnDisabled;

	Font font;

	private int cardsToPass = 0;

	public HeartsGame(Deck deck) {
		this.deck = deck;
		spriteSheet = deck.getSpriteSheet();
	}

	public void init() {

		passLeft = true;

		names = new RandomName();

		anim = new AnimationController();

		deck.ShuffleDeck();

		player = new Player(plyrName, deck);
		comp1 = new ComputerPlayer(names.getName(), deck, 1);
		comp2 = new ComputerPlayer(names.getName(), deck, 2);
		comp3 = new ComputerPlayer(names.getName(), deck, 3);

		comp1.setIsSideComp(true);
		comp2.setIsSideComp(false);
		comp3.setIsSideComp(true);

		player.setMidPoint(new Point(414, 280));
		comp1.setMidPoint(new Point(342, 230));
		comp2.setMidPoint(new Point(414, 180));
		comp3.setMidPoint(new Point(486, 230));

		comp1.setStackLocation(new Point(-100, 300));
		comp2.setStackLocation(new Point(450, -100));
		comp3.setStackLocation(new Point(1000, 300));

		// Makes sure the computers and the player never have the same name
		while (comp1.getName().equals(plyrName)) {
			comp1.setName(names.getName());
		}
		while (comp2.getName().equals(plyrName)
				|| comp2.getName().equals(comp1.getName())) {
			comp2.setName(names.getName());
		}
		while (comp3.getName().equals(plyrName)
				|| comp3.getName().equals(comp1.getName())
				|| comp3.getName().equals(comp2.getName())) {
			comp3.setName(names.getName());
		}

		initHands();

		font = new Font("SansSerif", Font.BOLD, 14);

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			ArrowButton = loader.loadImage("/arrow.png");
			ArrowButtonDisabled = loader.loadImage("/arrow_disabled.png");

		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrowButton = resize(ArrowButton, 60, 60);
		ArrowButtonDisabled = resize(ArrowButtonDisabled, 60, 60);

		ArrowBtn = new Sprite(ArrowButton);
		ArrowBtnDisabled = new Sprite(ArrowButtonDisabled);

		// Should switch location to be dependent on screen dimensions
		ArrowBtn.setPosition(420, 340);
		ArrowBtnDisabled.setPosition(420, 340);

		ArrowBtn.rotateImage(180);
		ArrowBtnDisabled.rotateImage(180);

	}

	public void initHands() {
		// Sets the location of the cards in the players hand
		int location = 100;
		int locationTop = 280;
		int locationSides = 70;

		player.sortHand();

		for (int i = 0; i < player.getHand().length; i++) {
			player.getHand()[i].getSprite().setPosition(location, 445);
			// player.getHand()[i].setShowBack(true);

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

		blankCard = new Card(-1, -1, -1, new Sprite(deck.getTile(64)));
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

			if (cardsHaveBeenPassed[i] == card) {
				passingThreeCardsCounter = i - 1;
				cardsHaveBeenPassed[i] = null;
				i = 3;
			}
		}
	}

	// finish this after switching up all the other methods

	// Card tempCardHolder;
	//
	// public void passLeft(){
	//
	// player.getHand()[1] = tempCardHolder;
	//
	// }

	// Checks to see who holds the 2 of clubs at the start of the game
	public void twoOfClubsStarts() {

		// System.out.println(player.getHand()[0].getSuit());
		// System.out.println(player.getHand()[0].getCardNumber());
		// System.out.println(player.getHand()[1].getSuit());
		// System.out.println(player.getHand()[1].getCardNumber());
		// System.out.println(player.getHand()[2].getSuit());
		// System.out.println(player.getHand()[2].getCardNumber());
		// System.out.println(player.getHand()[3].getSuit());
		// System.out.println(player.getHand()[3].getCardNumber());
		// System.out.println(player.getHand()[4].getSuit());
		// System.out.println(player.getHand()[4].getCardNumber());
		// System.out.println(player.getHand()[5].getSuit());
		// System.out.println(player.getHand()[5].getCardNumber());
		// System.out.println(player.getHand()[6].getSuit());
		// System.out.println(player.getHand()[6].getCardNumber());
		// System.out.println(player.getHand()[7].getSuit());
		// System.out.println(player.getHand()[7].getCardNumber());
		// System.out.println(player.getHand()[8].getSuit());
		// System.out.println(player.getHand()[8].getCardNumber());
		// System.out.println(player.getHand()[9].getSuit());
		// System.out.println(player.getHand()[9].getCardNumber());
		// System.out.println(player.getHand()[10].getSuit());
		// System.out.println(player.getHand()[10].getCardNumber());
		// System.out.println(player.getHand()[11].getSuit());
		// System.out.println(player.getHand()[11].getCardNumber());
		// System.out.println(player.getHand()[12].getSuit());
		// System.out.println(player.getHand()[12].getCardNumber());

		// for (int i = 0; i < 12; i++) {
		// System.out.println("Checking for 2 of Clubs");
		// if (player.getHand()[i].getSuitNumber() == 3 &&
		// player.getHand()[i].getCardNumber() == 2 ) {
		// player.setTurn(true);
		// System.out.println("P1");
		// } else if (comp1.getHand()[i].getSuitNumber() == 3 &&
		// comp1.getHand()[i].getCardNumber() == 2) {
		// comp1.setTurn(true);
		// System.out.println("C1");
		// } else if (comp2.getHand()[i].getSuitNumber() == 3 &&
		// comp1.getHand()[i].getCardNumber() == 2) {
		// comp2.setTurn(true);
		// System.out.println("C2");
		// } else if (comp3.getHand()[i].getSuitNumber() == 3 &&
		// comp1.getHand()[i].getCardNumber() == 2){
		// comp3.setTurn(true);
		// System.out.println("C3");
		//
		// }

		// }
	}

	public void tick() {

		if (mouseClicked) {

			if (playerTurn) {
				// this is the logic that sends cards to the middle when clicked
				for (int i = 0; i < player.getHand().length; i++) {
					if (player.getHand()[i].getSprite()
							.getVisibleBounds(i, player.getVisibleLength())
							.contains(pointClicked)) {
						int max = 12;
						int min = 0;

						int temp1 = rand.nextInt((max - min) + 1) + min;
						int temp2 = rand.nextInt((max - min) + 1) + min;
						int temp3 = rand.nextInt((max - min) + 1) + min;

						while (comp1.getHand()[temp1].getCardPlace() == -1) {
							temp1 = rand.nextInt((max - min) + 1) + min;
						}
						while (comp2.getHand()[temp2].getCardPlace() == -1) {
							temp2 = rand.nextInt((max - min) + 1) + min;
						}
						while (comp3.getHand()[temp3].getCardPlace() == -1) {
							temp3 = rand.nextInt((max - min) + 1) + min;
						}

						// sets the card clicked as the card sent to the middle
						player.setMiddleCard(player.getHand()[i]);
						player.getHand()[i] = blankCard;
						comp1.setMiddleCard(comp1.getHand()[temp1]);
						comp1.getHand()[temp1] = blankCard;
						comp2.setMiddleCard(comp2.getHand()[temp2]);
						comp2.getHand()[temp2] = blankCard;
						comp3.setMiddleCard(comp3.getHand()[temp3]);
						comp3.getHand()[temp3] = blankCard;

						// sets the animation speed of the cards
						int animSpeed = 35;

						// Animation that sends cards to the middle
						anim.createAnimation(new Animation(anim, player
								.getMiddleCard().getSprite(), player
								.getMidPoint(), animSpeed));
						anim.createAnimation(new Animation(anim, comp1
								.getMiddleCard().getSprite(), comp1
								.getMidPoint(), animSpeed));
						anim.createAnimation(new Animation(anim, comp2
								.getMiddleCard().getSprite(), comp2
								.getMidPoint(), animSpeed));
						anim.createAnimation(new Animation(anim, comp3
								.getMiddleCard().getSprite(), comp3
								.getMidPoint(), animSpeed));

						// shifts the hand to the left if needed
						player.UpdateHand(anim);
						comp1.UpdateHand(anim);
						comp2.UpdateHand(anim);
						comp3.UpdateHand(anim);

						cardClicked = true;
						playerTurn = false;
						drawMiddleCards = true;
					}
				}
			}

			if (arrowBtnVisible) {
				if (ArrowBtn.getBounds().contains(pointClicked) && readyToPass) {
					passCards();
					arrowBtnVisible = false;
					arrowBtnPressed = true;
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
						if (cardsToPass < 3) {
							if (player.getHand()[i].getSprite().getY() == 445) {
								anim.createAnimation(new Animation(anim, player
										.getHand()[i].getSprite(), new Point(
										Math.round(player.getHand()[i]
												.getSprite().getX()), 415), 10));
								cardsToPass++;
								player.getHand()[i].getSprite()
										.setSelectedToPass(true);
							}
						}
						if (Math.round(player.getHand()[i].getSprite().getY()) == 415) {
							anim.createAnimation(new Animation(anim, player
									.getHand()[i].getSprite(), new Point(Math
									.round(player.getHand()[i].getSprite()
											.getX()), 445), 10));
							cardsToPass--;
							player.getHand()[i].getSprite().setSelectedToPass(
									false);
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

						// checkCardAlreadyPassed(player.getHand()[i]);
						addCardToAlreadyPassed(player.getHand()[i]);
						passingThreeCardsCounter++;

						// System.out.println(cardsHaveBeenPassed[0]);
						// System.out.println(cardsHaveBeenPassed[1]);
						// System.out.println(cardsHaveBeenPassed[2]);
						//
						// System.out.println(passingThreeCardsCounter
						// + " Card Counter");
					}
				}

				if (passingThreeCardsCounter == 3) {
					passingThreeCardsCounter = 0;
					passingCards = false;
					// System.out.println("Passing Cards Complete");

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

		if (anim.isEmpty()) {
			// if there are no animations occurring and the player has sent
			// a card to the middle
			if (cardClicked) {
				cardsInMiddle = true;
				cardClicked = false;
			}

			// Makes sure players turn does not start until animation ends
			if (arrowBtnPressed) {
				arrowBtnPressed = false;
				playerTurn = true;
			}
		}

		if (cardsInMiddle) {
			if (!time.isRunning()) {
				time.start();
			}
			if (time.getElapsedTimeSecs() > .75) {

				// sets winner randomly for now
				Point p = player.getStackLocation();
				int temp = rand.nextInt((4 - 1) + 1) + 1;
				if (temp == 2) {
					p = comp1.getStackLocation();
				} else if (temp == 3) {
					p = comp2.getStackLocation();
				} else if (temp == 4) {
					p = comp3.getStackLocation();
				}

				// Create method to determine who won the hand and insert
				// here
				// Then set the Point p to the winners stackLocation
				// Point p = player.getStackLocation();

				int animSpeed = 30;

				if (anim.isEmpty()) {
					anim.createAnimation(new Animation(anim, player
							.getMiddleCard().getSprite(), p, animSpeed));
					anim.createAnimation(new Animation(anim, comp1
							.getMiddleCard().getSprite(), p, animSpeed));
					anim.createAnimation(new Animation(anim, comp2
							.getMiddleCard().getSprite(), p, animSpeed));
					anim.createAnimation(new Animation(anim, comp3
							.getMiddleCard().getSprite(), p, animSpeed));

					cardsInMiddle = false;
				}

				// Set middle cards to winners stack here
				// Sloppy, make this better when possible
				if (temp == 1) {
					player.addCardToStack(player.getMiddleCard());
					player.addCardToStack(comp1.getMiddleCard());
					player.addCardToStack(comp2.getMiddleCard());
					player.addCardToStack(comp3.getMiddleCard());
				} else if (temp == 2) {
					comp1.addCardToStack(player.getMiddleCard());
					comp1.addCardToStack(comp1.getMiddleCard());
					comp1.addCardToStack(comp2.getMiddleCard());
					comp1.addCardToStack(comp3.getMiddleCard());
				} else if (temp == 3) {
					comp2.addCardToStack(player.getMiddleCard());
					comp2.addCardToStack(comp1.getMiddleCard());
					comp2.addCardToStack(comp2.getMiddleCard());
					comp2.addCardToStack(comp3.getMiddleCard());
				} else if (temp == 4) {
					comp3.addCardToStack(player.getMiddleCard());
					comp3.addCardToStack(comp1.getMiddleCard());
					comp3.addCardToStack(comp2.getMiddleCard());
					comp3.addCardToStack(comp3.getMiddleCard());
				}

			}
		}
		if (time.getElapsedTimeSecs() > .75 && anim.isEmpty()) {
			time.stop();
			// set it to be winning players turn
			playerTurn = true;

			drawMiddleCards = false;
		}

		// Create if statement here to detect if it is the beginning of a
		// passing round
		// if it is select 3 cards from the computers hands and animate them
		// compHandSelected = true;
		if (!compHandSelected) {
			selectComputerCards();
			compHandSelected = true;
		}

		if (cardsToPass == 3) {
			readyToPass = true;
		} else {
			readyToPass = false;
		}

		anim.tick();

	}

	public void render(Graphics2D g) {

		g.setFont(font);

		// Draws the players names on the screen
		DrawOutline(player.getName(), 200, 410, g);
		DrawOutline(comp1.getName(), 30, 60, g);
		DrawOutline(comp2.getName(), 280, 20, g);
		DrawOutline(comp3.getName(), 780, 60, g);

		if (drawMiddleCards) {
			try {
				player.getMiddleCard().getSprite().paint(g);
				comp1.getMiddleCard().getSprite().paint(g);
				comp2.getMiddleCard().getSprite().paint(g);
				comp3.getMiddleCard().getSprite().paint(g);
			} catch (NullPointerException e) {

			}
		}

		for (int i = 0; i < player.getHand().length; i++) {
			player.getHand()[i].getSprite().paint(g);
			comp1.getHand()[i].getSprite().paint(g);
			comp2.getHand()[i].getSprite().paint(g);
			comp3.getHand()[i].getSprite().paint(g);
		}

		// Need to set up logic as to whether the pass button should be shown
		// Draw the pass button
		if (arrowBtnVisible) {
			if (readyToPass) {
				ArrowBtn.paint(g);
			} else {
				ArrowBtnDisabled.paint(g);
			}
		}
	}

	// currently implements the pass left only
	public void passCards() {

		// sets the animation speed of the passing cards
		int animSpeed = 50;

		int playerStk[] = new int[4];
		int comp1Stk[] = new int[4];
		int comp2Stk[] = new int[4];
		int comp3Stk[] = new int[4];

		int p = 1;
		int c1 = 1;
		int c2 = 1;
		int c3 = 1;

		Card temp1;
		Card temp2;
		Card temp3;

		// detects and notes where the selected cards to pass are
		for (int i = 0; i < player.getHand().length; i++) {
			if (player.getHand()[i].getSprite().isSelectedToPass()) {
				playerStk[p] = i;
				p++;
			}
			if (comp1.getHand()[i].getSprite().isSelectedToPass()) {
				comp1Stk[c1] = i;
				c1++;
			}
			if (comp2.getHand()[i].getSprite().isSelectedToPass()) {
				comp2Stk[c2] = i;
				c2++;
			}
			if (comp3.getHand()[i].getSprite().isSelectedToPass()) {
				comp3Stk[c3] = i;
				c3++;
			}
		}

		temp1 = comp3.getHand()[comp3Stk[1]];
		temp2 = comp3.getHand()[comp3Stk[2]];
		temp3 = comp3.getHand()[comp3Stk[3]];

		// Animates the cards between the computer players
		for (int count = 1; count < 4; count++) {
			anim.createAnimation(new Animation(anim,
					comp2.getHand()[comp2Stk[count]].getSprite(), new Point(
							comp3.getHand()[comp3Stk[count]].getSprite()
									.getLocation().x + 30,
							comp3.getHand()[comp3Stk[count]].getSprite()
									.getLocation().y), animSpeed));

			comp3.getHand()[comp3Stk[count]] = comp2.getHand()[comp2Stk[count]];

			anim.createAnimation(new Animation(anim,
					comp1.getHand()[comp1Stk[count]].getSprite(), new Point(
							comp2.getHand()[comp2Stk[count]].getSprite()
									.getLocation().x,
							comp2.getHand()[comp2Stk[count]].getSprite()
									.getLocation().y - 30), animSpeed));

			comp2.getHand()[comp2Stk[count]] = comp1.getHand()[comp1Stk[count]];

			anim.createAnimation(new Animation(anim,
					player.getHand()[playerStk[count]].getSprite(), new Point(
							comp1.getHand()[comp1Stk[count]].getSprite()
									.getLocation().x - 30,
							comp1.getHand()[comp1Stk[count]].getSprite()
									.getLocation().y), animSpeed));

			comp1.getHand()[comp1Stk[count]] = player.getHand()[playerStk[count]];

			// sets the moved cards to be not selected
			comp1.getHand()[comp1Stk[count]].getSprite().setSelectedToPass(
					false);
			comp2.getHand()[comp2Stk[count]].getSprite().setSelectedToPass(
					false);
			comp3.getHand()[comp3Stk[count]].getSprite().setSelectedToPass(
					false);
		}

		// moves the cards into the players hand from computer player 3
		player.getHand()[playerStk[1]] = temp1;
		player.getHand()[playerStk[2]] = temp2;
		player.getHand()[playerStk[3]] = temp3;

		// sets the moved cards to be not selected
		player.getHand()[playerStk[1]].getSprite().setSelectedToPass(false);
		player.getHand()[playerStk[2]].getSprite().setSelectedToPass(false);
		player.getHand()[playerStk[3]].getSprite().setSelectedToPass(false);

		// sorts and animates the cards to the correct position in the players
		// hand
		player.sortHand();
		for (int i = 0; i < player.getHand().length; i++) {
			if ((int) player.getHand()[i].getSprite().getX() != player
					.getCardPositionX(i)) {
				anim.createAnimation(new Animation(anim, player.getHand()[i]
						.getSprite(),
						new Point(player.getCardPositionX(i), 445), animSpeed));
			}
		}
	}

	// will have to switch from using random cards to using selected ones
	public void selectComputerCards() {

		int tempX = 60;
		int tempX1 = 750;
		int tempY = 60;

		for (int i = 0; i < 3; i++) {
			int max = 12;
			int min = 0;
			int randNum = rand.nextInt((max - min) + 1) + min;
			int randNum1 = rand.nextInt((max - min) + 1) + min;
			while (randNum == randNum1) {
				randNum1 = rand.nextInt((max - min) + 1) + min;
			}
			int randNum2 = rand.nextInt((max - min) + 1) + min;
			while (randNum2 == randNum || randNum2 == randNum1) {
				randNum2 = rand.nextInt((max - min) + 1) + min;
			}

			ComputerPlayer temp;
			temp = comp1;
			int x = tempX;
			int x1 = tempX;
			int x2 = tempX;
			int y = (int) temp.getHand()[randNum].getSprite().getY();
			int y1 = (int) temp.getHand()[randNum1].getSprite().getY();
			int y2 = (int) temp.getHand()[randNum2].getSprite().getY();
			if (i == 1) {
				temp = comp2;
				x = (int) temp.getHand()[randNum].getSprite().getX();
				x1 = (int) temp.getHand()[randNum1].getSprite().getX();
				x2 = (int) temp.getHand()[randNum2].getSprite().getX();
				y = tempY;
				y1 = tempY;
				y2 = tempY;
			} else if (i == 2) {
				temp = comp3;
				x = tempX1;
				x1 = tempX1;
				x2 = tempX1;
				y = (int) temp.getHand()[randNum].getSprite().getY();
				y1 = (int) temp.getHand()[randNum1].getSprite().getY();
				y2 = (int) temp.getHand()[randNum2].getSprite().getY();
			}

			// select 3 cards for the computer player
			anim.createAnimation(new Animation(anim, temp.getHand()[randNum]
					.getSprite(), new Point(x, y), 40));
			anim.createAnimation(new Animation(anim, temp.getHand()[randNum1]
					.getSprite(), new Point(x1, y1), 40));
			anim.createAnimation(new Animation(anim, temp.getHand()[randNum2]
					.getSprite(), new Point(x2, y2), 40));

			temp.getHand()[randNum].getSprite().setSelectedToPass(true);
			temp.getHand()[randNum1].getSprite().setSelectedToPass(true);
			temp.getHand()[randNum2].getSprite().setSelectedToPass(true);
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

	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
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
