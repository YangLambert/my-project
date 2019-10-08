package S105502525;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

/*
* This class manage the object in game.
*/

public class GameManager {
    private List<GameObject> objectList = new ArrayList<>(); //All the GameObject in game is store in this List.
    private magic MAGIC; // This is your player.
    private shoot Shoot; // This is your player.
    private sword Sword; // This is your player.
    private word1 Word1;
    private word2 Word2;// This is your player.
    private word3 Word3;// This is your player.
    
    private long lastUpdateTime = 0;
    private GraphicsContext gc;// This is what you need to draw on canvas.

    public GameManager(GraphicsContext gc){
        this.gc = gc; //Pass the GraphicsContext from canvas
        MAGIC = new magic();// Initialize the Player here
        objectList.add(MAGIC);// Add the Player to objectList
        Shoot = new shoot();// Initialize the Player here
        objectList.add(Shoot);// Add the Player to objectList
        Sword = new sword();// Initialize the Player here
        objectList.add(Sword);// Add the Player to objectList
        Word1 = new word1();// Initialize the Player here
        objectList.add(Word1);// Add the Player to objectList
        Word2 = new word2();// Initialize the Player here
        objectList.add(Word2);// Add the Player to objectList
        Word3 = new word3();// Initialize the Player here
        objectList.add(Word3);// Add the Player to objectList
        
        
    }

    // Draw method, being called at Controller's animation timer.
    public void draw(long timestamp){
        final double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0 ;
        for(GameObject obj:objectList){
            obj.draw(gc);
        }
        lastUpdateTime = timestamp;
    }


    //check every click on canvas if the GameObject is clicked or not.
    public void OnClick(MouseEvent event){
        for(GameObject obj:objectList){
            if(obj.isClick(event)){
                obj.OnClick ( );
                break;
            }
        }
    }
}