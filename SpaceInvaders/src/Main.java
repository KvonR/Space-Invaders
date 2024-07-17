import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * The Main class is the entry point of the game. This class sets up the game environment and starts the game loop.
 * - I removed the need to extend thread in this class also, shown bellow by the commented code
 */
public class Main{
	
//	private boolean running=true;
	private Screen screen;
	private Entity entity;
	
/**
  * Constructor for Main. Initializes the game screen and adds the initial game entity.
  */
	public Main() {
		screen=new Screen();
		screen.addSprite(entity);
		screen.setVisible(true);
	}
	
/**
  * The main method that sets up the game controller instance(Singleton Pattern) and starts the game with a Key Listener that can handle inputs
  * into the game to allow the player to move.
  * 
  * @param args 	Command-line argument(not used)
  */
	public static void main(String[] args) {
		GameController gameController = GameController.getInstance();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent key) {
                if (key.getID() == KeyEvent.KEY_PRESSED) {
                    gameController.keyPressed(key);
                } else if (key.getID() == KeyEvent.KEY_RELEASED) {
                    gameController.keyReleased(key);
                }
                return false; // Return false to allow further processing of the key event
            }
        });
		gameController.startGame();
		Main main=new Main();
		
//		main.start();
//		main.run();

	}
	
	
//				public void run() {
//					while (running) {
//						screen.repaint();
//						try {
//							Thread.sleep(1000);	// wait one second
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						} // wait one second
//						entity.setX(entity.getX()+20);
//						
//					}
//					}
//				
//				
}
