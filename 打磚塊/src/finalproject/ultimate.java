package finalproject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ultimate {
	private double x;
	private double y;
	private controller c;
	private game Game;
	private int direction;
	private int icedirection;
	private enemy e;
	Animation ult;
	private player p;

	private int count=0;
	public ultimate(double x , double y,game Game,int direction,controller c,player p){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		this.p=p;
		this.direction=direction;
		ult=new Animation(1,Game.ultimate[0],Game.ultimate[1],Game.ultimate[2]);
		
	}
	public void setx(double x){
		this.x = x;
	}
	public void sety(double y){
		this.y = y;
	}
	public double getx(){
		return x;
	}
	public double gety(){
		return y;
	}
	
	public void tick(){
		ult.runAnimation();
		if(c.removeult){
			c.removeULT(this);
			c.setremoveult(false);
		}
	}
	
	public int getdirection(){
		return direction;
	}
	public int getIceDirection(){
		return icedirection;
	}
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,40,40);
	}
	public void render(Graphics g){
		ult.drawAnimation(g, p.getx()-40, p.gety()-40, 0);
		
	}
	public int getcount(){
		return count;
	}
}

