package package1;

import javax.swing.JFrame;

public class MainGame extends JFrame implements Runnable {

	// This is the main class

	private static final long serialVersionUID = 1L;

	private CardGame game;

	GameGUI drawPanel;

	public MainGame() {
		game = new CardGame();
		drawPanel = new GameGUI(game);
	}

	public void init() {
		game.init();

		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Sets the screen default open position to the middle of the screen
		setLocationRelativeTo(null);
		add(drawPanel);
	}

	public void run() {
		init();
		game.run();
	}

	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.run();
	}
}
