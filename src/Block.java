import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Block extends ImageView{
	
	private int myHits;

	public Block(int k, Image image) {
		myHits = k;
		this.setImage(image);
	}
	
	public int wasHit() {
		myHits -= 1;
		return myHits;
	}
	
	public int checkHits() { return myHits;}


}
