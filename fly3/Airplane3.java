package cn.just.fly3;

public class Airplane3 extends Flying {
		public Airplane3(){
				image = Game.airplane3;
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
			
			public boolean out(){
				return y > Game.HEIGHT;
			}

}
