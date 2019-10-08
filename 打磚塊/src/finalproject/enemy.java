package finalproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
public class enemy{
	private Random ram = new Random();
	boolean calculated = false;
	private int direction=0;
	private int minus =1;
	private double x;
	private double y;
	private double vel=0.4;
	public Random r = new Random();
	private double health;
	private BufferedImage Enemy;
	private int enemyamount=0;
	private int enemydeath=0;
	private game Game;
	private player p;
	private controller c;
	private int point=0;
	Animation er;
	Animation el;
	Animation eu;
	Animation ed;
	public enemy(double x, double y, game Game,controller c,double health,player p){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		this.health=health;
		this.p=p;
		er = new Animation(5,Game.enemy[0],Game.enemy[1],Game.enemy[2],Game.enemy[3]);
		el = new Animation(5,Game.enemy[4],Game.enemy[5],Game.enemy[6],Game.enemy[7]);
		eu = new Animation(5,Game.enemy[8],Game.enemy[9],Game.enemy[10],Game.enemy[11]);
		ed = new Animation(5,Game.enemy[12],Game.enemy[13],Game.enemy[14],Game.enemy[15]);
		 
	}
	public void init(){
		ram = new Random();
		calculated = false;
		direction=0;
		minus =1;
		double x;
		double y;
		double vel=0.4;
		r = new Random();
		int health;
		BufferedImage Enemy;
		enemyamount=0;
		enemydeath=0;
		game Game;
		player p;
		controller c;
		point=0;
	}
	public void tick(){
			
			switch(shortest(p.getx(),p.gety())){
			case 0:
				if(p.getx()>getx()){
					x+=0.8;
					direction =1;//R
				}
				if(p.getx()<getx()){
					x-=0.8;
					direction =2;//L
				}
				if(Math.abs(p.getx()-getx())<2&&p.gety()<gety()){
					y-=0.8;
					direction =3;//D
				}
				if(Math.abs(p.getx()-getx())<2&&p.gety()>gety()){
					y+=0.8;
					direction =4;//U
				}
				break;
			case 1:
				if(p.gety()>gety()){
					y+=0.8;
					direction =4;//U
				}
				if(p.gety()<gety()){
					y-=0.8;
					direction =3;//D
				}
				if(Math.abs(p.gety()-gety())<2&&p.getx()<getx()){
					x-=0.8;
					direction =2;//L
				}
				if(Math.abs(p.gety()-gety())<2&&p.getx()>getx()){
					x+=0.8;
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
				Game.point+=10*r.nextInt(10);
				c.removeenemy(this);
				Game.setenemyD(Game.getenemyD()+1);
				Game.setenemyT(Game.getenemyT()+1);
				}		
			}
			}
			for(int k=0;k<Game.lazer1.size();k++){
				lazer l = Game.lazer1.get(k);
				if(physics.collision(this, l)){
				health-=80;
				c.removeLAZER(l);
				if(health<=0){
				Game.point+=100;
				c.removeenemy(this);
				Game.setenemyD(Game.getenemyD()+1);
				Game.setenemyT(Game.getenemyT()+1);
				}	
			}
			}
			
			for(int m=0;m<Game.iceball.size();m++){
				iceball i = Game.iceball.get(m);
				if(physics.collision(this, i)){
				health-=400;
				if(i.getIceDirection()==1)
					y-=25;
				else if(i.getIceDirection()==2)
					y+=25;
				else if(i.getIceDirection()==4)
					x-=25;//R
				else if(i.getIceDirection()==3)
					x+=25;//L
				c.removeICE(i);
				if(health<=0){
				Game.point+=100*r.nextInt(15);
				c.removeenemy(this);
				Game.setenemyD(Game.getenemyD()+1);
				Game.setenemyT(Game.getenemyT()+1);
				}		
			}
			}
	}
	public void renderu(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x+5, (int)y-10, (int)health/40, 3);
		eu.runAnimation();
		eu.drawAnimation(g, x, y, 0);
	}
	public void renderd(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x+5, (int)y-10, (int)health/40, 3);
		ed.runAnimation();
		ed.drawAnimation(g, x, y, 0);
	}
	public void renderr(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x+5, (int)y-10, (int)health/40, 3);
		er.runAnimation();	
		er.drawAnimation(g, x, y, 0);
	}
	public void renderl(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x+5, (int)y-10, (int)health/40, 3);
		el.runAnimation();
		el.drawAnimation(g, x, y, 0);
	}
	public int shortest(double x,double y){
		if(Math.abs(p.getx()-getx())>=Math.abs(p.gety()-gety()))
			return 1;
		else
			return 0;
	}
	public double getx(){
		return x;
	}
	public double gety(){
		return y;
	}
	public int getdirection(){
		return direction;
	}
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,30,30);
	}
	public void setx(double x){
		this.x = x;
	}
	public void sety(double y){
		this.y = y;
	}	
	public int getenemyamount(){
		return enemyamount;
	}
	public int getenemydeath(){
		return enemydeath;
	}
	public double  getenemyhealth(){
		return health;
	}
	public void setenemyhealth(int a){
		health-=a;
	}
	public int getpoint(){
		return point;
	}
}
