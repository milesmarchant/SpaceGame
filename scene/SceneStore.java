package scene;

import java.util.HashMap;

/**
 * Singleton pattern is used here to simplify the concept of saving and loading scenes. Many parts of code may want to change the scene, from loss/win detection
 * to menus.
 * 
 * @author Miles Marchant
 *
 */
public class SceneStore {
	private static SceneStore single = new SceneStore();
	
	private SceneStore(){
	}
	
	public static SceneStore get(){
		return single;
	}
	
	private HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	
	public Scene getScene(String ref){
		//if scene is loaded, return it
		if(scenes.get(ref) != null){
			return scenes.get(ref);
		}
		
		//otherwise, load the scene
		return null;	
	}

}
