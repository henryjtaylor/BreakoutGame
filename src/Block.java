import javafx.scene.image.ImageView;


public class Block extends ImageView{
	
	private int myHits;

	public Block(int k) {
		myHits = k;
	}
	
	public int wasHit() {
		myHits -= 1;
		return myHits;
	}

}
