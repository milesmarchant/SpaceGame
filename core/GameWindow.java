package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import scene.Scene;

public class GameWindow extends Canvas{
	
	public static final int WIN_WIDTH = 800;
	public static final int WIN_HEIGHT = 600;
	
	private BufferStrategy strategy;
		
	public GameWindow(KeyListener keyListener){
		JFrame container = new JFrame("Game");
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);
		
		setBounds(0,0,800,600);
		panel.add(this);
		
		//do not repaint because of buffer strategy
		setIgnoreRepaint(true);
		
		//make visible
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				Util.reportProfiles();
				Util.reportLogs();
				System.exit(0);
			}
		});
		
		addKeyListener(keyListener);
		
		requestFocus();
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	    Util.passGraphics((Graphics2D) strategy.getDrawGraphics());
	}
	
	public void render(Scene scene){
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC) ;
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY) ;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIN_WIDTH, WIN_HEIGHT);
		//start rendering the scene
		scene.drawEntities(g);
		Util.draw();
		//done rendering
		g.dispose();
		strategy.show();
	}

}
