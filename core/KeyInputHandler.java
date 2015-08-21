package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputHandler implements KeyListener{
	
	KeyStatus keyTracker;
	
	public KeyInputHandler(KeyStatus keyTracker){
		this.keyTracker = keyTracker;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyTracker.setKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyTracker.setKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
