import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Powerup {
	
	private int myPowerup;
	private ImageView myPowerupImage;
	
	private final String speedUp = "icon-arrow-up-c-128.png";
	private final String doublePlat = "2x.gif";
	private final String sticky = "New_Marshmallow_Body.png";
	

	public Powerup(int k) {
		myPowerup = k;
		imageAssign();
	}
	
	private void imageAssign() {
		String[] powerUpList = {speedUp, doublePlat, sticky};
		if (myPowerup != 0) {
			Image power = new Image(getClass().getClassLoader().getResourceAsStream(powerUpList[myPowerup-1]));
			myPowerupImage = new ImageView(power);
		}
	}
	
	public ImageView getImage() { return myPowerupImage;}
	public int getPower() {return myPowerup;}
	
	
	
}
