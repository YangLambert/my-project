package finalproject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyinput extends KeyAdapter{
	game Game;
	public Keyinput(game Game){
		this.Game=Game;
	}
	public void keyPressed(KeyEvent e){
		Game.keyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		Game.keyReleased(e);
	}
}
