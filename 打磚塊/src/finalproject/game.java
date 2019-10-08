package finalproject;
import java.awt.Canvas;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
public class game extends Canvas implements Runnable{
	public static final int WIDTH =320;
	public static final int HEIGHT =240;
	public static final int SCALE =4;
	private static final int BX = 300;  // location x 
	private static final int BY = 50; 
	public final String TITLE = "BOXHEAD";
	
	public static int[][] level1 ={
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};	
	private boolean running = false; 
	private boolean pressult=false;
	private static boolean enter = false;
	private int a=0;
	private boolean restart=false;
	private Thread thread;
	private boolean pause=true;
	private int level=1;
	public static boolean f=false;
	private BufferedImage bk = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
	private BufferedImage boxhead;
	private BufferedImage Enemy;
	private BufferedImage floor;
	private BufferedImage wall;
	private static JFrame frame2;
	private static JFrame frame;
	public BufferedImage HP;
	public BufferedImage[] lazer = new BufferedImage[3];
	public BufferedImage[] icebug = new BufferedImage[5];
	private static int enemyC=5;
	private int enemyD;
	private int bossD;
	private int enemyT;
	public int HPC;
	public int WPC;
	private int l=10;
	private int i=5;
	private int h=20;
	private int u=3;
	private int bss=35;
	private int count=0;
	private int weapon1;
	private Random r = new Random();
	public BufferedImage[] ultimate= new BufferedImage[5];
	public BufferedImage[] bullet= new BufferedImage[20];
	public BufferedImage[] player = new BufferedImage[20];
	public BufferedImage[] enemy = new BufferedImage[20];
	public BufferedImage[] boss = new BufferedImage[12];
	private double enemyhealth = 2000;
	public LinkedList<bullet> b;
	public LinkedList<enemy> e;
	public LinkedList<boss> bo;
	public LinkedList<upgrades> hp;
	public LinkedList<upgrades> lz;
	public LinkedList<upgrades> ib;
	public LinkedList<upgrades> ut;
	public LinkedList<lazer> lazer1;
	public LinkedList<iceball> iceball;
	public LinkedList<ultimate> ultimate2;
	public int lazeramount;
	public int iceamount;
	public int  ultamount;
	public int point=0;
	private static player p;
	private controller c;
	private static int direction = 0;
	public static double getx(){
		return p.getx();
	}
	public static double gety(){
		return p.gety();
	}
	public double getwidth(){
		return WIDTH;
	}
	public double getheight(){
		return HEIGHT;
	}

