package sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.GameWindow;

public class Sprite {
	
	private BufferedImage image;
	
	public Sprite(BufferedImage image){
		this.image = image;
	}
	
	public int getWidth(){
		return image.getWidth(null);
	}
	
	public int getHeight(){
		return image.getHeight(null);
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public void draw(Graphics2D g, int x, int y){
		g.drawImage(image, x, GameWindow.WIN_HEIGHT-y, null);
	}
	
	public void draw(Graphics2D g, int x, int y, double angle){
		g.rotate(-Math.toRadians(angle), x+getWidth()/2, GameWindow.WIN_HEIGHT-y+getHeight()/2);
		g.drawImage(image, x, GameWindow.WIN_HEIGHT-y, null);
		g.rotate(Math.toRadians(angle), x+getWidth()/2, GameWindow.WIN_HEIGHT-y+getHeight()/2);
	}

}
