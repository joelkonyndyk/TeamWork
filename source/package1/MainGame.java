package package1;

import javax.swing.JFrame;

public class MainGame extends JFrame {

	// This is the main class

	private Deck deck;
<<<<<<< HEAD
	GameGUI drawPanel = new GameGUI();
	
	public MainGame() {
		deck = new Deck();
		
	}
	
	public void init(){
		setSize(1024, 768);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(drawPanel);
	}

	public void Run() {
		init();
		deck.outputDeck();
		System.out.println();
		System.out.println();
		deck.ShuffleDeck();
		deck.outputDeck();
		
		
=======

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
>>>>>>> ec4b7070d8511e4661bfe9a49612a06f74c2426a
	}

	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.Run();
	}
}
