package entity.powerup;

import java.awt.Graphics2D;

import core.GameWindow;
import physics.Collision.RectHitBox;
import physics.Vec2;
import scene.Scene;
import sprite.SpriteStore;
import entity.Entity;
import entity.Player;

public class Powerup extends Entity<RectHitBox>{
	
	PowerupType type;
	
	public enum PowerupType{
		WEAPON_PLAYER_BASIC_GUN("weapon_player_basic_gun", 0);
		
		public String name;
		public int type;
		
		PowerupType(String name, int type){
			this.name = name;
			this.type = type;
		}
		
		public static PowerupType getByType(int powerupType){
			switch(powerupType){
				case 0:
					return WEAPON_PLAYER_BASIC_GUN;
				default:
					return null;
			}
		}
	}

	public Powerup(double x, double y, Vec2 vel, PowerupType type) {
		this.x = x;
		this.y = y;
		this.vel = vel;
		this.type = type;
		setupPowerup();
		collisionType = Entity.COLLISION_GROUP_POWERUP;
		collisionTargets = new int[]{Entity.COLLISION_GROUP_PLAYER};
	}

	@Override
	public void init() {
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
	public void onCollision(Scene scene, Entity<?> e) {
		if(e instanceof Player){
			scene.removeEntity(this);		
		}
	}
	
	private void setupPowerup(){
		id = Entity.generateNewId(type.name);
		sprite = SpriteStore.get().getSprite(type.name+".png");
		spriteXOffset = sprite.getWidth()/2;
		spriteYOffset = sprite.getHeight()/2;
		hitBox = new RectHitBox(this, sprite.getWidth(), sprite.getHeight());
	}

}
