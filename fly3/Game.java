package cn.just.fly3;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JPanel{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 666;
	Random rand = new Random();
	Flying[] flys = {};
	Hero hero = new Hero();
	Bullet[] bullets = {};
	Timer timer;
	Bomb1[] bomb={};
	private int score;
	public int state;
	public static final int START = 0;	
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;	
	public static final int GAME_OVER = 3;
	
	public static BufferedImage background = null;
	public static BufferedImage airplane1 = null;
	public static BufferedImage hero0 = null;
	public static BufferedImage hero1 = null;
	public static BufferedImage bullet2 = null;
	public static BufferedImage start = null;
	public static BufferedImage pause = null;
	public static BufferedImage gameover = null;
	public static BufferedImage bee = null;
	public static BufferedImage airplane2 = null;
	public static BufferedImage airplane3=null;
	public static BufferedImage bomb1=null;
	static{
		try {
			background = ImageIO.read
					(Game.class.getResource
							("background.png"));
			airplane1 = ImageIO.read(Game.class.getResource("airplane1.png"));
			hero0 = ImageIO.read(Game.class.getResource("hero0.png"));
			hero1 = ImageIO.read(Game.class.getResource("hero1.png"));
			bullet2 = ImageIO.read(Game.class.getResource("bullet2.png"));
			start = ImageIO.read(Game.class.getResource("start.png"));
			pause = ImageIO.read(Game.class.getResource("pause.png"));
			gameover = ImageIO.read(Game.class.getResource("gameover.png"));
			bee = ImageIO.read(Game.class.getResource("bee.png"));
			 airplane2 = ImageIO.read(Game.class.getResource("airplane2.png"));
			 airplane3=ImageIO.read(Game.class.getResource("airplane3.png"));
			 bomb1=ImageIO.read(Game.class.getResource("bomb1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�����Զ�����
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(background, 0, 0, null);
		flyPaint(g);
		statePaint(g);
		scorePaint(g);
	}
	
	//���ɻ�
	public void flyPaint(Graphics g){
		//��С�ɻ�
		for(int i=0;i<flys.length;i++){
			g.drawImage(flys[i].image, flys[i].getX(),
					flys[i].y, null);
		}
		//���ӵ�
		for(int i=0;i<bullets.length;i++){
			g.drawImage(bullets[i].image,
				bullets[i].x, bullets[i].y, null);
		}
		for(int i=0;i<bomb.length;i++){
				
				g.drawImage(bomb1, bomb[i].x, bomb[i].y,null);
		}
		//��Ӣ�ۻ�
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
	//��״̬
	public void statePaint(Graphics g){
		switch (state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		
		}
	}
	
	//����,����
	public void scorePaint(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(new Font
				("΢���ź�", Font.BOLD, 20));
		g.drawString("life��"+hero.getLife(),
				14, 30);
		g.drawString("score��"+score, 14, 55);
		
	}
	
	//�����һ�
	int cIndex = 0;
	public void createAction(){
		cIndex++;
		if(cIndex % 50 == 0){
			Flying fly = nextOneFly();
			flys = Arrays.copyOf(flys, flys.length+1);
			flys[flys.length-1] = fly;
		}
		if(cIndex % 30 == 0){
			Bullet[] b = 
					hero.shoot();
			bullets = Arrays.copyOf
					(bullets, bullets.length+b.length);
			System.arraycopy
			(b, 0, bullets, 
					bullets.length-b.length, 
					b.length);
			
//			bullets[bullets.length-1] = b[0];
//			bullets[bullets.length-2] = b[1];
		}
	}

	
	//�һ��߲�
	public void speedAction(){
		for(int i=0;i<flys.length;i++){
			flys[i].speed();
		}
		for(int i=0;i<bullets.length;i++){
			bullets[i].speed();
		}
		hero.speed();
	}
	
	public void delOutAction(){
		for(int i=0;i<flys.length;i++){
			if(flys[i].out()){
				Flying f = flys[i];
				flys[i] = flys[flys.length-1];
				flys[flys.length-1] = f;
				flys = Arrays.copyOf(flys, flys.length-1);
			}
		}
		for(int i=0;i<bullets.length;i++){
			if(bullets[i].out()){
				//���Խ�磬����λ��
				Bullet b = bullets[i];
				bullets[i] = bullets[bullets.length-1];
				bullets[bullets.length-1] = b;
				//����
				bullets = Arrays.copyOf
						(bullets, bullets.length-1);
			}
		}
	}
	
	//��ײ
	public void afoulAction(){
		for(int i=0;i<bullets.length;i++){
			for(int j=0;j<flys.length;j++){
				if(flys[j].afoul(bullets[i])){
					score += 5;
					Flying f = flys[j];
					Flying t = flys[j];
					flys[j] = flys[flys.length-1];
					flys[flys.length-1] = t;
					flys = Arrays.copyOf
							(flys, flys.length-1);
					if(f instanceof Bee){
						int aw = rand.nextInt(2);
						if(aw == 0){
							hero.addLife();
						}else{
							hero.addFire();
						}
					}
				}
			}
		}
		
		for(int i=0;i<flys.length;i++){
			if(flys[i].afoul(hero)){
				if(hero.getLife() <= 0){
					state = GAME_OVER;
					hero = new Hero();
					flys = new Flying[0];
					bullets = new Bullet[0];	
				}else{
					//����
					hero.subLife();
					//ɾ����ײ��ĵл�
					Flying f = flys[i];
					flys[i] = flys[flys.length-1];
					flys[flys.length-1] = f;
					flys = Arrays.copyOf(flys, flys.length-1);
				}
			}
		}
	}
	
	//����һ���һ�
	public Flying nextOneFly(){
		int type = rand.nextInt(30);
		if(type >= 26){
			return new Bee();
		}else if(type >= 10){
			return new Airplane2();
		}else if(type>=12){
			return new Airplane3();
				
		}{
			return new Airplane1();
		}
	}
	
	public void action(){
		//��ʱ��
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(state == RUNNING){
					createAction();
					speedAction();
					delOutAction();
					afoulAction();
				}
				repaint();
			}
		}, 10, 10);
		
		//Ӣ�ۻ�����궯
		//������
		this.addMouseMotionListener(
				new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state == RUNNING){
					int x = e.getX();
					int y = e.getY();
					hero.move(x, y);
				}
			}
		});
		
		//������������Ƴ�
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(state == PAUSE){
					state = RUNNING;
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(state == RUNNING){
					state = PAUSE;
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(state == START){
					state = RUNNING;
				}
				if(state == GAME_OVER){
					state = START;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		//��������
		JFrame frame = new JFrame("�һ���ս");
		Game g = new Game();
		//�������ӵ�������
		frame.add(g);
		//���ô��ڴ�С
		frame.setSize(WIDTH, HEIGHT);
		//���ھ���
		frame.setLocationRelativeTo(null);
		//����һֱ����
		frame.setAlwaysOnTop(true);
		//���ڴ�С���ɱ�
		frame.setResizable(false);
		//�رմ���ʱ���������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڿɼ�show
		frame.setVisible(true);
		
		g.action();
	}
}
