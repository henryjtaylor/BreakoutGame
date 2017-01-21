
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;



public class Levels {
	
	private final String UNC = "brick2.gif";
	private final String KENTUCKY = "brick3.gif";
	private final String KANSAS = "brick4.gif";
	private final String BALL = "large-basketball.gif";
	
	
	private int SIZE;
	private Scene myScene;
	private Group root;
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
    
    private Platform PLATFORM; 
    private ImageView BBALL;
    private BlockManager BLOCKMANAGER;

    
    public Scene init(Scene s, int level, int size) {
    	SIZE = size;
    	LEVEL = level;
    	myScene = s;
    	NUMBER_OF_BLOCKS = 10;
    	myScene = setupLevel();

    	
    	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> move(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
    	return myScene;
    	
    }
    private Scene setupLevel() {
    	root = new Group();
    	myScene = new Scene(root, SIZE, SIZE, Color.WHITE);
    	
    	makePlatform();
    	makeBall();
    	
    	if (LEVEL == 3) {
    		BALL_SPEED = 100;
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
    	BBALL.setX(BBALL.getX() + BALL_SPEED * time * X_DIRECTION);
    	BBALL.setY(BBALL.getY() + BALL_SPEED * time * Y_DIRECTION);
    	
    	PLATFORM.setX(PLATFORM.getX() + PLATFORM_DIRECTION * time * PLATFORM_SPEED);
    	
    	handleBallCollision();
    	PLATFORM_DIRECTION = 0;
    }
    
    private void handleKeyInput(KeyCode code) {
    	if (code == KeyCode.LEFT) {
    		PLATFORM_DIRECTION = -5;
    		//movePlatform(1);
    	} else if (code == KeyCode.RIGHT) {
    		PLATFORM_DIRECTION = 5;
    		//movePlatform(-1);
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
    	Block hitBrick = BLOCKMANAGER.checkBricks(BBALL.getX(), BBALL.getY());
    	changeBricks(hitBrick);
    }
    
    private void changeBricks(Block hitBrick) {
    	if (hitBrick == null) {
    		return;
    	}
    	root.getChildren().remove(hitBrick);
    	if (!(hitBrick.checkHits() == 0)) {
    		root.getChildren().add(hitBrick);
    	}
    	//if (BBALL.getY() < hitBrick.getY()+hitBrick.getFitHeight() && BBALL.getY() > hitBrick.getY()) {
    	//	return;	
    	//}
    	Y_DIRECTION = Y_DIRECTION*-1;
    	return;
    }
    
    
    
    //alternative way to move platform
    /*
    private void movePlatform(int direction) {
    	if (direction > 0) {
    		if (PLATFORM.getX() >= 0) {
    			PLATFORM.setX(PLATFORM.getX() - 10);
    		}
    	} else {
    		if (PLATFORM.getX() + PLATFORM.getHeight() < SIZE) {
    			PLATFORM.setX(PLATFORM.getX() + 10);
    		}
    	}
    }
    */
    
    private void makeBall() {
    	Image ball = new Image(getClass().getClassLoader().getResourceAsStream(BALL));
    	ImageView ballView = new ImageView(ball);
    	ballView.setX(SIZE/2);
    	ballView.setY(SIZE/2);
    	ballView.setFitWidth(20);
    	ballView.setFitHeight(20);
    	BBALL = ballView;
    	root.getChildren().add(BBALL);
    }
    
    private void makePlatform() {
    	Platform platform = new Platform();
    	if (LEVEL == 1) {
    		platform.setWidth(SIZE/4);
    	} else {
    		platform.setWidth(SIZE/5);
    	}
    	platform.setHeight(10);
    	platform.setX(SIZE/2);
    	platform.setY(SIZE-platform.getHeight()-10);
    	PLATFORM = platform;
    	root.getChildren().add(PLATFORM);
    }
    

    //need to somehow figure how to take in formation from file, etc.
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
}
