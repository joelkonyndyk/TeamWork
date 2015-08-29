package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class HeartsGame {
	// This is where we will create the Hearts game logic

	private RandomName names;
	private String plyrName;
	private Deck deck;
	private Player player;
	private ComputerPlayer comp1, comp2, comp3;

	private boolean mouseClicked = false;
	private Point pointClicked;

	private Sprite sprite;
	private Sprite cardBack;
	private Sprite spriteTest;
	
	SpriteSheet spriteSheet;

	private boolean runGame = false;

	public HeartsGame(Deck deck) {
		this.deck = deck;
		spriteSheet = deck.getSpriteSheet();
	}

	public void init() {
		names = new RandomName();

		deck.ShuffleDeck();

		player = new Player(plyrName, deck);
		comp1 = new ComputerPlayer(names.getName(), deck, 1);
		comp2 = new ComputerPlayer(names.getName(), deck, 2);
		comp3 = new ComputerPlayer(names.getName(), deck, 3);

		player.setTurn(true);
		
//		cardBack = new Sprite(spriteSheet.getTile(57));

		initHands();
	}
	
	public void initHands(){		
		// Sets the location of the cards in the players hand
				int location = 50;
				for (int i = 0; i < player.getHand().length; i++) {
					player.getHand()[i].getSprite().setPosition(location, 400);
					location += 50;
				}

				spriteTest = deck.getCard(1).getSprite();
//				spriteTest = cardBack;
				spriteTest.setPosition(200, 200);
	}

	public void run() {

	}

	public void tick() {
		
		for (int i = 0; i < 52; i++){
			for (int j = 0; j < player.getHand().length; j++){
				if (deck.getCard(i).equals(player.getHand()[j])){
//					System.out.println("Works");
					deck.getCard(i).setShowBack(false);
				} else{
					deck.getCard(i).setShowBack(true);
				}				
			}
		}

		if (mouseClicked) {
			if (spriteTest.getBounds().contains(pointClicked)) {
				spriteTest.rotateImage90();
				spriteTest
						.setPosition(spriteTest.getX() + 5, spriteTest.getY());
			}
			mouseClicked = false;
		}

	}

	public void render(Graphics2D g) {

		spriteTest.paint(g);

		for (int i = 0; i < player.getHand().length; i++) {
			player.getHand()[i].getSprite().paint(g);

		}
	}

	public void mouseClicked(Point p) {
		mouseClicked = true;
		pointClicked = p;
	}

	// Getters and Setters
	public boolean isRunning() {
		return runGame;
	}

	public void setRunGame(boolean runGame) {
		this.runGame = runGame;
	}
}
