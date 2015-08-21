package weapon;

import scene.Scene;
import entity.Bullet;
import entity.Entity;

public class PlayerBasicGun extends Weapon {
	
	int lastShotTime = -10000;

	public PlayerBasicGun(Entity<?> source) {
		super(source);
	}

	@Override
	public void fire(Scene scene, int time) {
		if(time-lastShotTime > 15){
			scene.addEntity(new Bullet(10, 90, source.getX(), source.getY()+8, source.getCollisionType()));
			lastShotTime = time;
		}
	}

}
