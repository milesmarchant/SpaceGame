package entity;

import java.awt.Graphics2D;

import physics.Collision.CircleHitBox;
import physics.Vec2;
import scene.Scene;
import sprite.SpriteStore;
import core.GameWindow;

public class Bullet extends Entity<CircleHitBox>{
			
	//TODO? apply DRY to constructors for ease of reading
	
	/**
	 * 
	 * @param direction is a degree value. If a number <0 or >359 is given, it will simplify to 0<direction<360 
	 */
	public Bullet(double speed, double direction, double x, double y, int sourceCollisionType){
		double[] vectorCoords = Vec2.convertToRect(speed, direction);
		vel = new Vec2(vectorCoords[0], vectorCoords[1]);
		this.x = x;
		this.y = y;
		id = Entity.generateNewId("Bullet");
		sprite = SpriteStore.get().getSprite("bullet.png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight()/2;
		hitBox = new CircleHitBox(this, 4);
		collisionType = sourceCollisionType;
		collisionTargets = new int[]{sourceCollisionType == 1 ? 2:1};
	}
	
	public Bullet(Vec2 vel, double x, double y, int sourceCollisionType){
		this.vel = vel;
		this.x = x;
		this.y = y;
		sprite = SpriteStore.get().getSprite("bullet.png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight()/2;
		hitBox = new CircleHitBox(this, 4);
		collisionType = sourceCollisionType;
		collisionTargets = new int[]{sourceCollisionType == 1 ? 2:1};
	}
	
	@Override
	public void init(){
	}

	@Override
	public void update(Scene scene) {
		x += vel.x;
		y += vel.y;
		if(x < 0 || x > GameWindow.WIN_WIDTH || y < 0 || y > GameWindow.WIN_HEIGHT){
			scene.removeEntity(this);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g, (int)x-spriteXOffset, (int)y+spriteYOffset);
	}
	
	@Override
	public void onCollision(Scene scene, Entity<?> e){
		if(e instanceof Bullet){
			return;
		}
		scene.removeEntity(this);
	}
	
	@Override
	public String toString(){
		return "Bullet";
	}

}
