package package1;

import javax.swing.JFrame;

public class MainGame extends JFrame {

	// This is the main class

	private Deck deck;
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
		
		
	}

	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.Run();
	}
}
