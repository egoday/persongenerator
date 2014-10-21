package org.persongenerator.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application  {

	public static void main(String... args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
		
        Scene scene = new Scene(root);

        stage.setTitle("PersonGenerator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/person-icon.png")));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
