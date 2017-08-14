package cn.just.fly3;

import java.awt.image.BufferedImage;

public abstract class Flying {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	//走步
	public abstract void speed();
	
	//越界处理
	public abstract boolean out();
	
	public boolean afoul(Flying f){
		int x = this.x + this.width/2;
		int y = this.y + this.height/2;
		int x1 = f.x - this.width/2;
		int y1 = f.y - this.height/2;
		int x2 = f.x + f.width + this.width/2;
		int y2 = f.y + f.height + this.height/2;
		if(x >= x1 && x <= x2 
				&& y >= y1 && y <= y2){
			return true;
		}else{
			return false;
		}
//		return x >= x1 && x <= x2 
//				&& y >= y1 && y <= y2;
	}
}
