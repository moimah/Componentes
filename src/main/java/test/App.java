package test;

import dad.javafx.componentes.DateChooser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application{

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setCenter(new DateChooser());
		Scene scene = new Scene(root, 500, 300);
		
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
