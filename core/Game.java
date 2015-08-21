package core;

import java.awt.event.KeyListener;

import scene.ExampleScene;
import scene.Scene;
import entity.Player;

public class Game{
	
	private GameWindow gameWindow;
	private Scene currentScene;
	private KeyStatus keyTracker = new KeyStatus();
	
	// 1 / (fps) * 1,000,000,000
	private final long frameLength = (long) ((1.0 / 30) * 1000000000);
	//temporarily doubled the length of seconds to slow the game down by half
	
	public Game(){
		initWindow();
		initGame();
		run();
	}
	
	private void initWindow(){
		KeyListener listener = new KeyInputHandler(keyTracker);
		gameWindow = new GameWindow(listener);
	}
	
	private void initGame(){
		currentScene = new ExampleScene(keyTracker);
		currentScene.addEntity(new Player(keyTracker, 400, 100));
		System.out.println("Frame length: "+frameLength/1000000.);
		System.out.println("Second length: "+frameLength*30/1000000.);
	}
	
	private void run(){
		long frameNanos = 0;
		
		while(!Thread.currentThread().isInterrupted()){
			Util.startProfile("frames");
//			frameNanos = System.nanoTime();
			long startTime = System.nanoTime();
			
			Util.startProfile("gameLogic");
			gameLogic();
			Util.endProfile("gameLogic");
			
			Util.startProfile("render");
			gameWindow.render(currentScene);
			Util.endProfile("render");

			long gameTime = (System.nanoTime() - startTime);
			long waitTime = (frameLength-gameTime);
			try {
//				long testTime = System.nanoTime();
				if(waitTime > 0){
					Thread.sleep(waitTime/1000000, (int) (waitTime%1000000));
				}
//				System.out.println(((System.nanoTime()-(testTime))-waitTime)/1000000.);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println(waitTime/1000000.);
//			System.out.println((System.nanoTime() - frameNanos)/1000000.+"\n");
			Util.endProfile("frames");
		}
	}
	
	private void gameLogic(){
		currentScene.updateScene();
		currentScene.updateEntities();
		currentScene.handleCollisions();
	}
	
	public void changeScene(String id){
	}
	
	
}
