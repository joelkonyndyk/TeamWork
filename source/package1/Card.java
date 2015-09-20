package package1;

import java.awt.image.BufferedImage;

public class Card {

	private int cardNumber;
	private int suitNumber;
	private int cardPlace;

	private boolean showBack = false;

	private Sprite sprite;

	private BufferedImage cardFront;
	private BufferedImage cardBack;

	private String suit;

	// Constructors
	public Card(int cardNumber, int suitNum) {
		this.cardNumber = cardNumber;
		this.suitNumber = suitNum;
	}

	public Card(int cardNumber, int suitNum, int cp, Sprite sprite) {
		this.cardNumber = cardNumber;
		this.suitNumber = suitNum;
		this.cardPlace = cp;
		this.sprite = sprite;
	}

	public Card(int cardNumber, String suit) {

		this.cardNumber = cardNumber;
		this.suit = suit;

		if (suit == "heart") {
			suitNumber = 1;
		} else if (suit == "diamond") {
			suitNumber = 2;
		} else if (suit == "club") {
			suitNumber = 3;
		} else if (suit == "spade") {
			suitNumber = 4;
		}
	}

	// Methods

	public void checkSuit() {
		if (suitNumber == 1) {
			suit = "spade";
		} else if (suitNumber == 2) {
			suit = "heart";
		} else if (suitNumber == 3) {
			suit = "club";
		} else if (suitNumber == 4) {
			suit = "diamond";
		}
	}

	// ///////////////////
	// Getters and Setters
	// ////////////////////
	
	public int getCardNumber() {
		return this.cardNumber;
	}

	public int getSuitNumber() {
		return this.suitNumber;
	}
	
	public int getCardAndSuitNumber(){
		return this.suitNumber * this.cardNumber;
	}

	public int getCardPlace() {
		return this.cardPlace;
	}

	public void setCardPlace(int cp) {
		this.cardPlace = cp;
	}

	public void setCardBack(BufferedImage img) {
		cardBack = img;
	}
	
	public void setCardNumber(int CardNumber)
		
		switch
		case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10; case 11: case 12; case 13;
		    System.Out.Println("testing case 1 to 5");
		    break;
		 case 6: case 7: case 8: case 9: case 10:
		    System.Out.Println("testing case 6 to 10");
		    break;
		 default:
		

	}

	public String getSuit() {
		checkSuit();
		return this.suit;
	}

	public String getCard() {
		String card = "";
		if (cardNumber < 11) {
			card = "" + cardNumber;
		} else if (cardNumber == 11) {
			card = "J";
		} else if (cardNumber == 12) {
			card = "Q";
		} else if (cardNumber == 13) {
			card = "K";
		}
		return card;
	}

	public void setShowBack(boolean b) {
		showBack = b;

		if (showBack) {
			this.sprite.setImage(cardBack);
		} else {
			sprite.setImage(cardFront);
		}
	}

	public boolean showBack() {
		return showBack;
	}

	public Sprite getSprite() {
		return this.sprite;
	}
}
