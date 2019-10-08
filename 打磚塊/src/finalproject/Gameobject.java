package finalproject;

import java.awt.Rectangle;

public class Gameobject {
public double x;
public double y;
Gameobject(double x, double y){
	this.x=x;
	this.y=y;
}
public Rectangle getbounds(){
	return new Rectangle((int)x , (int) y,40,40);
}
}
