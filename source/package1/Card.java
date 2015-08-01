package package1;

public class Card {

	private int cardNumber;
	private int suitNumber;
	private int cardPlace;

	private String suit;

	// Constructors
	public Card(int cardNumber, int suitNum) {
		this.cardNumber = cardNumber;
		this.suitNumber = suitNum;
	}

	public Card(int cardNumber, int suitNum, int cp) {
		this.cardNumber = cardNumber;
		this.suitNumber = suitNum;
		this.cardPlace = cp;
	}

	public Card(int cardNumber, String suit) {

		this.cardNumber = cardNumber;
		this.suit = suit;

		if (suit == "spade") {
			suitNumber = 1;
		} else if (suit == "heart") {
			suitNumber = 2;
		} else if (suit == "club") {
			suitNumber = 3;
		} else if (suit == "diamond") {
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

	// Getters and Setters

	public int getCardNumber() {
		return this.cardNumber;
	}

	public int getSuitNumber() {
		return this.suitNumber;
	}

	public int getCardPlace() {
		return this.cardPlace;
	}

	public void setCardPlace(int cp) {
		this.cardPlace = cp;
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
}
