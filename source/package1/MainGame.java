package package1;

import javax.swing.JFrame;

public class MainGame extends JFrame {

	// This is the main class

	private Deck deck;
	
	GameGUI drawPanel = new GameGUI();
		
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
		
		for (int i = 0; i < 10; i++) {
			// System.out.println(names.getName());
		}
	}

	private RandomName names;

	public MainGame() {
		deck = new Deck();
		names = new RandomName();
	}


	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.Run();
	}
}
