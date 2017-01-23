
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final int KEY_INPUT_SPEED = 5;
    private final double GROWTH_RATE = 1.1;
    private Stage myStage;
    private Scene myScene;
    private Group root;
    private Levels myLevel;
    
    

    public String retrieveTitle() { return TITLE;}
    
	public Scene init(int size, Stage s) {
		myStage = s;
		SIZE = size;
		myLevel = new Levels();
		myScene = setupGame();
		informationPage();
		return myScene;
	}
	
	private Scene setupGame() {
		root = new Group();
		myScene = new Scene(root, SIZE, SIZE, Color.TAN);
		//myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		//myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
		return myScene;
	}
	
	private void informationPage() {
		root.getChildren().clear();
		makeIntroLabel();
		makeIntroButton();
	}
	
	private void directionPage() {
		root.getChildren().clear();
		makeDirectionLabel();
		makeDirectionButton();
	}
	
	private void selectLevel(int level) {
		root.getChildren().clear();
		//Levels setLevel = new Levels();
		Scene sceneTwo = myLevel.init(myScene, myStage, level, SIZE, this);
		myStage.setScene(sceneTwo);
		myStage.show();
	}
	
	private void makeIntroLabel() {
		Label label = new Label(getIntro());
		positionLabel(SIZE/10, SIZE/10, root, label);
	}
	
	private void makeDirectionLabel() {
		Label label = new Label(getDirect());
		positionLabel(SIZE/10, SIZE/10, root, label);
		
	}
	
	
	private void makeIntroButton() {
		Button button = makeButton(SIZE/2, SIZE-50, root, "NEXT");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				directionPage();
			}
		});
	}
	
	private void makeDirectionButton() {
		Button button = makeButton(SIZE/2, SIZE-50, root, "LEVEL ONE!");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				root.getChildren().clear();
				selectLevel(1);
			}
		});
	}
	
	
	private void positionLabel(double x, double y, Group r, Label label) {
		label.setTranslateX(x);
		label.setTranslateY(y);
		r.getChildren().add(label);
	}
	
	private Button makeButton(double x, double y, Group r, String str) {
		Button button = new Button();
		button.setTranslateX(x-100/2);
		button.setTranslateY(y);
		//r.getChildren().add(button);
		button.setText(str);
		button.setMaxWidth(100);
		r.getChildren().add(button);
		return button;
	}
	
	public void wonLevel(int level) {
		root.getChildren().clear();
		int newLevel = level +1;
		Label label = new Label("LEVEL " + Integer.toString(newLevel));
		positionLabel(SIZE/2, SIZE/2, root, label);
		Button button = makeButton(SIZE/2, SIZE-50, root, "LET'S GO!");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				root.getChildren().clear();
				selectLevel(newLevel);
			}
		});
		
	}
	
	public void handlePlayerLost(){
		root.getChildren().clear();
		Label label = new Label("GAME OVER");
		positionLabel(SIZE/2, SIZE/2, root, label);
		Button button = makeButton(SIZE/2, SIZE-50, root, "PLAY AGAIN");
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				directionPage();
			}
		});
		
	}
	
	
	private void pauseScreen(int seconds) {
		long time = System.currentTimeMillis();
		long end = time + (seconds * 1000);
		while (time < end) {
			time = System.currentTimeMillis();
		}
		return;
	}
	

	
	
	
	
	
	
	
	
	
	private String getIntro() {
		String str = "Welcome Coach of the Duke Basketball Team!\n"
				+ "You are beginning the long road to the National\n"
				+ "Championship, starting with the ACC tournament,\n"
				+ "then moving on to the semi finals of March Madness\n"
				+ "and culminating with the Championship game!\n"
				+ "Unforunately, you are going to have to face\n"
				+ "three of Dukes top opponents:\n"
				+ "		Kansas, Kentucky, and UNC\n";
		return str;
	}
	
	private String getDirect() {
		String str = "To play, Use the left and right arrow keys\n"
				+ "to move the platform left and right. Make sure\n"
				+ "			the ball doesn't get by you!";
		return str;
	}

}
