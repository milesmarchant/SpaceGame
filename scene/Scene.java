package scene;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.BiConsumer;

import core.KeyStatus;
import core.Util;
import entity.Entity;
import entity.Laser;
import entity.powerup.Powerup;
import entity.powerup.Powerup.PowerupType;

public abstract class Scene {
	protected HashMap<String, Entity<?>> entities = new HashMap<String, Entity<?>>();
	
	protected int sceneTime = 0;
	
	protected int pauseCounter = 0;
	
	protected Random rand = new Random();
	
	protected boolean paused = false;
	
	protected KeyStatus keyTracker;
	
	public Scene(KeyStatus keyTracker){
		this.keyTracker = keyTracker;
	}
	
	public final void addEntity(Entity<?> entity){
		entity.init();
		entities.put(entity.getId(), entity);
//		System.out.println("Adding entity: "+entity.toString());
		onAdd(entity);
	}
	
	/**
	 * Allows scene specific behavior on entity addition
	 * 
	 * @param entity
	 */
	public abstract void onAdd(Entity<?> entity);
	
	public final boolean removeEntity(Entity<?> entity){
		Entity<?> removed = entities.remove(entity.getId());
		boolean successful = true;
		if(removed != null){
//			System.out.println("Successfully removed entity: "+entity.toString());
		}else{
			successful = false;
			System.out.println("Failed to remove entity: "+entity.toString());
		}
		onRemove(removed);
		return successful;
	}
	
	/**
	 * Allows scene specific behavior on entity removal
	 * 
	 * @param entity
	 */
	public abstract void onRemove(Entity<?> entity);
	
	//TODO have it deal with the UUIDs
	public Entity<?> getEntity(String name){
		return entities.get(name);
	}
	
	/**
	 * Contains logic common to all scenes, and invokes the abstract {@link sceneLogic} method which contains scene specific logic
	 */
	public final void updateScene(){
		pauseCounter++;
		sceneTime++;
		sceneLogic();
	}
	
	/**
	 * Implementations of this should define behavior of a particular scene, such as buttons, enemy spawning, victory checking, etc.
	 */
	public abstract void sceneLogic();
	
	/**
	 * Queue approach to iterating over entities suggested by user Avi on StackOverflow <a href="http://stackoverflow.com/a/993036/4548380">here</a>
	 * 
	 * TODO? Move into some sort of abstract superclass? Should there be different Scene classes, or just different instances by reading data?
	 */
	public void updateEntities(){
		LinkedList<Entity<?>> entityQueue = new LinkedList<Entity<?>>();
		entityQueue.addAll(entities.values());
		while(!entityQueue.isEmpty()){
			Entity<?> e = entityQueue.remove();
			e.update(this);
		}
//		Util.printCount("Number of entities: "+entities.size(), counter, 30);
	}
	
	public void handleCollisions(){
		if(paused){
			return;
		}
		LinkedList<Entity<?>> entityQueue = new LinkedList<Entity<?>>();
		entityQueue.addAll(entities.values());
		while(!entityQueue.isEmpty()){
			Entity<?> e = entityQueue.remove();
			LinkedList<Entity<?>> targets = new LinkedList<Entity<?>>(entityQueue);
			while(!targets.isEmpty()){
				Entity<?> target = targets.remove();
				if(e instanceof Laser || target instanceof Laser){
					
				}
				if(!e.getHitBox().collideWith(target.getHitBox())){
					continue;
				}
				if(Util.contains(e.getCollisionTargets(), target.getCollisionType())){
					e.onCollision(this, target);
				}
				if(Util.contains(target.getCollisionTargets(), e.getCollisionType())){
					target.onCollision(this, e);
				}
			}
		}
		
		//old collision code
		
//		//iterate through all entities
//		while(!entityQueue.isEmpty()){
//			Entity e = entityQueue.remove();
//			LinkedList<Entity> collisionTargets = new LinkedList<Entity>();
//			List<Integer> collisionValues = Arrays.asList(e.getCollisionTargets());
//			//find which entities are valid collision targets for e
//			for(Entity possibleTarget: entities){
//				if(collisionValues.contains(possibleTarget.getCollisionType())){
//					collisionTargets.add(possibleTarget);
//				}
//			}
//			//check for collisions with each target
//			for(Entity target: collisionTargets){
//				double dx = e.getX() - target.getX();
//				double dy = e.getY() - target.getY();
//				double distance = Math.sqrt(dx*dx + dy*dy);
//				if(distance < e.getCollisionRadius() + target.getCollisionRadius()){
//					e.onCollision(this, target);
//					target.onCollision(this, e);
//				}
//			}
//		}
	}
	
	public void drawEntities(Graphics2D g){
		entities.forEach(new BiConsumer<String, Entity<?>>(){
			@Override
			public void accept(String s, Entity<?> e) {
				e.draw(g);
			}
		});
	}
	
	public PowerupType getPowerup(Powerup.PowerupType... powerupTypes){
		//TODO if nothing inputted, stop
		PowerupType powerupType = powerupTypes[rand.nextInt(powerupTypes.length)];
		return powerupType;
	}
}
