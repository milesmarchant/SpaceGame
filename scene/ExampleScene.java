package scene;

import physics.Vec2;
import core.KeyStatus;
import entity.Asteroid;
import entity.Entity;
import entity.XEnemy;
import entity.powerup.Powerup;
import entity.powerup.Powerup.PowerupType;

public class ExampleScene extends Scene{
	
	int powerupChance = 10;

	public ExampleScene(KeyStatus keyTracker) {
		super(keyTracker);
	}

	@Override
	public void onAdd(Entity<?> entity) {
		
	}

	@Override
	public void onRemove(Entity<?> entity) {
	}

	@Override
	public void sceneLogic() {
		if(keyTracker.getKey(keyTracker.KEY_P) && pauseCounter > 5){
			paused = !paused;
			pauseCounter = 0;
		}
		if(paused){
			return;
		}
		if(sceneTime % 30 == 0){
			int determiner = rand.nextInt(2);
			if(determiner == 0){//change back
				addEntity(new Asteroid(32 + (768-32)*rand.nextDouble(), 650, new Vec2(-0.3 + (0.3+0.3)*rand.nextDouble(), -2 + (-5+2)*rand.nextDouble())));
			} else{
				addEntity(new XEnemy(32 + (768-32)*rand.nextDouble(), 650, new Vec2(0, -2)));
			}
		}		
	}

}
