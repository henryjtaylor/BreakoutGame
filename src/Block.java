import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Block extends ImageView{
	
	private int myHits;
	private int myPowerup;
	
	public Block(int k, Image image) {
		myHits = k;
		this.setImage(image);
		Random random = new Random();
		double r = random.nextDouble();
		if (r <= .2) {
			myPowerup = (int) (Math.random() * 3 + 1);
		} else {
			myPowerup = 0;
		}
	}
	
	public int wasHit() {
		myHits -= 1;
		return myHits;
	}
	
	public int checkHits() { return myHits;}
	public int getPowerup() {return myPowerup;}

}
