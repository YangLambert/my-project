package S105502525;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.canvas.GraphicsContext;

public class gmanager2 {
	private long lastUpdateTime = 0;
	private toy Toy ;
	private List<gobject> objectList = new ArrayList<>();
	 private GraphicsContext gc;
	 private int cc=0;
	 private brick1 brick1s[]= new brick1[9];
	 private brick2 brick2s[]= new brick2[17];
	 private bullet[] enemy = new bullet[10000000];
	
	  public gmanager2(){
	        
		  Toy= new toy();
	       // Initialize the Player here
	        objectList.add(Toy);
	        
	        brick1s[0]= new brick1(50,0,1);
	         brick1s[1]= new brick1(150,0,1);
	         brick1s[2]= new brick1(250,0,1);
	         brick1s[3]= new brick1(350,0,1);
	         brick1s[4]= new brick1(450,0,1);
	         brick1s[5]= new brick1(450,60,1);
	         brick1s[6]= new brick1(450,120,1);
	         brick1s[7]= new brick1(450,180,1);
	         brick1s[8]= new brick1(450,240,1);
	         brick2s[0]= new brick2(0,0);
	         brick2s[1]= new brick2(0,60);
	         brick2s[2]= new brick2(100,0);
	         brick2s[3]= new brick2(0,120);
	         brick2s[4]= new brick2(0,180);
	         brick2s[5]= new brick2(200,0);
	         brick2s[6]= new brick2(0,240);
	         brick2s[7]= new brick2(300,0);
	         brick2s[8]= new brick2(50,240);
	         brick2s[9]= new brick2(100,240);
	         brick2s[10]= new brick2(150,240);
	         brick2s[11]= new brick2(200,240);
	         brick2s[12]= new brick2(250,240);
	         brick2s[13]= new brick2(300,240);
	         brick2s[14]= new brick2(350,240);
	         brick2s[15]= new brick2(400,240);
	         brick2s[16]= new brick2(400,0);
	         for(int i=0;i<9;i++){
	        	 objectList.add(brick1s[i]);
	         }
	         for(int i=0;i<17;i++){
	        	 objectList.add(brick2s[i]);
	         }
	    
	         
	        
	    }
	
	   public void draw(long timestamp){
		 
	        final double elapsedSeconds = (timestamp - lastUpdateTime) / 1_000_000_000.0 ;
	        final double dx = elapsedSeconds * Toy.getSpeed() * Toy.getxDerection();
	        if(Toy.x > 350)
	        	Toy.turnxDerection(-1);
	        else if(Toy.x <60)
	        	Toy.turnxDerection(1);
	        
	        Toy.move(dx, 0);
	        for(gobject obj:objectList){
	            obj.draw(gc);
	        }
	   
	        lastUpdateTime = timestamp;
	    }
	   public void setGreenMove()
	   {
		   Toy.setSpeed(150);
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
		   if(Toy.getHp()<=0){
			   return 1;
		   }
		   return 0;
	   }
	  
	   
}
