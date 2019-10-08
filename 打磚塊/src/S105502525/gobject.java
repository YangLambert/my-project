package S105502525;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class gobject {
	protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Image image;
    protected int hp;  
	  public gobject(double x,double y,double width,double height,int hp,Image image){
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        this.hp = hp;
	        this.image = image;
	    }
	  public void draw(GraphicsContext gc){
	        gc.drawImage(image,x,y,width,height);
	        //the position of the image, now it's drawn from x,y to x+width,y+height
	        //and it's draw from the top left corner of the image.
	    }
	    public void move(double deltaX,double deltaY){
	        this.x+=deltaX;
	        this.y+=deltaY;
	        //something missing here.
	    }
	    public abstract int getHp(); 
	    
	    public void setHp() {  
	    	
		  }
       
}
