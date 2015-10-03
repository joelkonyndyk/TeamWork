package package1;

import java.awt.Point;
import java.util.Stack;

public class Player {

	private String name;
	private Deck deck;

	private Card[] hand;
	private Card[] sort;

	private Stack<Card> cards = new Stack<Card>();

	private int[] playerHandPositionX = { 100, 150, 200, 250, 300, 350, 400,
			450, 500, 550, 600, 650, 700 };

	private boolean isTurn = false;

	private int startCard;

	private final int handSize = 13;

	public Player(String name, Deck deck) {
		this.name = name;
		this.deck = deck;
		init();
	}

	// Initialize
	public void init() {
		hand = new Card[handSize];
		sort = new Card[handSize];
		startCard = 40;

		for (int i = 0; i < handSize; i++) {
			// the -1 is added to account for the list starting at 0
			hand[i] = deck.getCard(startCard - 1);
			startCard++;
		}
	}

	public void sortHand() {

		boolean swapped = true;
		int j = 0;
		Card tmp;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < hand.length - j; i++) {
				if (hand[i].getSuitNumber() > hand[i + 1].getSuitNumber()) {
					tmp = hand[i];
					hand[i] = hand[i + 1];
					hand[i + 1] = tmp;
					swapped = true;
				}
				if (hand[i].getSuitNumber() == hand[i + 1].getSuitNumber()) {
					if (hand[i].getCardNumber() > hand[i + 1].getCardNumber()) {
						tmp = hand[i];
						hand[i] = hand[i + 1];
						hand[i + 1] = tmp;
						swapped = true;
					}
				}
			}
		}
	}

	public void UpdateHand(AnimationController anim) {

		// This will shift shift the players hand to left when there are open
		// spaces
		// Note: this does not work when multiple cards are selected at once
		boolean swapped = true;
		Card tmp;
		while (swapped) {
			swapped = false;

			for (int i = 0; i < hand.length; i++) {
				// Added try catch in case i + 1 went out of bounds
				try {
					if (hand[i].getCardPlace() == -1
							&& hand[i + 1].getCardPlace() != -1) {

						// Animates the cards to the left if needed
						anim.createAnimation(new Animation(anim, hand[i + 1]
								.getSprite(), new Point(playerHandPositionX[i],
								445), 10));

						// Switches the spot of the card in the players hand
						tmp = hand[i];
						hand[i] = hand[i + 1];
						hand[i + 1] = tmp;

						swapped = true;
					}
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}
		// ***********************
		// The code below in in progress for centering the players hand on the
		// screen
		// ***********************

		// int middle = 6;
		// int startingPos = 0;
		// int cardsLeft = 0;
		//
		// for (int i = 0; i < player.getHand().length; i++) {
		// if (player.getHand()[i].getCardPlace() != -1) {
		// cardsLeft++;
		// }
		// }
		// startingPos = middle - (cardsLeft / 2);
		//
		// for (int i = 0; i < player.getHand().length; i++) {
		// if (player.getHand()[i].getCardPlace() != -1 && startingPos < 13) {
		// player.getHand()[i].getSprite().setX(
		// player.getCardPositionX(startingPos));
		// player.setHand(player.getHand()[i], startingPos);
		// startingPos++;
		// }
		// }
		// startingPos = 0;
	}

	public void addCard(Card c) {
		cards.push(c);
	}

	// ///////////////////
	// Getters and Setters
	// ////////////////////

	public String getName() {
		return name;
	}

	public Card[] getHand() {
		return hand;
	}

	public int[] getP1CardLocX() {
		return playerHandPositionX;
	}

	public int getCardPositionX(int i) {
		return playerHandPositionX[i];
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
