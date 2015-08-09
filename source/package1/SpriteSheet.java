package package1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage spriteSheet;
	BufferedImage Img = null;
	int width = 72;
	int height = 100;
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

	// Load image from the sprite sheet
	public BufferedImage loadImage() {

		
		try {
			Img = ImageIO.read(new File("source/package1/cards.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Img;
	}

	// Stores images in ArrayList Hearts(0-12), Diamonds(13-25), Clubs(26-38),
	// Spades(39-51), Backs(52-64)
	public void storeImages() {
		for (int i = 0; i < 5; i++)
			for (int z = 0; z < 13; z++) {
				images.add(Img
						.getSubimage(z * width, i * height, width, height));
			}

	}

	public BufferedImage getTile(int cardNumber) {
		return images.get(cardNumber);
	}

	/*
	 * Old Method to Grab subimages from our sprite sheet
	 * 
	 * public BufferedImage getTile(int xCardLocation, int yCardLocation){
	 * return Img.getSubimage(xCardLocation * width, yCardLocation * height,
	 * width, height);
	 * 
	 * }
	 */

}
