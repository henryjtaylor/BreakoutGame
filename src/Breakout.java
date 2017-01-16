
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Breakout{
	
	private final String TITLE = "Breakout to the Championship";
    private final String BALL_IMAGE = "ball.gif";
    private int SIZE;
    private final Paint BACKGROUND = Color.WHITE;
    private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private final int KEY_INPUT_SPEED = 5;
    private final double GROWTH_RATE = 1.1;
    private int BOUNCER_SPEED = 30;
    private Stage myStage;
    private Scene myScene;
    private Group root;
    
    

    public String retrieveTitle() { return TITLE;}
    
	public Scene init(int size, Stage s) {
		myStage = s;
		SIZE = size;
		myScene = setupGame();
		informationPage();
		return myScene;
	}
	
	private Scene setupGame() {
		myScene = new Scene(root, SIZE, SIZE, Color.TAN);
		//myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		//myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
		return myScene;
	}
	
	private void informationPage() {
		root.getChildren().clear();
		makeIntroLabel();
	}
	
	private void positionLabel(int x, int y, Group r, Label label) {
		label.setTranslateX(x);
		label.setTranslateY(y);
		r.getChildren().add(label);
	}
	private void makeIntroLabel() {
		Label label = new Label(getIntro());
		positionLabel(SIZE/2, SIZE/2, root, label);
	}

	private String getIntro() {
		String str = "Welcome Coach of the Duke Basketball Team! You are beginning\n"
				+ "the long road to the National Championship, starting with the\n"
				+ "ACC tournament, then moving on to the semi finals of March Madness\n"
				+ "and culminating with the Championship game! Unforunately, you are\n"
				+ "going to have to face three of Dukes top opponents:\n"
				+ "				Kansas, Kentucky, and UNC\n";
		return str;
	}
	

}
