package S105502525;

import S105502525.gobject;
import S105502525.ImageUtility;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class bullet extends gobject {
    private double speed = 0;//basic speed
    public int xDerection = 1;//x derection speed
    public int yDerection = 0;//y derection speed
   
    public bullet(){
        super(100,100,100,100,50,ImageUtility.bullet);
    }
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double x){
        speed = x;
    }
    public int getxDerection(){
        return xDerection;
    }
    public int getyDerection(){
        return yDerection;
    }
    @Override
    public int getHp() {  
	    return this.hp;  
	  }  
    @Override
	  public void setHp() {  
		  this.hp-- ;
	  }
    public void turnxDerection(int x){
    	xDerection = x;
    }
    @Override
    public void draw(GraphicsContext gc){
        super.draw (gc); // call the gameObject draw first
        //if someone click on Player, the speak should change from true to false (or false to true).  
    }

}
