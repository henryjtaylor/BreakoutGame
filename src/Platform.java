import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle{
	private int VERT;
	private boolean STICKY;
	
	public void Rectangle(int size) {
		VERT = size;
		STICKY = false;
	}
	
	public int positionY() {
		return VERT;
	}
	
	public boolean isSticky() { return STICKY;}
	public void makeSticky() {
		STICKY = true;
	}
}
