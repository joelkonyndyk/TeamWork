package package1;

public class MainGame {

	// This is the main class

	private Deck deck;

	private RandomName names;

	public MainGame() {
		deck = new Deck();
		names = new RandomName();
	}

	public void Run() {

		for (int i = 0; i < 10; i++) {
			// System.out.println(names.getName());
		}

		// deck.outputDeck();
		// System.out.println();
		// System.out.println();
		// deck.ShuffleDeck();
		// deck.outputDeck();
	}

	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.Run();
	}
}
