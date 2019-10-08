package S105502525;

import java.io.File;


import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class game_controller2 {
	private MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("sound/victory.mp3").toURI().toString()));
	@FXML
	private Rectangle brickblock;
	@FXML
	private Sphere ball;
	@FXML
	private  Canvas canvas;
	@FXML
	private ImageView marry;
	@FXML
	private ImageView gameover;
	@FXML
	private ImageView victory;
	
	 private AnimationTimer rectangleAnimation;
	 private long lastUpdateTime = 0;
	 private double rectangleSpeedX = 0 ; // pixels per second
	    private double rectangleSpeedY = 0 ;
	    int i=0,j=0;
	    int o=0;
	    int cc=0;
	    private gmanager Gmanager;
	    private gmanager2 Gmanager2 = new gmanager2();
	    public game_controller2 (gmanager Gmanager)
	    {
	    	this.Gmanager = Gmanager;
	    }	 	   
	 @FXML
	 private void initialize() {
		 
		 brickblock.setTranslateX(220);
		 brickblock.setTranslateY(550);
		 ball.setTranslateX(250);
		 ball.setTranslateY(545);
		 marry.setTranslateX(220);
		 marry.setTranslateY(541);
		 gameover.setVisible(false);
		 victory.setVisible(false);
		 Gmanager2.set_gc(canvas.getGraphicsContext2D ());
	        rectangleAnimation = new AnimationTimer() {
	            @Override
	            public void handle(long timestamp) {
	                if (lastUpdateTime > 0) {
	                	 cc=Gmanager.get_cc();
	                    final double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0 ;
	                    final double deltaX = elapsedSeconds * rectangleSpeedX;
	                    final double balldeltaX = elapsedSeconds * i;
	                    final double balldeltaY= elapsedSeconds * j;
	                    final double oldX = brickblock.getTranslateX();
	                    ball.setTranslateX(ball.getTranslateX()+balldeltaX);
	                    ball.setTranslateY(ball.getTranslateY()+balldeltaY);
	                    marry.setTranslateX(brickblock.getTranslateX());
	                    marry.setTranslateY(brickblock.getTranslateY()-9);
	                    if(cc==3){
	                    	marry.setTranslateY(brickblock.getTranslateY());
	                    	marry.setFitWidth(50);
	                    	marry.setFitHeight(47);;
	                    marry.setImage(new Image("file:image/sword1.png"));
	                    }
	                    if(cc==2){
	                    	marry.setTranslateY(brickblock.getTranslateY());
	                    	marry.setFitWidth(50);
	                    	marry.setFitHeight(47);;
	                    marry.setImage(new Image("file:image/shoot1.png"));
	                    }
	                    double newX =oldX + deltaX;
	                    if(newX>=433.5640203000001){
	                    	newX=433.5640203000001;
	                    }
	                    else if (newX<=0){
	                    	newX=0;
	                    }
	                    
	                    brickblock.setTranslateX(newX);	                    
	                    final double deltaY = elapsedSeconds * rectangleSpeedY;
	                    final double oldY = brickblock.getTranslateY();
	                    final double newY =oldY + deltaY;
	                    brickblock.setTranslateY(newY);
	                    
	                }
	                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	                Gmanager2.draw(timestamp);
	           
              

                   if( Gmanager2.isCollisionWith(ball.getTranslateX(),ball.getTranslateY())==1){
                	   i=-i; 
                   }
                   else if( Gmanager2.isCollisionWith(ball.getTranslateX(),ball.getTranslateY())==2){
                	   j=-j; 
                   }
                    if(ball.getTranslateX() <= 6|| ball.getTranslateX() >= 495){        
                    i=-i;     	
                    }  
	                  if(ball.getTranslateY() <= 6 ){  
	                    	j=-j;  
	                
	                    }  
	                  if(ball.getTranslateY() >= 595  ){  
	                	  if(o==0){
	                	  gameover.setVisible(true);
	                	  }
	                    }  
	                  if(Gmanager2.setscreen()==1){
		                	o=1;
		                	victory.setVisible(true);
		                	mediaPlayer.play();
		                }
	                 
	                    if(isCollisionWith()) {  
	                    	
	                    	j=-j;
	                    } 
	                 
	                lastUpdateTime = timestamp;
	            }
	        };
	        rectangleAnimation.start ();
	    }
	  public void setScene(Scene scene){
	    	FadeTransition ft = new FadeTransition(Duration.millis(3000),brickblock);
	        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	            	if (event.getCode()==KeyCode.SPACE&&ball.getTranslateX()==250&&
	       		 ball.getTranslateY()==545) {
	            		if(cc==1){
	            		i=300;
	            		j=-200;
	            		}
	            		else if(cc==2){
		            		i=350;
		            		j=-250;}
	            		else if(cc==3){
		            		i=150;
		            		j=-100;}
	            		
	            		Gmanager2.setGreenMove();
	                }
	                if (event.getCode()==KeyCode.RIGHT) {
	                    rectangleSpeedX = 300;
	                    
	                }
	                if (event.getCode()==KeyCode.LEFT) {
	                    rectangleSpeedX = -300;
	                }
	              
	            }
	         
	           
	        });

	        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	                if (event.getCode() == KeyCode.RIGHT){
	                    rectangleSpeedX = 0;    
	                }
	                if (event.getCode() == KeyCode.LEFT){
	                    rectangleSpeedX = 0;    
	                }	                	                
	            }
	        });
	    }
	  public boolean isCollisionWith(){  
	        if(ball.getTranslateX() + 7 > brickblock.getTranslateX() && ball.getTranslateX() < brickblock.getTranslateX() + brickblock.getWidth() && ball.getTranslateY() > brickblock.getTranslateY() && ball.getTranslateY() < brickblock.getTranslateY() + brickblock.getHeight()){  
	        	return true;  
	        }  
	        return false;  
	    }
	 
	  
	  
}
