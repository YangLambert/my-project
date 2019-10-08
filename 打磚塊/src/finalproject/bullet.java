package finalproject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class bullet {
	private double x;
	private double y;
	private controller c;
	private game Game;
	private int direction;
	private enemy e;
	Animation br;
	Animation bl;
	Animation bu;
	Animation bd;
	BufferedImage image;
	public bullet(double x , double y,game Game,int direction,controller c){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		this.direction=direction;
		bu=new Animation(1,Game.bullet[0],Game.bullet[4],Game.bullet[8],Game.bullet[0]);
		bd=new Animation(1,Game.bullet[1],Game.bullet[5],Game.bullet[9],Game.bullet[1]);
		br=new Animation(1,Game.bullet[2],Game.bullet[6],Game.bullet[10],Game.bullet[2]);
		bl=new Animation(1,Game.bullet[3],Game.bullet[7],Game.bullet[11],Game.bullet[3]);
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
		x+=10;
		br.runAnimation();
	}
	public void tickl(){
		x-=10;
		bl.runAnimation();;
	}
	public void ticku(){
		y-=10;
		bu.runAnimation();
	}
	public void tickd(){
		y+=10;
		bd.runAnimation();;
	}
	public int getdirection(){
		return direction;
	}
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,20,20);
	}
	public void render(Graphics g){

			bu.drawAnimation(g, x, y, 0);


			bd.drawAnimation(g, x, y, 0);
		

			br.drawAnimation(g, x, y, 0);
	
			bl.drawAnimation(g, x, y, 0);
		
	}
	
}
