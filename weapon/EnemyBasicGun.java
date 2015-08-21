package weapon;

import scene.Scene;
import entity.Bullet;
import entity.Entity;

public class EnemyBasicGun extends Weapon {
	
	int lastShotTime = -10000;

	public EnemyBasicGun(Entity<?> source) {
		super(source);
	}

	@Override
	public void fire(Scene scene, int time) {
		if(time-lastShotTime > 30){
			scene.addEntity(new Bullet(5, Math.toDegrees(Math.atan2(scene.getEntity("Player").getY()-source.getY(), scene.getEntity("Player").getX()-source.getX())), 
			source.getX(), source.getY(), Entity.COLLISION_GROUP_ENEMY));
			lastShotTime = time;
		}
	}

}
