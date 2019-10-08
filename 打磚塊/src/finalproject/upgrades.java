package finalproject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class upgrades {
	private double x;
	private double y;
	private boolean draw;
	private game Game;
	private controller c;
	private BufferedImage HP;
	private BufferedImage iceball;
	private BufferedImage lazer;
	private BufferedImage ultimate;
	public upgrades(double x, double y, game Game,controller c){
		this.x=x;
		this.y=y;
		this.Game=Game;
		this.c=c;
		HP=Game.HP;
		lazer=Game.lazer[2];
		iceball=Game.icebug[4];
		ultimate= Game.ultimate[3];
	}
	public void renderHP(Graphics g){
		g.drawImage(HP, (int)x, (int)y, null);
	}
	public void renderULT(Graphics g){
		g.drawImage(ultimate, (int)x, (int)y, null);
	}
	public void renderICE(Graphics g){
		g.drawImage(iceball, (int)x, (int)y, null);
	}
	public void renderLAZER(Graphics g){
		g.drawImage(lazer, (int)x, (int)y, null);
	}
	public Rectangle getbounds(){
		return new Rectangle((int)x , (int) y,40,40);
	}
}
