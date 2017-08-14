package cn.just.fly3;

import java.awt.image.BufferedImage;

public class Bomb1 {
		static int X=272;
		static int Y=356;
		static int w=50;
		static int h=40;
		int x,y;
		int life=18;
		public Bomb1(int x,int y){
				this.x=x;
				this.y=y;
		}
		public void lifeDown(){
				if(life>0)
				{
						life--;
				}
		}
}
