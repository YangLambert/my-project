package S105502525;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;  
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;  
import javafx.scene.shape.Rectangle;  
public class brick2 extends gobject  
{  
	protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;
    protected int hp=10;    
	  
	  public brick2(double x,double y)  
	  {  
		 super(x,y,50,30,10,ImageUtility.brick2);
	  }   
	  @Override
	  public int getHp() {
		  
	    return hp;  
	  }  
	  @Override
	  public void setHp() {  
		  hp-- ;
	  }
	  @Override
	    public void draw(GraphicsContext gc){
	        super.draw (gc); // call the gameObject draw first
	        //if someone click on Player, the speak should change from true to false (or false to true).  
	    }
	}  