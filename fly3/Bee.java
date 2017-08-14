package cn.just.fly3;

import java.util.Random;

public class Bee extends Flying{

	int xSpeed = 3;
	
	Random rand = new Random();
	public Bee(){
		image = Game.bee;
		width = image.getWidth();
		height = image.getHeight();
		//[0,n)
		x = rand.nextInt(Game.WIDTH-width-12);
		y = -height;
	}
	
	@Override
	public void speed() {
		y++;
		x+=xSpeed;
		if(x >= Game.WIDTH-width-12){
			xSpeed = -3;
		}
		if(x <= 0){
			xSpeed = 3;
		}
	}

	@Override
	public boolean out() {
		return y > Game.HEIGHT;
	}
	
}