	public void drawlevel(Graphics g){
		for(int i=0;i<25;i++){
			for(int j=0;j<33;j++){
				switch(level1[i][j]){
				case 0:
					g.drawImage(floor, j*40, i*40, this);
					break;
				case 1:
					g.drawImage(wall, j*40, i*40, this);
					break;
				default:
					break;
				}
			}
		}
	}
	public static int getdirection(){
		return direction;
	}
	public void initialize(){
		 try
         {		
                boxhead=ImageIO.read(new File("image/boxhead.png"));
                wall=ImageIO.read(new File("image/wall.png"));
                ultimate[0]=ImageIO.read(new File("image/ult1.png"));
                ultimate[1]=ImageIO.read(new File("image/ult2.png"));
                ultimate[2]=ImageIO.read(new File("image/ult3.png"));
                ultimate[3]=ImageIO.read(new File("image/ulticon.png"));
                enemy[0]=ImageIO.read(new File("image/enemyr1.png"));
                enemy[1]=ImageIO.read(new File("image/enemyr2.png"));
                enemy[2]=ImageIO.read(new File("image/enemyr3.png"));
                enemy[3]=ImageIO.read(new File("image/enemyr4.png"));
                enemy[4]=ImageIO.read(new File("image/enemyl1.png"));
                enemy[5]=ImageIO.read(new File("image/enemyl2.png"));
                enemy[6]=ImageIO.read(new File("image/enemyl3.png"));
                enemy[7]=ImageIO.read(new File("image/enemyl4.png"));
                enemy[8]=ImageIO.read(new File("image/enemyu1.png"));
                enemy[9]=ImageIO.read(new File("image/enemyu2.png"));
                enemy[10]=ImageIO.read(new File("image/enemyu3.png"));
                enemy[11]=ImageIO.read(new File("image/enemyu4.png"));
                enemy[12]=ImageIO.read(new File("image/enemyd1.png"));
                enemy[13]=ImageIO.read(new File("image/enemyd2.png"));
                enemy[14]=ImageIO.read(new File("image/enemyd3.png"));
                enemy[15]=ImageIO.read(new File("image/enemyd4.png"));
                boss[0]=ImageIO.read(new File("image/bossr1.png"));
                boss[1]=ImageIO.read(new File("image/bossr2.png"));
                boss[2]=ImageIO.read(new File("image/bossr3.png"));
                boss[3]=ImageIO.read(new File("image/bossl1.png"));
                boss[4]=ImageIO.read(new File("image/bossl2.png"));
                boss[5]=ImageIO.read(new File("image/bossl3.png"));
                boss[6]=ImageIO.read(new File("image/bossu1.png"));
                boss[7]=ImageIO.read(new File("image/bossu2.png"));
                boss[8]=ImageIO.read(new File("image/bossu3.png"));
                boss[9]=ImageIO.read(new File("image/bossd1.png"));
                boss[10]=ImageIO.read(new File("image/bossd2.png"));
                boss[11]=ImageIO.read(new File("image/bossd3.png"));
                bullet[0]=ImageIO.read(new File("image/fireu1.png"));
                bullet[1]=ImageIO.read(new File("image/fired1.png"));
                bullet[2]=ImageIO.read(new File("image/firer1.png"));
                bullet[3]=ImageIO.read(new File("image/firel1.png"));
                bullet[4]=ImageIO.read(new File("image/fireu2.png"));
                bullet[5]=ImageIO.read(new File("image/fired2.png"));
                bullet[6]=ImageIO.read(new File("image/firer2.png"));
                bullet[7]=ImageIO.read(new File("image/firel2.png"));
                bullet[8]=ImageIO.read(new File("image/fireu3.png"));
                bullet[9]=ImageIO.read(new File("image/fired3.png"));
                bullet[10]=ImageIO.read(new File("image/firer3.png"));
                bullet[11]=ImageIO.read(new File("image/firel3.png"));
                floor=ImageIO.read(new File("image/floor.png"));
                player[0]=ImageIO.read(new File("image/r1.png"));
 	            player[1]=ImageIO.read(new File("image/r2.png"));
 	            player[2]=ImageIO.read(new File("image/r3.png"));
 	            player[3]=ImageIO.read(new File("image/r4.png"));
 	            player[4]=ImageIO.read(new File("image/l1.png"));
 	            player[5]=ImageIO.read(new File("image/l2.png"));
 	            player[6]=ImageIO.read(new File("image/l3.png"));
 	            player[7]=ImageIO.read(new File("image/l4.png"));
 	            player[8]=ImageIO.read(new File("image/u1.png"));
 	            player[9]=ImageIO.read(new File("image/u2.png"));
 	            player[10]=ImageIO.read(new File("image/u3.png"));
 	            player[11]=ImageIO.read(new File("image/u4.png"));
 	            player[12]=ImageIO.read(new File("image/d1.png"));
 	            player[13]=ImageIO.read(new File("image/d2.png"));
 	            player[14]=ImageIO.read(new File("image/d3.png"));
 	            player[15]=ImageIO.read(new File("image/d4.png"));
 	            HP=ImageIO.read(new File("image/HP.png"));
 	            lazer[0]=ImageIO.read(new File("image/lazerlr.png"));
 	            lazer[1]=ImageIO.read(new File("image/lazerud.png"));
 	            lazer[2]=ImageIO.read(new File("image/lazer.png"));
 	            icebug[0]=ImageIO.read(new File("image/uiceball.png"));
	            icebug[1]=ImageIO.read(new File("image/diceball.png"));
	            icebug[2]=ImageIO.read(new File("image/riceball.png"));
	            icebug[3]=ImageIO.read(new File("image/liceball.png"));
	            icebug[4]=ImageIO.read(new File("image/ice.png"));
         }
         catch(Exception e)
         {
                 javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤J¹ÏÀÉ¿ù»~: "+"image/player.png");
                 boxhead=null;
         }
		 lazeramount=0;
		 iceamount=0;
		 point=0;
		 enemyD=0;
		 enemyC=5;
		 WPC=0;
		 enemyT=0;
		 level=1;
		 l=5;
		 i=10;
		 u=15;
		 addKeyListener(new Keyinput(this));
		 c = new controller(this); 
		 p = new player(500,600,this,c,10000);
		 c.newenemy(enemyC);
		 b=c.getbullet();
		 e=c.getenemy();
		 bo=c.getboss();
		 hp=c.getHP();
		 lz=c.getLAZERICON();
		 ib=c.getICEICON();
		 ut=c.getULTICON();
		 lazer1=c.getLAZER();
		 iceball=c.getICE();
		 ultimate2=c.getultimate();
	}
	public void renew(){
		lazeramount=0;
		 iceamount=0;
		 point=0;
		 enemyD=0;
		 enemyC=5;
		 WPC=0;
		 enemyT=0;
		 level=1;
		 l=5;
		 i=10;
		 u=25;
		 h=20;
		 c = new controller(this); 
		 p = new player(500,600,this,c,10000);
		 c.newenemy(enemyC);
		 b=c.getbullet();
		 e=c.getenemy();
		 bo=c.getboss();
		 hp=c.getHP();
		 lz=c.getLAZERICON();
		 ib=c.getICEICON();
		 ut=c.getULTICON();
		 lazer1=c.getLAZER();
		 iceball=c.getICE();
		 ultimate2=c.getultimate();
	}
	public void tick(){
		if(pause&&!restart){
		f=p.getDeath();
		if(!f){
			restart=true;
			if(restart){
			renew();
		}				
		}
		p.tick();
		c.tick();	
		if(enemyD>=enemyC){
			level++;
			enemyC+=1;
			if(enemyC>10){
				enemyC=10;
				a++;
				if(a>5){
					a=5;
				}
				for(int i=0;i<a;i++){
					c.newBoss();
				}
				if(r.nextInt(2)+1==1){
					c.newLAZERICON();
				}
				else if(r.nextInt(2)+1==2){
					c.newICEICON();
				}
				else if(r.nextInt(2)+1==3){
					c.newULTICON();
				}
				
			}
			enemyD=0;
			c.newenemy(enemyC);
		}
		if(enemyT==h){
			c.newHP();
			h+=20;
		}
		if(enemyT==i){
			c.newICEICON();
			i+=40;
		}
		if(enemyT==u){
			c.newULTICON();
			u+=25;
		}
		if(enemyT==l){
			c.newLAZERICON();
			l+=25;
		}
		if(enemyT==bss){
			c.newBoss();
			bss+=35;
		}
	}
	}

