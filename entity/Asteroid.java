package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import core.GameWindow;
import physics.Collision.CircleHitBox;
import physics.Vec2;
import scene.Scene;
import sprite.SpriteStore;

public class Asteroid extends Entity<CircleHitBox>{
	
	public Asteroid(double x, double y){
		this(x, y, new Vec2(0, -8));
	}
	
	public Asteroid(double x, double y, Vec2 vel){
		sprite = SpriteStore.get().getSprite("asteroid.png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight()/2;
		id = Entity.generateNewId("Asteroid");
		this.x = x;
		this.y = y;
		this.vel = vel;
		hitBox = new CircleHitBox(this, 12);
		collisionType = Entity.COLLISION_GROUP_ENEMY;
		collisionTargets = new int[]{Entity.COLLISION_GROUP_PLAYER};
	}
	
	@Override
	public void init(){
	}
	
	@Override
	public void update(Scene scene) {
		x += vel.x;
		y += vel.y;
		if(y < 0 || Math.abs(x)>5000 || Math.abs(y)>5000){
			scene.removeEntity(this);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g, (int)x-spriteXOffset, (int)y+spriteYOffset);
	}
	
	@Override
	public void onCollision(Scene scene, Entity<?> e){
		scene.removeEntity(this);
	}
	
	@Override
	public String toString(){
		return "Asteroid";
	}

}
