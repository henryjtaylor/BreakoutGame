import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle{
	private int VERT;
	
	public void Rectangle(int size) {
		VERT = size;
	}
	
	public int positionY() {
		return VERT;
	}
}
