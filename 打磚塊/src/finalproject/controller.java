package finalproject;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
public class controller {
	private LinkedList<enemy> e = new LinkedList<enemy>();
	private LinkedList<boss> bo = new LinkedList<boss>();
	private LinkedList<bullet> b = new LinkedList<bullet>();
	private LinkedList<upgrades> hp = new LinkedList<upgrades>();
	private LinkedList<upgrades> lz = new LinkedList<upgrades>();
	private LinkedList<upgrades> ib = new LinkedList<upgrades>();
	private LinkedList<upgrades> ut = new LinkedList<upgrades>();
	private LinkedList<lazer> lazer = new LinkedList<lazer>();
	private LinkedList<ultimate> ultimate = new LinkedList<ultimate>();
	private LinkedList<iceball> iceball = new LinkedList<iceball>();
	private Random r = new Random();
	private bullet tbullet;
	private ultimate tultimate;
	private lazer tlazer;
	private iceball ticeball;
	private upgrades thp;
	private upgrades tlz;
	private upgrades tib;
	private upgrades tut;
	public boolean removeult=false;
	public  enemy tenemy;
	private boss tboss;
	private player p;
	private game Game;
	private upgrades u;
	private boolean bosshealth=false;
	private int count=0;
	public controller(game Game){
		this.Game = Game;
	}
	public void newenemy(int enemyC){
		for(int i=0;i<enemyC;i++){
			switch(r.nextInt(4)){
				case 0:
					addenemy(new enemy(5,r.nextInt(960),Game,this,1500,p));
				break;
				case 1:
					addenemy(new enemy(1240,r.nextInt(960),Game,this,1500,p));
				break;
				case 2:
					addenemy(new enemy(r.nextInt(1280),35,Game,this,1500,p));
				break;
				case 3:
					addenemy(new enemy(r.nextInt(1280),940,Game,this,1500,p));
				break;
				default:
					break;
			}
			
		}
	}
	public void newBoss(){
			switch(r.nextInt(4)){
				case 0:
					addboss(new boss(0,r.nextInt(960),Game,this,5000,p));
				break;
				case 1:
					addboss(new boss(1240,r.nextInt(960),Game,this,5000,p));
				break;
				case 2:
					addboss(new boss(r.nextInt(1280),0,Game,this,5000,p));
				break;
				case 3:
					addboss(new boss(r.nextInt(1280),940,Game,this,5000,p));
				break;
				default:
					break;
			}
			bosshealth=true;
		}
	public void newHP(){
		
			addHP(new upgrades(r.nextInt(400)+400,r.nextInt(400)+400,Game,this));
			Game.setHP(1);
		
	}
	public void newLAZERICON(){
			addLAZERICON(new upgrades(r.nextInt(400)+400,r.nextInt(400)+400,Game,this));
	}
	public void newICEICON(){
		addICEICON(new upgrades(r.nextInt(400)+400,r.nextInt(400)+400,Game,this));
	}
	public void newULTICON(){
		addULTICON(new upgrades(r.nextInt(400)+400,r.nextInt(400)+400,Game,this));
	}
	public void tick(){
		if(Game.pressUlt()){
			count++;
			if(count>40){
				
				count=0;
				for(int i=0;i<e.size();i++){
					tenemy = e.get(i);
					tenemy.setenemyhealth(1000);
					if(tenemy.getenemyhealth()<0){
						removeenemy(tenemy);
						Game.setenemyD(Game.getenemyD()+1);
						Game.setenemyT(Game.getenemyT()+1);
					}
					Game.point+=1000;
					Game.setpressUlt(false);
				}
				for(int i=0;i<bo.size();i++){
					tboss = bo.get(i);
					tboss.setbosshealth(2000);
					if(tboss.getbosshealth()<0){
						removeboss(tboss);
						Game.setbossD(Game.getbossD()+1);
					}
					Game.point+=1000;
					Game.setpressUlt(false);
				}
				ultimate.clear();
			}
		}
		for(int i =0;i<b.size();i++){
			tbullet = b.get(i);
			if(tbullet.gety()<0||tbullet.getx()<0||tbullet.getx()>1240||tbullet.gety()>920){
				removebullet(tbullet);
			}
			if(tbullet.getdirection()==1){
				tbullet.ticku();
			}
			if(tbullet.getdirection()==2){
				tbullet.tickd();
			}
			if(tbullet.getdirection()==3){
				tbullet.tickr();
			}
			if(tbullet.getdirection()==4){
				tbullet.tickl();
			}		
		}
		for(int i =0;i<ultimate.size();i++){
			tultimate = ultimate.get(i);
			tultimate.tick();
		}
		
		for(int i =0;i<lazer.size();i++){
			tlazer = lazer.get(i);
			if(tlazer.gety()<0||tlazer.getx()<0||tlazer.getx()>1240||tlazer.gety()>920){
				removeLAZER(tlazer);
			}
			if(tlazer.getdirection()==1){
				tlazer.ticku();
			}
			if(tlazer.getdirection()==2){
				tlazer.tickd();
			}
			if(tlazer.getdirection()==3){
				tlazer.tickr();
			}
			if(tlazer.getdirection()==4){
				tlazer.tickl();
			}		
		}
		for(int i =0;i<iceball.size();i++){
			ticeball = iceball.get(i);
			if(ticeball.gety()<0||ticeball.getx()<0||ticeball.getx()>1240||ticeball.gety()>920){
				removeICE(ticeball);
			}
			if(ticeball.getdirection()==1){
				ticeball.ticku();
			}
			if(ticeball.getdirection()==2){
				ticeball.tickd();
			}
			if(ticeball.getdirection()==3){
				ticeball.tickr();
			}
			if(ticeball.getdirection()==4){
				ticeball.tickl();
			}		
		}
		for(int i=0;i<e.size();i++){
			tenemy = e.get(i);
			tenemy.tick();
		}
		for(int i=0;i<bo.size();i++){
			tboss = bo.get(i);
			tboss.tick();
		}
	}
	public void render(Graphics g){
		for(int i = 0 ;i<ut.size();i++){
			tut =ut.get(i);
			tut.renderULT(g);
		}
		for(int i = 0 ;i<b.size();i++){
			tbullet =b.get(i);
			tbullet.render(g);
		}
		for(int i = 0 ;i<e.size();i++){
			tenemy =e.get(i);
			if(tenemy.getdirection()==1)
				tenemy.renderr(g);
			else if(tenemy.getdirection()==2)
				tenemy.renderl(g);
			else if(tenemy.getdirection()==3)
				tenemy.renderu(g);
			else if(tenemy.getdirection()==4)
				tenemy.renderd(g);
		}
		for(int i = 0 ;i<bo.size();i++){
			tboss =bo.get(i);
			if(tboss.getdirection()==1)
				tboss.renderr(g);
			else if(tboss.getdirection()==2)
				tboss.renderl(g);
			else if(tboss.getdirection()==3)
				tboss.renderu(g);
			else if(tboss.getdirection()==4)
				tboss.renderd(g);
		}
		for(int i = 0 ;i<hp.size();i++){
			thp =hp.get(i);
			thp.renderHP(g);
		}
		for(int i = 0 ;i<lz.size();i++){
			tlz =lz.get(i);
			tlz.renderLAZER(g);
		}
		for(int i = 0 ;i<lazer.size();i++){
			tlazer =lazer.get(i);
			if(tlazer.getdirection()==1)
				tlazer.renderd(g);
			else if(tlazer.getdirection()==2)
				tlazer.renderu(g);
			else if(tlazer.getdirection()==3)
				tlazer.renderl(g);
			else if(tlazer.getdirection()==4)
				tlazer.renderr(g);
			
		}
		for(int i = 0 ;i<ultimate.size();i++){
			tultimate =ultimate.get(i);
			tultimate.render(g);		
		}
		for(int i = 0 ;i<ib.size();i++){
			tib =ib.get(i);
			tib.renderICE(g);
		}
		for(int i = 0 ;i<iceball.size();i++){
			ticeball =iceball.get(i);
			if(ticeball.getdirection()==1)
				ticeball.renderd(g);
			else if(ticeball.getdirection()==2)
				ticeball.renderu(g);
			else if(ticeball.getdirection()==3)
				ticeball.renderl(g);
			else if(ticeball.getdirection()==4)
				ticeball.renderr(g);
			
		}
	}


