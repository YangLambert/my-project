package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;



public class player {
	private static double x;
	private static double y;
	private double px;
	private double py;
	private Random r = new Random();
	private double velx = 0;
	private double vely = 0;
	private double playerhealth;
	public boolean playerDeath = true;
	private controller c;
	private BufferedImage Player;
	private game Game;
	public int iceamount;
	public int lazeramount;
	public int weapon=0;
	Animation pr;
	Animation pl;
	Animation pu;
	Animation pd;
	public player(double x, double y, game Game,controller c,double playerhealth){
		this.x = x;
		this.y = y;
		this.Game=Game;
		this.playerhealth=playerhealth;
		this.c=c;
		pr = new Animation(5,Game.player[0],Game.player[1],Game.player[2],Game.player[3]);
		pl = new Animation(5,Game.player[4],Game.player[5],Game.player[6],Game.player[7]);
		pu = new Animation(5,Game.player[8],Game.player[9],Game.player[10],Game.player[11]);
		pd = new Animation(5,Game.player[12],Game.player[13],Game.player[14],Game.player[15]);
	}
	public void tick(){	
		if(playerhealth<10000){
			playerhealth+=0.5;
		}
		
			x+=velx;
			y+=vely;
			if(x<=0)
				x=0;
			if(y<=0)
				y=0;
			if(x>=Game.WIDTH*Game.SCALE-60)
				x=Game.WIDTH*Game.SCALE-60;
			if(y>=Game.HEIGHT*Game.SCALE-60)
				y=Game.HEIGHT*Game.SCALE-60;
			for(int i=0;i< Game.e.size();i++){
				enemy e = Game.e.get(i);
				if(physics.collision(this, e)){
					c.removeenemy(e);
					playerhealth-=1000;
					Game.setenemyD(Game.getenemyD()+1);
				}
			}
			for(int i=0;i< Game.bo.size();i++){
				boss bo = Game.bo.get(i);
				if(physics.collision(this, bo)){
					c.removeboss(bo);
					playerhealth-=4500;
					Game.setbossD(Game.getbossD()+1);
				}
			}
			for(int i=0;i< Game.hp.size();i++){
				upgrades hp = Game.hp.get(i);
				if(physics.collision(this, hp)){
					c.removeHP(hp);
					if(playerhealth<10000){
						playerhealth+=3500;
						if(playerhealth>10000){
							playerhealth=10000;
						}
					}
				}
			}
			for(int i=0;i< Game.lz.size();i++){
				upgrades lz = Game.lz.get(i);
				if(physics.collision(this, lz)){
					c.removeLAZERICON(lz);
					Game.setlazeramount(Game.getlazeramount()+300);
				}
				
			}
			for(int i=0;i< Game.ib.size();i++){
				upgrades ib = Game.ib.get(i);
				if(physics.collision(this, ib)){
					c.removeICEICON(ib);
					Game.seticeamount(Game.geticeamount()+40);
				}
				
			}
			for(int i=0;i< Game.ut.size();i++){
				upgrades ut = Game.ut.get(i);
				if(physics.collision(this, ut)){
					c.removeULTICON(ut);
					Game.setultamount(Game.getultamount()+5);
				}
				
			}
			if(playerhealth<0){
				playerDeath=false;
			}
			if(Game.getdirection()==1||Game.getdirection()==0)
			pu.runAnimation();
			else if(Game.getdirection()==2)
			pd.runAnimation();
			else if(Game.getdirection()==3)
			pr.runAnimation();
			else if(Game.getdirection()==4)
			pl.runAnimation();
			
	}
	public void render(Graphics g){
		Font ft = new Font("arial",Font.BOLD,15);
		g.setFont(ft);
		g.setColor(Color.black);
		if(weapon==0&&!Game.getRestart()){
			g.drawString("fireball", (int)x-5,(int) y-10);
		}
		else if(weapon==1){
			g.drawString("lazer:"+Integer.toString(Game.getlazeramount()), (int)x-10,(int) y-10);
		}
		else if(weapon==2){
			g.drawString("iceball:"+Integer.toString(Game.geticeamount()), (int)x-15,(int) y-10);
		}
		else if(weapon==3){
			g.drawString("ultimate:"+Integer.toString(Game.getultamount()), (int)x-15,(int) y-10);
		}
		if(Game.getdirection()==1||Game.getdirection()==0){
			pu.drawAnimation(g, x, y, 0);
		}
		else if(Game.getdirection()==2){
			pd.drawAnimation(g, x, y, 0);
		}
		else if(Game.getdirection()==3){
			pr.drawAnimation(g, x, y, 0);
		}
		else if(Game.getdirection()==4){
			pl.drawAnimation(g, x, y, 0);
		}
	}
	public boolean getDeath(){
		return playerDeath;
	}
	public void  setDeath(boolean a){
		 playerDeath=a;
	}
	public static double getx(){
		return x;
	}
	public static double gety(){
		return y;
	}
	public void setx(double x){
		this.x = x;
	}
	public void sety(double y){
		this.y = y;
	}
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,40,40);
	}
	public void setvelx(double velx){
		this.velx=velx;
	}
	public void setvely(double vely){
		this.vely=vely;
	}
	public double getphealth(){
		return playerhealth;
	}
	public int getweapon(){
		return weapon;
	}
	public void setweapon(int i){
		weapon=i;
	}
}
