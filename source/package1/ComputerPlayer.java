package package1;

import java.awt.Point;
import java.util.Stack;

public class ComputerPlayer {

	private String compName;
	private Deck deck;

	private Point midPoint;
	private Point stackLocation;

	private Card[] hand;

	private Card middleCard;

	private Stack<Card> cards = new Stack<Card>();

	private boolean isTurn = false;

	private boolean isSideComp;

	private int deckSection;
	private int startCard;

	private final int posBaseX = 280;
	private final int posBaseY = 70;	

	private final int handSize = 13;

	public ComputerPlayer(String name, Deck deck, int deckSection) {
		compName = name;
		this.deck = deck;
		this.deckSection = deckSection;
		init();
	}

	// Initialize
	public void init() {
		hand = new Card[handSize];

		if (deckSection == 1) {
			startCard = 0;
		} else if (deckSection == 2) {
			startCard = 13;
		} else if (deckSection == 3) {
			startCard = 26;
		}

		for (int i = 0; i < handSize; i++) {
			hand[i] = deck.getCard(startCard);
			startCard++;
		}
	}

	public void UpdateHand(AnimationController anim) {
		
		int animSpeed = 25;
		
		boolean swapped = true;
		Card tmp;

		while (swapped) {
			swapped = false;

			for (int i = 0; i < hand.length; i++) {
				// Added try catch in case i + 1 went out of bounds
				try {
					if (hand[i].getCardPlace() == -1
							&& hand[i + 1].getCardPlace() != -1) {

						if (isSideComp) {
							anim.createAnimation(new Animation(anim,
									hand[i + 1].getSprite(), new Point(
											(int) hand[i + 1].getSprite()
													.getX(), posBaseY
													+ (i * 20)), animSpeed));
						} else {
							anim.createAnimation(new Animation(anim,
									hand[i + 1].getSprite(), new Point(posBaseX
											+ (i * 20), 30), animSpeed));
						}

						// Switches the spot of the card in the computers hand
						tmp = hand[i];
						hand[i] = hand[i + 1];
						hand[i + 1] = tmp;

						swapped = true;
					}
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}
	}

	public void addCardToStack(Card c) {
		cards.push(c);
	}

	// ///////////////////
	// Getters and Setters
	// ////////////////////

	public String getName() {
		return compName;
	}

	public void setName(String s) {
		compName = s;
	}

	public Card[] getHand() {
		return hand;
	}

	public void setMidPoint(Point p) {
		midPoint = p;
	}

	public Point getMidPoint() {
		return midPoint;
	}
	
	public Point getStackLocation(){
		return stackLocation;
	}
	
	public void setStackLocation(Point p){
		stackLocation = p;
	}

	public void setIsSideComp(boolean b) {
		isSideComp = b;
	}

	public Card getMiddleCard() {
		return middleCard;
	}

	public void setMiddleCard(Card c) {
		middleCard = c;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public void setHand(Card card, int cardIndexToPass) {
		this.hand[cardIndexToPass] = card;
	}

}
