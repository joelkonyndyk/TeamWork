package package1;

public class MainGame {

	// This is the main class

	private Deck deck;

	public MainGame() {
		deck = new Deck();
	}

	public void Run() {
		deck.outputDeck();
		System.out.println();
		System.out.println();
		deck.ShuffleDeck();
		deck.outputDeck();
	}

	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.Run();
	}
}
