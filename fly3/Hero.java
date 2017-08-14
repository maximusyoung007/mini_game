package cn.just.fly3;

import java.awt.image.BufferedImage;

public class Hero extends Flying{
	
	//��
	private int life;
	//����
	private int fire;

	BufferedImage[] images = {};
	
	public Hero(){
		life = 3;
		image = Game.hero0;
		images = new BufferedImage[]
				{Game.hero0,Game.hero1};
		width = image.getWidth();
		height = image.getHeight();
		x = Game.WIDTH/2-width/2-6;
		y = Game.HEIGHT/5*3;
	}
	
	//������
	public void subFire(){
		fire--;
	}
	
	//�ӻ���
	public void addFire(){
		fire += 40;
	}
	
	//��ȡ����
	public int getFire() {
		return fire;
	}

	//���û���
	public void setFire(int fire) {
		this.fire = fire;
	}
	
	//����
	public void addLife(){
		life++;
	}
	
	//����
	public void subLife(){
		life--;
	}
	//��ȡ��
	public int getLife() {
		return life;
	}
	//������
	public void setLife(int life) {
		this.life = life;
	}

	//�ƶ�
	public void move(int x, int y){
		this.x = x - this.width/2;
		this.y = y - this.height/2;
	}
	
	//���ӵ�
	public Bullet[] shoot(){
		int x = this.width/4;
		System.out.println(getFire());
		if(fire <= 0){
			Bullet[] bulls = new Bullet[1];
			bulls[0] = new Bullet(this.x + 2 * x, y);
			return bulls;
		}else{
			Bullet[] bulls = new Bullet[2];
			bulls[0] = new Bullet(this.x + x, y);
			bulls[1] = new Bullet(this.x + 3 * x, y);
			fire--;
			return bulls;
		}
	}

	int index = 0;
	@Override
	public void speed() {
		image = images[index++ / 20 % images.length];
	}

	@Override
	public boolean out() {
		return false;
	}
}
