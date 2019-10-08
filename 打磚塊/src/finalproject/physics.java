package finalproject;

import java.util.LinkedList;

public class physics {
	public static boolean collision(enemy e,bullet b){	
		if(e.getbounds().intersects(b.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(enemy e,lazer b){	
		if(e.getbounds().intersects(b.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(enemy e,iceball b){	
		if(e.getbounds().intersects(b.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(boss e,bullet b){	
		if(e.getbounds().intersects(b.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(boss e,lazer b){	
		if(e.getbounds().intersects(b.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(boss e,iceball b){	
		if(e.getbounds().intersects(b.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(bullet b,enemy e){	
		if(b.getbounds().intersects(e.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(player p,enemy e){	
		if(p.getbounds().intersects(e.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(bullet b,boss e){	
		if(b.getbounds().intersects(e.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(player p,boss e){	
		if(p.getbounds().intersects(e.getbounds()))
				return true;
		
		return false;
	}
	public static boolean collision(player p,upgrades u){	
		if(p.getbounds().intersects(u.getbounds()))
				return true;
		
		return false;
	}
}
