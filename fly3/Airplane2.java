package cn.just.fly3;

public class Airplane2 extends Flying{
	
	public Airplane2(){
		image = Game.airplane2;
		width = image.getWidth();
		height = image.getHeight();
		//Math.random()��0-1֮������С��
		x = (int)(Math.random()*(Game.WIDTH-width-12));
		y = -height;
	}
	
	//���䣬�߲�
	public void speed(){
		y++;
	}

	@Override
	public boolean out() {
		return y > Game.HEIGHT;
	}
	
	//����������
	
	
	/*
	public boolean afoul(Hero b){
		int x = this.x + this.width/2;
		int y = this.y + this.height/2;
		int x1 = b.x - this.width/2;
		int y1 = b.y - this.height/2;
		int x2 = b.x + b.width + this.width/2;
		int y2 = b.y + b.height + this.height/2;
		if(x >= x1 && x <= x2 
				&& y >= y1 && y <= y2){
			return true;
		}else{
			return false;
		}
//		return x >= x1 && x <= x2 
//				&& y >= y1 && y <= y2;
	}
	*/
}
