//This entire file is a part of my masterpiece
//Henry Taylor

/**
 * Essentially the Scene Manager. Shows the different pages, and transitions to Levels 
 * when player reaches the animated stage. Between each level and once a player loses/wins,
 * code is sent back to Breakout for the next stage to be prepared. I think this code is 
 * written well because it is very clean, very little repeats (the only repeats were repeats
 * that couldn't be avoided). In addition, it is well organized going down -- starts with 
 * all the methods that call a page, then goes down to the helper functions that make
 * those pages. This segment of code is also integral for the program as a whole
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Breakout{
	
	private final String TITLE = "Breakout to the Championship";
    private int SIZE;
    private final Paint BACKGROUND = Color.SANDYBROWN;
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
		myScene = new Scene(root, SIZE, SIZE, BACKGROUND);
		return myScene;
	}
	
	private void informationPage() {
		root.getChildren().clear();
		makeLabel(getIntro());
		makeIntroButton();
	}
	
	private void directionPage() {
		root.getChildren().clear();
		makeLabel(getDirect());
		makeDirectionButton();
	}
	
	private void selectLevel(int level) {
		root.getChildren().clear();
		Scene sceneTwo = myLevel.init(myScene, myStage, level, SIZE, this);
		myStage.setScene(sceneTwo);
		myStage.show();
	}

	private void makeLabel(String string) {
		Label label = new Label(string);
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
		button.setText(str);
		button.setMaxWidth(100);
		r.getChildren().add(button);
		return button;
	}
	
	public void wonLevel(int level) {
		root.getChildren().clear();
		if (level == 3) {
			handleGameOver();
		} else {
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
	}
	
	public void handleGameOver() {
		Label label = new Label("YOU WON! GO DUKE!");
		positionLabel(SIZE/2, SIZE/2, root, label);
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
				+ "the ball doesn't get by you! There are also three\n"
				+ "powerups available, as well as numerous cheat codes!\n"
				+ "\n"
				+ "\n"
				+ "Cheat Codes:\n"
				+ "W: Win Instantly\n"
				+ "R: Reset\n"
				+ "L: Extra Lives\n"
				+ "Z: Lost Instantly\n"
				+ "M: Get two platforms\n"
				+ "T: Sticky platforms\n"
				+ "Space: Release stuck ball\n"
				+ "1-3: Go to that level";
		return str;
	}

}
