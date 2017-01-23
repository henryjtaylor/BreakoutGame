
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Levels {
	
	private final String UNC = "brick2.gif";
	private final String KENTUCKY = "brick3.gif";
	private final String KANSAS = "brick4.gif";
	private final String BALL = "large-basketball.gif";
	
	
	private int SIZE;
	private Label myPoints;
	private Scene myScene;
	private Scene mainScene;
	private Group root;
	private Breakout BREAKOUT;
	private Stage myStage;
	private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private int LEVEL;
    private int BALL_SPEED;
    private int X_DIRECTION = 1;
    private int Y_DIRECTION = 1;
    private int PLATFORM_DIRECTION = 0;
    private int PLATFORM_SPEED = 200;
    private int NUMBER_OF_BLOCKS;
    private int HIT_BLOCKS;
    private int NUMBER_OF_LIVES;
    private ImageView[] LIVES;
    
    private Platform PLATFORM; 
    private Platform PLATFORM2;
    private ImageView BBALL;
    private BlockManager BLOCKMANAGER;
    private Timeline ANIMATION;

    
    public Scene init(Scene s, Stage stage, int level, int size, Breakout b) {
    	BREAKOUT = b;
    	SIZE = size;
    	LEVEL = level;
    	if (level == 1) {
    		makePointBar();
    	}
    	mainScene = s;
    	determineBlocks();
    	HIT_BLOCKS = 0;
    	NUMBER_OF_LIVES = 3;
    	myStage = stage;
    	LIVES = new ImageView[NUMBER_OF_LIVES];
    	myScene = setupLevel();

    	
    	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> move(SECOND_DELAY));
    	ANIMATION = new Timeline();
		ANIMATION.setCycleCount(Timeline.INDEFINITE);
		ANIMATION.getKeyFrames().add(frame);
		ANIMATION.play();
    	return myScene;
    	
    }
    private Scene setupLevel() {
    	root = new Group();
    	myScene = new Scene(root, SIZE, SIZE, Color.WHITE);
    	
    	PLATFORM = makePlatform(1);
    	makeBall();
    	makeLives();
    	makeLevelBar();
    	root.getChildren().add(myPoints);
    	
    	
    	
    	if (LEVEL == 3) {
    		BALL_SPEED = 120;
    	} else {
    		BALL_SPEED = 75;
    	}
    	
    	BLOCKMANAGER = new BlockManager(NUMBER_OF_BLOCKS, SIZE, BBALL.getFitWidth());
    	
    	setBlocks(NUMBER_OF_BLOCKS);
    	
    	myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    	//myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
    	
    	
    	return myScene;
    }
    
    private void move(double time) {
    	
    	moveBall(time);
    	movePlatform(time);
    	
    	handleBallCollision();
    	PLATFORM_DIRECTION = 0;
    }
    
    private void moveBall(double time) {
    	BBALL.setX(BBALL.getX() + BALL_SPEED * time * X_DIRECTION);
    	BBALL.setY(BBALL.getY() + BALL_SPEED * time * Y_DIRECTION);
    }
    
    private void movePlatform(double time) {
    	PLATFORM.setX(PLATFORM.getX() + PLATFORM_DIRECTION * time * PLATFORM_SPEED);
    	if (PLATFORM2 != null) {
    		PLATFORM2.setX(PLATFORM2.getX() + PLATFORM_DIRECTION*time*PLATFORM_SPEED);
    	}
    }
    
    private void handleKeyInput(KeyCode code) {
    	if (code == KeyCode.LEFT) {
    		PLATFORM_DIRECTION = -5;
    	} else if (code == KeyCode.RIGHT) {
    		PLATFORM_DIRECTION = 5;
    	} else if (code == KeyCode.DIGIT1) {
    		LEVEL = 0;
    		returnToMain();
    	} else if (code == KeyCode.DIGIT2) {
    		LEVEL = 1;
    		returnToMain();
    	} else if (code == KeyCode.DIGIT3) {
    		LEVEL = 2;
    		returnToMain();
    	} else if (code == KeyCode.Z) {
    		NUMBER_OF_LIVES = 0;
    		returnToMain();
    	} else if (code == KeyCode.M) {
    		PLATFORM2 = makePlatform(2);
    	} else if (code == KeyCode.R) {
    		reset();
    	} else if (code == KeyCode.L) {
    		refillLives();
    	}
    }
    
    private void handleBallCollision() {
    	if (BBALL.getY() + BBALL.getFitHeight() >= PLATFORM.getY()) {
    		if (BBALL.getX() + BBALL.getFitWidth()/2 <= PLATFORM.getX() + PLATFORM.getWidth()) {
    			if (BBALL.getX() + BBALL.getFitWidth()/2 >= PLATFORM.getX()) {
    				Y_DIRECTION = Y_DIRECTION * -1;
    			}
    		}
    	}
    	if (BBALL.getX() <= 0) {
    		X_DIRECTION = X_DIRECTION * -1;
    	}
    	if (BBALL.getX() + BBALL.getFitWidth() >= SIZE) {
    		X_DIRECTION = X_DIRECTION * -1;
    	}
    	if (BBALL.getY() <= 0) {
    		Y_DIRECTION = Y_DIRECTION * -1;
    	}
    	if (BBALL.getY() > SIZE) {
    		lostLife();
    	}
    	Block hitBrick = BLOCKMANAGER.checkBricks(BBALL.getX(), BBALL.getY());
    	changeBricks(hitBrick);
    	
    }
    
    
    private void changeBricks(Block hitBrick) {
    	if (hitBrick == null) {
    		return;
    	}
    	updatePointBar();
    	HIT_BLOCKS += 1;
    	if (HIT_BLOCKS == (NUMBER_OF_BLOCKS) + (NUMBER_OF_BLOCKS*2) + (NUMBER_OF_BLOCKS*3)) {
    		returnToMain();
    	}
    	root.getChildren().remove(hitBrick);
    	if (!(hitBrick.checkHits() == 0)) {
    		root.getChildren().add(hitBrick);
    	}
    	if (BBALL.getX() <= hitBrick.getX() && BBALL.getY() < hitBrick.getY()) {
    		X_DIRECTION *= -1;
    		return;	
    	} else {
    		Y_DIRECTION = Y_DIRECTION*-1;
    	}
    	//Y_DIRECTION = Y_DIRECTION*-1;
    	return;
    }
    
    private void makePointBar() {
    	myPoints = new Label("0");
    	myPoints.setTranslateX(SIZE-50);
    	myPoints.setTranslateY(SIZE-myPoints.getHeight()-20);
    	
    }
    private void updatePointBar() {
    	int points = Integer.parseInt(myPoints.getText());
    	points += 10;
    	myPoints.setText(Integer.toString(points));
    }
    
    private void makeLevelBar() {
    	Label levelLabel = new Label("Level " + Integer.toString(LEVEL));
    	levelLabel.setTranslateX(SIZE/2);
    	levelLabel.setTranslateY(SIZE-levelLabel.getHeight() - 20);
    	root.getChildren().add(levelLabel);
    }
    
    private void makeLives() {
    	Label livesLeft = new Label("Lives Left:");	
    	livesLeft.setTranslateX(10);//10);
    	livesLeft.setTranslateY(SIZE-livesLeft.getHeight() - 20);
    	root.getChildren().add(livesLeft);
    	Image ball = new Image(getClass().getClassLoader().getResourceAsStream(BALL));
    	FontLoader lengthCalc = Toolkit.getToolkit().getFontLoader();
    	double liveX= livesLeft.getTranslateX() + lengthCalc.computeStringWidth(livesLeft.getText(), livesLeft.getFont()) + 10;
    	for (int i = 0; i < NUMBER_OF_LIVES; i++) {
    		ImageView ballView = setBallPosition(liveX, livesLeft.getTranslateY(), 15, 15, new ImageView(ball));
    		root.getChildren().add(ballView);
    		liveX += ballView.getFitWidth() + 5;
    		LIVES[i] = ballView;
    	}
    	
    }
    
    private void refillLives() {
    	for (int i = 0; i < NUMBER_OF_LIVES; i++) {
    		root.getChildren().remove(LIVES[i]);
    	}
    	NUMBER_OF_LIVES = 3;
    	makeLives();
    }
    
    private void lostLife() {
    	if (NUMBER_OF_LIVES == 0) {
    		returnToMain();
    	} else {
	    	//returnToMain();
	    	NUMBER_OF_LIVES -= 1;
	    	root.getChildren().remove(LIVES[NUMBER_OF_LIVES]);
	    	//BALL_SPEED = 0;
	    	reset();
    	}
    }

    
    private void makeBall() {
    	Image ball = new Image(getClass().getClassLoader().getResourceAsStream(BALL));
    	ImageView ballView = setBallPosition(SIZE/2, SIZE/3, 20, 20, new ImageView(ball));
    	BBALL = ballView;
    	root.getChildren().add(BBALL);
    }
    
    private ImageView setBallPosition(double x, double y, int width, int height, ImageView ball) {
    	ball.setX(x);
    	ball.setY(y);
    	ball.setFitWidth(width);
    	ball.setFitHeight(height);
    	return ball;
    }
    
    private Platform makePlatform(int k) {
    	Platform platform = new Platform();
    	if (LEVEL == 1) {
    		platform.setWidth(SIZE/4);
    	} else {
    		platform.setWidth(SIZE/5);
    	}
    	platform.setHeight(10);
    	if (k == 2) {
    		platform.setX(PLATFORM.getX() + PLATFORM.getWidth() + 10);
    		platform.setY(PLATFORM.getY());
    	} else {
    		platform.setX(SIZE/2);
        	platform.setY(SIZE-platform.getHeight()-30);
    	}
    	root.getChildren().add(platform);
    	return platform;
    }
    

    private void setBlocks(int num) {
    	int width = SIZE/num;
    	int height = SIZE/20;
    	Image unc = new Image(getClass().getClassLoader().getResourceAsStream(UNC));
    	Image kentucky = new Image(getClass().getClassLoader().getResourceAsStream(KENTUCKY));
    	Image kansas = new Image(getClass().getClassLoader().getResourceAsStream(KANSAS));
    	buildBlocks(unc, width, num, 3, height);
    	buildBlocks(kentucky, width, num, 2, height);
    	buildBlocks(kansas, width, num, 1, height);
    	
    }
    
    private void buildBlocks(Image image, int width, int number, int hits, int height) {
    	int x  = 0;
    	int y;
    	if (hits == 3) {
    		y = 0;
    	} else if (hits == 2) {
    		y = height;
    	} else {
    		y = height *2;
    	}
    	for (int i = 0; i < number; i++) {
    		Block block = new Block(hits, image);
    		block.setFitWidth(width);
    		block.setFitHeight(height);
    		block.setX(x);
    		block.setY(y);
    		root.getChildren().add(block);
    		BLOCKMANAGER.addBrick(i, block);
    		x+= width;
    		
    	}
    	
    }
    
    private void determineBlocks() {
    	if (LEVEL == 1) {
    		NUMBER_OF_BLOCKS = 3;
    	} else if (LEVEL == 2) {
    		NUMBER_OF_BLOCKS = 5;
    	} else {
    		NUMBER_OF_BLOCKS = 7;
    	}
    	
    }
    
    
    private void returnToMain() {
    	ANIMATION.stop();
    	if (NUMBER_OF_LIVES == 0) {
    		BREAKOUT.handlePlayerLost();
    	} else {
    		BREAKOUT.wonLevel(LEVEL);
    	}
    	myStage.setScene(mainScene);
    	myStage.show();
    }
    
    private void reset() {
    	BBALL.setX(SIZE/2);
    	BBALL.setY(SIZE/3);
    	PLATFORM.setX(SIZE/2);
    	PLATFORM.setY(SIZE-PLATFORM.getHeight()-30);
    	if (PLATFORM2 != null) {
    		PLATFORM2.setX(PLATFORM.getX() + PLATFORM.getWidth() + 10);
    		PLATFORM2.setY(PLATFORM.getY());
    	}
    	
    }
}
