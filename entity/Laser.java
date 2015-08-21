package entity;

import java.awt.Graphics2D;

import physics.Collision.RectHitBox;
import scene.Scene;
import sprite.SpriteStore;

public class Laser extends Entity<RectHitBox>{
	
	int remainingTime;
	Entity<?> source;
	double sourceXOffset;
	double sourceYOffset;

	public Laser(double x, double y, int sourceCollisionType, Entity<?> source, double sourceXOffset, double sourceYOffset){
		this.x = x;
		this.y = y;
		this.source = source;
		this.sourceXOffset = sourceXOffset;
		this.sourceYOffset = sourceYOffset;
		sprite = SpriteStore.get().getSprite("laser.png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight();
		hitBox = new RectHitBox(this, new double[]{4, -4, -4, 4}, new double[]{1500, 1500, 0, 0});
		collisionType = sourceCollisionType;
		collisionTargets = new int[]{sourceCollisionType == 1 ? 2:1};
	}

	@Override
	public void init() {
		remainingTime = 30;
	}

	@Override
	public void update(Scene scene) {
		x = source.getX() + sourceXOffset;
		y = source.getY() + sourceYOffset;
		remainingTime--;
		if(remainingTime <= 0){
			scene.removeEntity(this);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g, (int)x-spriteXOffset, (int)y+spriteYOffset);
	}

	@Override
	public void onCollision(Scene scene, Entity<?> e) {
	}

}
