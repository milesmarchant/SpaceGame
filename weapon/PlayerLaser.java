package weapon;

import scene.Scene;
import entity.Entity;
import entity.Laser;

public class PlayerLaser extends Weapon {
	
	int lastShotTime = -10000;

	public PlayerLaser(Entity<?> source) {
		super(source);
	}

	@Override
	public void fire(Scene scene, int time) {
		if(time-lastShotTime > 45){
			scene.addEntity(new Laser(source.getX(), source.getY()+8, source.getCollisionType(), source, 0, 8));
			lastShotTime = time;
		}
	}

}
