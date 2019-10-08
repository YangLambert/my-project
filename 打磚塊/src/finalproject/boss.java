package finalproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
public class boss{
	private Random ram = new Random();
	boolean calculated = false;
	private int direction=0;
	private int minus =1;
	public  double x;
	public  double y;
	public Random r = new Random();
	private double health;
	private BufferedImage Enemy;
	private int bossamount=0;
	private int bossdeath=0;
	private game Game;
	private player p;
	private controller c;
	private int point=0;
	Animation dr;
	Animation dl;
	Animation du;
	Animation dd;
	public boss(double x, double y, game Game,controller c,double health,player p){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		this.health=health;
		this.p=p;
		dr = new Animation(5,Game.boss[0],Game.boss[1],Game.boss[2]);
		dl = new Animation(5,Game.boss[3],Game.boss[4],Game.boss[5]);
		du = new Animation(5,Game.boss[6],Game.boss[7],Game.boss[8]);
		dd = new Animation(5,Game.boss[9],Game.boss[10],Game.boss[11]);
		 
	}
	public void tick(){
			if(health<5000){
				health+=5;
			}
			switch(shortest(p.getx(),p.gety())){
			case 0:
				if(p.getx()>getx()){
					x+=1.2;
					direction =1;//R
				}
				if(p.getx()<getx()){
					x-=1.2;
					direction =2;//L
				}
				if(Math.abs(p.getx()-getx())<2&&p.gety()<gety()){
					y-=1.2;
					direction =3;//D
				}
				if(Math.abs(p.getx()-getx())<2&&p.gety()>gety()){
					y+=1.2;
					direction =4;//U
				}
				break;
			case 1:
				if(p.gety()>gety()){
					y+=1.2;
					direction =4;//U
				}
				if(p.gety()<gety()){
					y-=1.2;
					direction =3;//D
				}
				if(Math.abs(p.gety()-gety())<2&&p.getx()<getx()){
					x-=1.2;
					direction =2;//L
				}
				if(Math.abs(p.gety()-gety())<2&&p.getx()>getx()){
					x+=1.2;
					direction =1;//R
				}
				break;
			default:
				break;	
			}
			for(int j=0;j<Game.b.size();j++){
				bullet b = Game.b.get(j);
				if(physics.collision(this, b)){
				health-=300;
				c.removebullet(b);
				if(health<=0){
				Game.point+=100*r.nextInt(10);
				c.removeboss(this);
				Game.setbossD(Game.getbossD()+1);
				}		
			}
			}
			for(int k=0;k<Game.lazer1.size();k++){
				lazer l = Game.lazer1.get(k);
				if(physics.collision(this, l)){
				health-=80;
				c.removeLAZER(l);
				if(health<=0){
				Game.point+=1000;
				c.removeboss(this);
				Game.setbossD(Game.getbossD()+1);
				}	
			}
			}
			for(int m=0;m<Game.iceball.size();m++){
				iceball i = Game.iceball.get(m);
				if(physics.collision(this, i)){
				health-=400;
				if(i.getIceDirection()==1)
					y-=10;
				else if(i.getIceDirection()==2)
					y+=10;
				else if(i.getIceDirection()==4)
					x-=10;//R
				else if(i.getIceDirection()==3)
					x+=10;//L
				c.removeICE(i);
				if(health<=0){
				Game.point+=1000*r.nextInt(15);
				c.removeboss(this);
				Game.setbossD(Game.getbossD()+1);
				}		
			}
			}
	}
	public void renderu(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y-20, (int)health/70, 4);
		du.runAnimation();
		du.drawAnimation(g, x, y, 0);
	}
	public void renderd(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y-20, (int)health/70, 4);
		dd.runAnimation();
		dd.drawAnimation(g, x, y, 0);
	}
	public void renderr(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y-20, (int)health/70, 4);
		dr.runAnimation();	
		dr.drawAnimation(g, x, y, 0);
	}
	public void renderl(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y-20, (int)health/70, 4);
		dl.runAnimation();
		dl.drawAnimation(g, x, y, 0);
	}
	public int shortest(double x,double y){
		if(Math.abs(p.getx()-getx())>=Math.abs(p.gety()-gety()))
			return 1;
		else
			return 0;
	}
	public double getx(){
		return x+10;
	}
	public double gety(){
		return y+10;
	}
	public int getdirection(){
		return direction;
	}
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,70,70);
	}
	public void setx(double x){
		this.x = x;
	}
	public void sety(double y){
		this.y = y;
	}	
	public int getbossamount(){
		return bossamount;
	}
	public int getbossdeath(){
		return bossdeath;
	}
	public double  getbosshealth(){
		return health;
	}
	public int getpoint(){
		return point;
	}
	public void setbosshealth(int a){
		health-=a;
	}
}