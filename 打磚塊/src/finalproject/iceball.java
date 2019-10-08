package finalproject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class iceball {
	private double x;
	private double y;
	private controller c;
	private game Game;
	private int direction;
	private int icedirection;
	private enemy e;
	private BufferedImage[] ice = new BufferedImage[4];
	public iceball(double x , double y,game Game,int direction,controller c){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		this.direction=direction;
		ice[0]=Game.icebug[0];
		ice[1]=Game.icebug[1];
		ice[2]=Game.icebug[2];
		ice[3]=Game.icebug[3];
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
	
	public void tickr(){
		x+=5;
	}
	public void tickl(){
		x-=5;
	}
	public void ticku(){
		y-=5;
	}
	public void tickd(){
		y+=5;
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
	public void renderd(Graphics g){
		g.drawImage(ice[0],(int) x-10,(int) y-40, null);
		icedirection=1;
	}
	public void renderu(Graphics g){
		g.drawImage(ice[1],(int) x-10,(int) y+40, null);
		icedirection=2;
	}
	public void renderl(Graphics g){
		g.drawImage(ice[2],(int) x+40,(int) y-10, null);
		icedirection=3;
	}
	public void renderr(Graphics g){
		g.drawImage(ice[3],(int) x-40,(int) y-10, null);
		icedirection=4;
	}
}

