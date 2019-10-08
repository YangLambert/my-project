package S105502525;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.canvas.GraphicsContext;

public class gmanager {
	private long lastUpdateTime = 0;
	private green Green ;
	private List<gobject> objectList = new ArrayList<>();
	 private GraphicsContext gc;
	 private int cc=0;
	 private brick1 brick1s[]= new brick1[17];
	 private brick2 brick2s[]= new brick2[9];
	 
	  public gmanager(){
	        
	        Green= new green();
	       // Initialize the Player here
	        objectList.add(Green);
	        
	 	   
	         brick1s[0]= new brick1(0,0,1);
	         brick1s[1]= new brick1(0,60,1);
	         brick1s[2]= new brick1(100,0,1);
	         brick1s[3]= new brick1(0,120,1);
	         brick1s[4]= new brick1(0,180,1);
	         brick1s[5]= new brick1(200,0,1);
	         brick1s[6]= new brick1(0,240,1);
	         brick1s[7]= new brick1(50,240,1);
	         brick1s[8]= new brick1(100,240,1);
	         brick1s[9]= new brick1(150,240,1);
	         brick1s[10]= new brick1(200,240,1);
	         brick1s[11]= new brick1(250,240,1);
	         brick1s[12]= new brick1(300,240,1);
	         brick1s[13]= new brick1(350,240,1);
	         brick1s[14]= new brick1(400,240,1);
	         brick1s[15]= new brick1(300,0,1);
	         brick1s[16]= new brick1(400,0,1);
	         brick2s[0]= new brick2(50,0);
	         brick2s[1]= new brick2(150,0);
	         brick2s[2]= new brick2(250,0);
	         brick2s[3]= new brick2(350,0);
	         brick2s[4]= new brick2(450,0);
	         brick2s[5]= new brick2(450,60);
	         brick2s[6]= new brick2(450,120);
	         brick2s[7]= new brick2(450,180);
	         brick2s[8]= new brick2(450,240);
	         for(int i=0;i<17;i++){
	        	 objectList.add(brick1s[i]);
	         }
	         for(int i=0;i<9;i++){
	        	 objectList.add(brick2s[i]);
	         }
	         
	         
	         
	        
	    }
	
	   public void draw(long timestamp){
		 
	        final double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0 ;
	        final double dx = elapsedSeconds * Green.getSpeed() * Green.getxDerection();
	        if(Green.x > 350)
	        	Green.turnxDerection(-1);
	        else if(Green.x <60)
	        	Green.turnxDerection(1);
	        
	        Green.move(dx, 0);
	        for(gobject obj:objectList){
	            obj.draw(gc);
	        }
	   
	        lastUpdateTime = timestamp;
	    }
	   public void setGreenMove()
	   {
		   Green.setSpeed(120);
	   }

	   public int isCollisionWith(double x,double y){  
		   
		   for (gobject obj:objectList){
		   if(x > obj.x && y >= obj.y && y <= obj.y+obj.height&&x<obj.x+5){  
			  
			   obj.setHp();
			  if(obj.getHp()<=0){
	        	objectList.remove(obj);
			  }
	            return 1;  
	        }
		   else if(x < obj.x+obj.width&&x> obj.x+obj.width-5 && y >= obj.y && y <= obj.y+obj.height){
			   
			   obj.setHp();
				  if(obj.getHp()<=0){
		        	objectList.remove(obj);
				  }
			 
			   return 1; 
		   }
		   else if (y > obj.y&& y<obj.y+5&& x >= obj.x && x <= obj.x+obj.width){
			
			  
			   obj.setHp();
			   if(obj.getHp()<=0){
		        	objectList.remove(obj);
				  }
			
			   return 2;}
		   else if (y < obj.y+obj.height&&y>obj.y+obj.height-5 && x >= obj.x && x <= obj.x+obj.width){
			  
			   obj.setHp();
				  if(obj.getHp()<=0){
		        	objectList.remove(obj);
				  }
			
			   return 2;}  
	    }  
		     return 0; 
	   }
	   public void set_gc(GraphicsContext gc)
	   {
		   this.gc = gc; //Pass the GraphicsContext from canvas
	   }
	   public void set_cc(int cc)
	   {
		   this.cc =cc; //Pass the GraphicsContext from canvas
	   }
	   public int get_cc()
	   {
		   return cc;//Pass the GraphicsContext from canvas
	   }
	   public int setscreen(){
		   if(Green.getHp()<=0){
			   return 1;
		   }
		   return 0;
	   }
	  
	   
}
