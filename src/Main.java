

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private int SIZE = 400;
	private Breakout myBreakout;
	
	@Override
	public void start(Stage s) {
		
		myBreakout = new Breakout();
		s.setTitle(myBreakout.retrieveTitle());
		Scene scene = myBreakout.init(SIZE, s);
		s.setScene(scene);
		s.show();
		
		
	}
	
	private static void main(String[] args) {
		launch(args);
	}

}
