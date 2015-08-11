package package1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class MainGame extends Canvas implements Runnable {

	// This is the main class

	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private CardGame game;

	private boolean running = false;
	private Thread thread;

	private int updates;
	private int frames;

	private GameGUI drawPanel;

	public MainGame() {
		frame = new JFrame();
		game = new CardGame();
		drawPanel = new GameGUI(game);
	}

	public void init() {
		game.init();
		drawPanel.init();
		requestFocus();

		this.addMouseListener(new MouseInput(this, drawPanel));
		this.addMouseMotionListener(new MouseInput(this, drawPanel));

		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Sets the screen default open position to the middle of the screen
		frame.setLocationRelativeTo(null);
		frame.add(drawPanel);
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		init();

		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updatesTemp = 0;
		int framesTemp = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			// this is the game loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				updatesTemp++;
				delta--;
			}
			render();
			framesTemp++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + " Ticks, Fps " + frames);
				updates = updatesTemp;
				frames = framesTemp;
				updatesTemp = 0;
				framesTemp = 0;
			}
		}
		stop();
	}

	private void tick() {
		drawPanel.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// anything under here is what is being drawn
		// game.run();
		// game.render(g);

		g.setColor(Color.CYAN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		drawPanel.render(g);

		// ///////////

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		MainGame main = new MainGame();
		main.start();
		// main.run();
	}
}
