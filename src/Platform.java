import javafx.scene.image.ImageView;

public class Platform extends ImageView{
	
	
	private int myHits;

	public Platform(int k) {
		myHits = k;
	}
	
	public int wasHit() {
		myHits -= 1;
		return myHits;
	}

	
	
}