	public void addbullet(bullet block){
		b.add(block);
	}
	public void removebullet(bullet block){
		b.remove(block);
	}
	public void addenemy(enemy block){
		e.add(block);
	}
	public void removeenemy(enemy block){
		e.remove(block);
	}
	public void addboss(boss block){
		bo.add(block);
	}
	public void removeboss(boss block){
		bo.remove(block);
	}
	public void addHP(upgrades block){
		hp.add(block);
	}
	public void removeHP(upgrades block){
		hp.remove(block);
	}
	public void addLAZERICON(upgrades block){
		lz.add(block);
	}
	public void removeLAZERICON(upgrades block){
		lz.remove(block);
	}
	public void addLAZER(lazer block){
		lazer.add(block);
	}
	public void removeLAZER(lazer block){
		lazer.remove(block);
	}
	//////ultimate
	public void addULTICON(upgrades block){
		ut.add(block);
	}
	public void removeULTICON(upgrades block){
		ut.remove(block);
	}
	public void addULT(ultimate block){
		ultimate.add(block);
	}
	public void removeULT(ultimate block){
		ultimate.remove(block);
	}
	//////iceball
	public void addICEICON(upgrades block){
		ib.add(block);
	}
	public void removeICEICON(upgrades block){
		ib.remove(block);
	}
	public void addICE(iceball block){
		iceball.add(block);
	}
	public void removeICE(iceball block){
		iceball.remove(block);
	}
	////
	public int  getpoint(){
		return tenemy.getpoint();
	}
	public LinkedList<bullet> getbullet(){
		return b;
	}
	public LinkedList<enemy> getenemy(){
		return e;
	}
	public LinkedList<boss> getboss(){
		return bo;
	}
	public LinkedList<upgrades> getHP(){
		return hp;
	}
	public LinkedList<upgrades> getLAZERICON(){
		return lz;
	}
	public LinkedList<upgrades> getICEICON(){
		return ib;
	}
	public LinkedList<upgrades> getULTICON(){
		return ut;
	}
	public LinkedList<lazer> getLAZER(){
		return lazer;
	}
	
	public LinkedList<iceball> getICE(){
		return iceball;
	}
	public LinkedList<ultimate> getultimate(){
		return ultimate;
	}
	public double getBossX(){
		return tboss.getx();
	}
	public double getBossY(){
		return tboss.gety();
	}
	public double getBossHealth(){
		return tboss.getbosshealth();
	}
	public void setremoveult(boolean a){
		removeult=a;
	}
}
