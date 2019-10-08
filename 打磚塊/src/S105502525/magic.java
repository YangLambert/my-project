package S105502525;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

/*
* This class is the Player in Game.
* It extends the GameObject and has its' own method.
*/

public class magic extends GameObject {
    private double speed = 200;//basic speed
    public int xDerection = 0;//x derection speed
    public int yDerection = 0;//y derection speed
    public boolean speak = false;//speeking or not
    public magic(){
        super(0,16,225,275,ImageUtility.magic);
    }
    public double getSpeed(){
        return speed;
    }
    @Override
    public void draw(GraphicsContext gc){
        super.draw (gc); // call the gameObject draw first
        //if someone click on Player, the speak should change from true to false (or false to true).
        if(speak) {
            gc.setTextBaseline( VPos.CENTER);
            gc.setFont(new Font (32)); //set the Font for the text being draw from GraphicsContext.
            gc.fillText ( "�y�t:�� �����O:��",x+width-10,y);
        }
    }

    @Override
    public void OnClick ( ) { //Should be called when the player is clicked by the mouse.
        speak = !speak;
    }
}
