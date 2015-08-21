package entity;

import java.awt.Graphics2D;

import entity.powerup.Powerup;
import entity.powerup.Powerup.PowerupType;
import physics.Collision.RectHitBox;
import physics.Vec2;
import scene.Scene;
import sprite.SpriteStore;
import weapon.EnemyBasicGun;
import weapon.Weapon;

public class XEnemy extends Entity<RectHitBox>{
	
	int counter;
	double rotSpeed = 2;
	
	Weapon weapon;
		
	public XEnemy(double x, double y, Vec2 vel){
		this.x = x;
		this.y = y;
		this.vel = vel;
		id = Entity.generateNewId("XEnemy");
		sprite = SpriteStore.get().getSprite("x_enemy.png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight()/2;
		hitBox = new RectHitBox(this, sprite.getWidth(), sprite.getHeight());
		collisionType = Entity.COLLISION_GROUP_ENEMY;
		collisionTargets = new int[]{Entity.COLLISION_GROUP_PLAYER};
	}

	@Override
	public void init() {
		counter = 0;
		weapon = new EnemyBasicGun(this);
	}

	@Override
	public void update(Scene scene) {
		spriteAngle += rotSpeed;
		angle += rotSpeed;
		x += vel.x;
		y += vel.y;
		if(y < 0 || Math.abs(x)>5000 || Math.abs(y)>5000){
			scene.removeEntity(this);
		}
		if(scene.getEntity("Player") != null){
				weapon.fire(scene, counter);
		}
		counter++;
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g, (int)x-spriteXOffset, (int)y+spriteYOffset, spriteAngle);
	}

	@Override
	public void onCollision(Scene scene, Entity<?> e) {
		scene.addEntity(new Powerup(x, y, new Vec2(0, -3), scene.getPowerup(PowerupType.WEAPON_PLAYER_BASIC_GUN)));
		scene.removeEntity(this);
		if(e instanceof Laser){
			System.out.println("Destroyed by laser");
		}
	}

}
