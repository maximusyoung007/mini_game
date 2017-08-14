package cn.just.fly3;

public class Bullet extends Flying{
	public Bullet(int x, int y){
		//获取Game类中加载的图片
		image = Game.bullet2;
		//获取图片的宽
		width = image.getWidth();
		height = image.getHeight();
		//初始的x，y坐标
		this.x = x - this.width/2;
		this.y = y - 20;
	}
	
	//子弹走步
	public void speed(){
		y -= 3;
	}

	@Override
	public boolean out() {
		return y < -height;
	}
}