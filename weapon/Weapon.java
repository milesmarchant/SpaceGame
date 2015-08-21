package weapon;

import scene.Scene;
import entity.Entity;

public abstract class Weapon {
	
	Entity<?> source;
	
	public Weapon(Entity<?> source){
		this.source = source;
	}

	public abstract void fire(Scene scene, int time);
	
}
