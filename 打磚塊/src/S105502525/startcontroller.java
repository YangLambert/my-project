package S105502525;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class startcontroller {
	@FXML
	private Button start;
	@FXML
	private Button exit;
	private MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("sound/start.mp3").toURI().toString()));
	public void sScene(Scene scene,Stage stage){
		mediaPlayer.play();
		start.setOnAction(new EventHandler<ActionEvent>()
				{
			@Override
            public void handle ( ActionEvent event ) {
             stage.setScene(scene);
             mediaPlayer.stop();
            }
				});
		exit.setOnAction(new EventHandler<ActionEvent>()
		{
	@Override
    public void handle ( ActionEvent event ) {
     System.exit(0);;
    }
		});
	}
}


