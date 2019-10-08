package finalproject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class lazer {
	private double x;
	private double y;
	private controller c;
	private game Game;
	private int direction;
	private enemy e;
	BufferedImage lazerud;
	BufferedImage lazerlr;
	public lazer(double x , double y,game Game,int direction,controller c){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		this.direction=direction;
		lazerlr=Game.lazer[0];
		lazerud=Game.lazer[1];
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
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,30,30);
	}
	public void renderd(Graphics g){
		g.drawImage(lazerud,(int) x,(int) y, null);
	}
	public void renderu(Graphics g){
		g.drawImage(lazerud,(int) x,(int) y, null);
	}
	public void renderl(Graphics g){
		g.drawImage(lazerlr,(int) x,(int) y, null);
	}
	public void renderr(Graphics g){
		g.drawImage(lazerlr,(int) x,(int) y, null);
	}
	
}
