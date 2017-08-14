package cn.just.fly3;

public class Airplane3 extends Flying {
		public Airplane3(){
				image = Game.airplane3;
				width = image.getWidth();
				height = image.getHeight();
				//Math.random()，0-1之间的随机小数
				x = (int)(Math.random()*(Game.WIDTH-width-12));
				y = -height;
			}
			
			//下落，走步
			public void speed(){
				y++;
			}
			
			public boolean out(){
				return y > Game.HEIGHT;
			}

}
