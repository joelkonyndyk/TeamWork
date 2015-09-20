package package1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

public class Animation {

	// private int originX;
	// private int originY;
	// private int destX;
	// private int destY;
	//
	// private float currX;
	// private float currY;
	//
	// // will need an origin, destination, and current degree/rotation
	//
	// private ArrayList scenes;
	// private int sceneIndex;
	// private long movieTime;
	// private long totalTime;

	// Variables for test animation class
	private LinkedList<Sprite> animList = new LinkedList<Sprite>();

	private StopWatch time = new StopWatch();

	private Sprite sprite;

	private int destX, destY;

	// distance to destination
	private float distX, distY;

	// increment to move per turn
	private float incX, incY;

	private int duration;

	// Constructors
	public Animation(Sprite s, Point destPt, int duration) {
		sprite = s;

		distX = Math.abs(destPt.x - s.getX());
		distY = Math.abs(destPt.y - s.getY());

		this.duration = duration;

		init();
	}

	// Methods

	public void init() {
		incX = distX / duration;
		incY = distY / duration;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
	}

	// Getters and Setters

	// public Animation() {
	// scenes = new ArrayList();
	// totalTime = 0;
	// start();
	// }
	//
	// // add scenes to the array list and set time for each scene
	// public synchronized void addScene(Sprite sprite, long time, Point
	// destPoint) {
	// totalTime += time;
	// scenes.add(new Onescene(sprite, totalTime, destPoint));
	// }
	//
	// // start animation from beginning
	// public synchronized void start() {
	// movieTime = 0;
	// sceneIndex = 0;
	// }
	//
	// // change scenes
	// public synchronized void update(long timePassed) {
	//
	// if (scenes.size() > 1) {
	// movieTime += timePassed;
	//
	// if (movieTime >= totalTime) {
	// movieTime = 0;
	// sceneIndex = 0;
	// }
	// while (movieTime > getScene(sceneIndex).endTime) {
	// sceneIndex++;
	// }
	// }
	// }
	//
	// // get animations current scene
	// public synchronized Image getImage() {
	//
	// if (scenes.size() == 0) {
	// return null;
	// }
	//
	// else {
	// return getScene(sceneIndex).pic;
	// }
	// }
	//
	// // get scene
	// private Onescene getScene(int x) {
	// return (Onescene) scenes.get(x);
	// }
	//
	// private class Onescene {
	// Image pic;
	// long endTime;
	//
	// public Onescene(Sprite sprite, long endTime) {
	// pic = sprite.getImage();
	// this.endTime = endTime;
	// }
	// }
}
