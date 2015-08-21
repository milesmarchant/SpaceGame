package core;

import java.awt.event.KeyEvent;
import java.util.BitSet;

public class KeyStatus {
	
	public final int KEY_UP     = KeyEvent.VK_UP;
	public final int KEY_RIGHT  = KeyEvent.VK_RIGHT;
	public final int KEY_DOWN   = KeyEvent.VK_DOWN;
	public final int KEY_LEFT   = KeyEvent.VK_LEFT;
	public final int KEY_SPACE  = KeyEvent.VK_SPACE;
	public final int KEY_P      = KeyEvent.VK_P;
	
	BitSet keyStatus;
	
	public KeyStatus(){
		keyStatus = new BitSet();
	}
	
	/**
	 * Only for use by the KeyInputHandler
	 */
	public synchronized void setKey(int key, boolean pressed){
		if(keyStatus.get(key) == pressed) return;
//		System.out.println("Setting key: "+KeyEvent.getKeyText(key)+" as down: "+pressed);
		keyStatus.set(key, pressed);
	}
	
	public synchronized boolean getKey(int key){
		return keyStatus.get(key);
	}

}
