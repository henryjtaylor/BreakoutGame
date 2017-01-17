
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;



public class Levels {
	
	private final String UNC = "brick2.gif";
	private final String Kentucky = "brick3.gif";
	private final String Kansas = "brick4.gif";
	
	
	private int SIZE;
	private Scene myScene;
	private Group root;
	private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private int LEVEL;

    
    public Scene init(Scene s, int level, int size) {
    	SIZE = size;
    	LEVEL = level;
    	myScene = s;
    	myScene = setupLevel();
    	return myScene;
    	
    }
    private Scene setupLevel() {
    	root = new Group();
    	myScene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
    	
    	setBlocks();
    	
    	
    	
    	return myScene;
    }
    
    //need to somehow figure how to take in formation from file, etc.
    private void setBlocks() {
    	
    }

}
