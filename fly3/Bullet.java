package cn.just.fly3;

public class Bullet extends Flying{
	public Bullet(int x, int y){
		//��ȡGame���м��ص�ͼƬ
		image = Game.bullet2;
		//��ȡͼƬ�Ŀ�
		width = image.getWidth();
		height = image.getHeight();
		//��ʼ��x��y����
		this.x = x - this.width/2;
		this.y = y - 20;
	}
	
	//�ӵ��߲�
	public void speed(){
		y -= 3;
	}

	@Override
	public boolean out() {
		return y < -height;
	}
}