	private synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	private synchronized void stop1(){
		if(!running)
			return;
		try{
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		running = false;
		System.exit(1);
	}
	public void run(){
		initialize();
		long lasttime = System.nanoTime();
		final double amountofticks = 60.0;
		double ns = 1000000000 / amountofticks;
		double delta = 0;
		int update = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now-lasttime) / ns;
			lasttime = now;
			if(delta >=1){
				tick();
				update++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer >1000){
				timer+=1000;
				System.out.println(update + " Ticks,fps "+ frames);
				System.out.println("lazeramount="+lazeramount);
				System.out.println("iceamount="+iceamount);
				System.out.println("ultamount="+ultamount);
				System.out.println("weapon="+p.getweapon());
				System.out.println("enemy="+enemyT);
				System.out.println("ult="+u);
				//
				update = 0;
				frames = 0;
			}
		}
		stop1();
	}
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
		;	createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(bk, 0, 0, getWidth(),getHeight(),this);	
		drawlevel(g);
		p.render(g);
		c.render(g);
		g.setColor(Color.WHITE);
		g.fillRect(1000, 5, 400, 30);
		Font ft = new Font("arial",Font.BOLD,40);
		g.setFont(ft);
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(point), 1010, 35);
		g.setColor(Color.GRAY);
		g.fillRect(0, 5, 400, 30);
		g.setColor(Color.GREEN);//health
		g.fillRect(0, 5, (int)p.getphealth()/25, 30);
		Font ft2 = new Font("arial",Font.BOLD,40);
		g.setFont(ft2);
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString((int)p.getphealth()/100)+"%",0, 35);
		Font ft3 = new Font("arial",Font.BOLD,40);
		g.setFont(ft3);
		g.setColor(Color.black);
		g.drawString("LEVEL  "+Integer.toString(level), 540, 35);
		if(!pause){
			Font ft4 = new Font("arial",Font.BOLD,60);
			g.setFont(ft4);
			g.setColor(Color.black);
			g.drawString("PAUSE", 540, 500);
		}
		if(restart){
			Font ft5 = new Font("arial",Font.BOLD,30);
			g.setFont(ft5);
			g.setColor(Color.black);
			g.drawString("Press enter to start again", 440, 400);
		}
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP&&pause){		
			p.setvely(-5);
			direction =1;
		}
		else if(key == KeyEvent.VK_DOWN&&pause){		
			p.setvely(5);
			direction =2;
		}
		else if(key == KeyEvent.VK_RIGHT&&pause){
			p.setvelx(5);
			direction =3;	
		}
		else if(key == KeyEvent.VK_LEFT&&pause){
			p.setvelx(-5);
			direction =4;				
		}
		else if(key == KeyEvent.VK_SPACE&&pause){
			if(p.getweapon()==1&&lazeramount>0){
				c.addLAZER(new lazer(p.getx(),p.gety(),this,direction,c));
				lazeramount-=1;
				if(lazeramount<=0){
					p.setweapon(0);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP&&pause){
			p.setvely(0);	
		}
		else if(key == KeyEvent.VK_DOWN&&pause){
			p.setvely(0);
		}
		else if(key == KeyEvent.VK_RIGHT&&pause){
			p.setvelx(0);
		}
		else if(key == KeyEvent.VK_LEFT&&pause){
			p.setvelx(0);
		}
		else if(key == KeyEvent.VK_Z&&pause){
			p.setweapon(0);
		}
		else if(key == KeyEvent.VK_S){
			pause=!pause;
		}
		else if(key == KeyEvent.VK_X&&pause){
			if(lazeramount>0){
				p.setweapon(1);
			}
		}
		else if(key == KeyEvent.VK_ENTER){
			if(!f){
				restart=false;
			}
		}
		else if(key == KeyEvent.VK_C&&pause){
			if(iceamount>0){
				p.setweapon(2);
			}
		}
		else if(key == KeyEvent.VK_V&&pause){
			if(ultamount>0){
				p.setweapon(3);
			}
		}
		else if(key == KeyEvent.VK_SPACE&&pause){
			if(p.getweapon()==0){
				c.addbullet(new bullet(p.getx(),p.gety(),this,direction,c));
			}
			else if (p.getweapon()==2&&iceamount>0){
				c.addICE(new iceball(p.getx(),p.gety(),this,direction,c));
				iceamount-=1;
				if(iceamount<=0){
					p.setweapon(0);
				}
			}
			else if (p.getweapon()==3&&ultamount>0){
				c.addULT(new ultimate(p.getx()-40,p.gety()-40,this,direction,c,p));
				ultamount-=1;
				pressult=true;
				if(ultamount<=0){
					p.setweapon(0);
				}
			}
				
		}
	}

	public static void main(String arg[]){
		startmenu();
		//GameMenu();
		///////game frame
	}
	public static void startmenu(){
		
	frame = new JFrame("Start");
	frame.setSize(1280, 960);
	JPanel pa = new JPanel(new GridBagLayout());
	pa.setBackground(Color.BLACK);
	pa.setLayout(null);
	Icon b2 = new ImageIcon("image/start.png");
	JButton b = new JButton(b2);
	b.setBounds(500, 400, 250, 140);
	b.setBorder(BorderFactory.createEmptyBorder());
	b.setContentAreaFilled(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	pa.add(b);
	frame.add(pa);	
	b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
		frame.setVisible(false);
		GameMenu();	
		}
	});
	}
	
	public static void GameMenu(){
		game Game = new game();
		Game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		Game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		Game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));	
		frame2 = new JFrame(Game.TITLE);
		frame2.setSize(1280,960);
		frame2.add(Game);
		frame2.pack();
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setResizable(false);
		frame2.setLocationRelativeTo(null);
		frame2.setVisible(true);			
		Game.start();
		System.out.println("fuck");
	}
	public BufferedImage getboxhead(){
		return boxhead;
	}
	
	public BufferedImage getenemy(){
		return Enemy;
	}
	public BufferedImage[] getpu(){
		return player;
	}
	public int getenemyC(){
		return enemyC;
	}
	public void setenemyC(int a){
		enemyC=a;
	}
	public int getenemyD(){
		return enemyD;
	}
	public void setenemyD(int a){
		enemyD=a;
	}
	public int getbossD(){
		return bossD;
	}
	public void setbossD(int a){
		bossD=a;
	}
	public int getenemyT(){
		return enemyT;
	}
	public void setenemyT(int a){
		enemyT=a;
	}
	public int getHP(){
		return HPC;
	}
	public void setHP(int a){
		HPC = a;
	}
	public void setWeapon(int a){
		WPC = a;
	}
	public void setlazeramount(int j) {
		lazeramount=j;
	}
	public int getlazeramount() {
		return lazeramount;
	}
	public void seticeamount(int j) {
		iceamount=j;
	}
	public int geticeamount() {
		return iceamount;
	}
	public void setultamount(int j) {
		ultamount=j;
	}
	public int getultamount() {
		return ultamount;
	}
	public boolean getRestart(){
		return restart;
	}
	public boolean pressUlt(){
		return pressult;
	}
	public void setpressUlt(boolean a){
		pressult=a;
	}
}