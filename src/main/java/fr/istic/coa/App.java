package fr.istic.coa;

import fr.istic.coa.scheduler.Scheduler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setOnCloseRequest(e -> {
            Scheduler.getInstance().stop();
            Platform.exit();
            System.exit(0);
        });

		stage.setTitle("TP AOC - ActiveObject");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
