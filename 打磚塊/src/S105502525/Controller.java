package S105502525;
import java.io.File;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class Controller {
    @FXML
    private Canvas canvas;

    private GameManager gameManager;
    private AnimationTimer animationTimer;
    private gmanager Gmanager;
    
    public Controller (gmanager Gmanager)
    {
    	this.Gmanager = Gmanager;
    }

    @FXML
    private void initialize(){
        gameManager = new GameManager(canvas.getGraphicsContext2D ());// initialize the GameManager
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                // clear the canvas to repaint on the canvas.
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gameManager.draw(timestamp);
            }
        };
        animationTimer.start();
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameManager.OnClick(event);
            }
        });
    }

    public void setScene(Scene scene,Scene scene2, Stage stage){
        scene.setOnKeyPressed ( new EventHandler<KeyEvent>() {
            @Override
           public void handle ( KeyEvent event ) {
            	
            	if(event.getCode() == KeyCode.M ){
            		Gmanager.set_cc(1);
            		stage.setScene(scene2);
            	}
            	else if(event.getCode() == KeyCode.A){
            		Gmanager.set_cc(2);
            		stage.setScene(scene2);
            	}
            	else if(event.getCode() == KeyCode.S ){
            		Gmanager.set_cc(3);
            		stage.setScene(scene2);
            	}
            }
        } );
        scene.setOnKeyReleased ( new EventHandler<KeyEvent>() {
            @Override
            public void handle ( KeyEvent event ) {
            
            	
            }
        } );
    }

}
