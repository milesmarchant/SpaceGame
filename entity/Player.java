package entity;

import java.awt.Graphics2D;

import physics.Collision.PointHitBox;
import scene.Scene;
import sprite.SpriteStore;
import weapon.PlayerBasicGun;
import weapon.PlayerLaser;
import weapon.Weapon;
import core.KeyStatus;

public class Player extends Entity<PointHitBox> {
	
	private KeyStatus keyTracker;
	
	private double speedUp = 10;
	private double speedDown = 10;
	private double speedLeft = 10;
	private double speedRight = 10;
	
	private int counter;
	private int health;
	
	private Weapon weapon;
	
	public Player(KeyStatus keyTracker, double x, double y){
		this.keyTracker = keyTracker;
		this.x = x;
		this.y = y;
		id = "Player";
		sprite = SpriteStore.get().getSprite("player.png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight()/2;
		hitBox = new PointHitBox(this);
		collisionType = Entity.COLLISION_GROUP_PLAYER;
		collisionTargets = new int[]{Entity.COLLISION_GROUP_ENEMY, Entity.COLLISION_GROUP_POWERUP};
	}
	
	@Override
	public void init(){
		counter = 0;
		health = 3;
		weapon = new PlayerLaser(this);
	}

	@Override
	public void update(Scene scene) {
		if(keyTracker.getKey(keyTracker.KEY_UP)){
			y += speedUp;
		}
		if(keyTracker.getKey(keyTracker.KEY_DOWN)){
			y -= speedDown;
		}
		if(keyTracker.getKey(keyTracker.KEY_LEFT)){
			x -= speedLeft;
		}
		if(keyTracker.getKey(keyTracker.KEY_RIGHT)){
			x += speedRight;
		}
		if(keyTracker.getKey(keyTracker.KEY_SPACE)){
			weapon.fire(scene, counter);
		}
		counter++;
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g, (int)x-spriteXOffset, (int)y+spriteYOffset);
	}
	
	@Override
	public void onCollision(Scene scene, Entity<?> e){
		if(e.getCollisionType() == Entity.COLLISION_GROUP_ENEMY){
			health--;
			System.out.println("health: "+health);
			if(health <= 0){
				System.out.println("dead");
				scene.removeEntity(this);
			}
		}
		if(e.getCollisionType() == Entity.COLLISION_GROUP_POWERUP){
			
		}
	}
	
	@Override
	public String toString(){
		return "Player";
	}

}
