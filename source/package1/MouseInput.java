package package1;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

	private MainGame game;
	private GameGUI gamePanel;

	// private StopWatch mouseTimer;

	private int screenX = 0;
	private int screenY = 0;

	// private boolean

	private Point clickPoint;

	public MouseInput(final MainGame game, final GameGUI gamePanel) {
		this.game = game;
		this.gamePanel = gamePanel;

		// mouseTimer = new StopWatch();
		// mouseTimer.start();
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// gamePanel.boardCleared();

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {

		screenX = game.getLocationOnScreen().x;
		screenY = game.getLocationOnScreen().y;

		int mx = e.getX();
		int my = e.getY();

		Point p = new Point(mx, my);

//		System.out.println(p);
		
		gamePanel.mouseClicked(p);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = e.getPoint();

		int mx = e.getX();
		int my = e.getY();

	}
}
