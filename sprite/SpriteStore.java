package sprite;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Singleton is somewhat justified here because it is managing resources, and because no classes can write to it. Because there is only a
 * one way flow of information (resource -> SpriteStore -> Sprite), many of the drawbacks of singletons are inherently avoided.
 */
public class SpriteStore {
	private static SpriteStore single = new SpriteStore();
	
	private SpriteStore(){
	}
	
	public static SpriteStore get(){
		return single;
	}
	
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	public Sprite getSprite(String ref){
		//if sprite is loaded, return it
		if(sprites.get(ref) != null){
			return sprites.get(ref);
		}
		
		//otherwise load the sprite and return it
		BufferedImage sourceImage = null;
		
		try{
			URL url = this.getClass().getClassLoader().getResource("resources/sprites/"+ref);
			
			if(url == null) {
				fail("Can't find ref: "+ref);
			}
			
			sourceImage = ImageIO.read(url);
		} catch(IOException e){
			fail("Failed to load: "+ref);
			e.printStackTrace();
		}
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);
		
		image.getGraphics().drawImage(sourceImage, 0, 0, null);
		Sprite sprite = new Sprite(image);
		sprites.put(ref, sprite);
		return sprite;
	}

	private void fail(String message){
		System.err.println(message);
		System.exit(0);
	}
}
