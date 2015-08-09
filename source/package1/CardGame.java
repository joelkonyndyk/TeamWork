package package1;

public class CardGame {
	// Where we will create the logic for the game

	private RandomName names;

	private String plyrName;

	private Deck deck;

	private Player player;

	private ComputerPlayer comp1, comp2, comp3;

	public CardGame() {

		deck = new Deck();
		names = new RandomName();

		player = new Player(plyrName, deck);
		comp1 = new ComputerPlayer(names.getName(), deck, 1);
		comp2 = new ComputerPlayer(names.getName(), deck, 2);
		comp3 = new ComputerPlayer(names.getName(), deck, 3);

	}
}
