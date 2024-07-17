import java.awt.*;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents the main screen of the game, responsible for rendering game entities.
 * This class manages the JFrame and JPanel with displaying game content.
 */
public class Screen {


	private JFrame frame = new JFrame();
	private Canvas canvas = new Canvas();

	private ArrayList <Entity> entityList=new ArrayList <Entity>();//All entities needed to be rendered
	
/**
  * Inner class Canvas extending JPanel to handling custom rendering of the game
  */
	class Canvas extends JPanel {

/**
  * Renders the game screen and all entities within the entityList.
  *
  * @param g 		Graphics context used for drawing.
  */
		public final void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			// Paint all the objects
			for (int idx=0;idx<entityList.size();idx++) {
				Entity entity=entityList.get(idx);
				
				entity.paint(g);
			
			}
			drawScoreAndLives(g);

		}
	}
	
/**
  * Draws the player's score and remaining lives on the top left of the screen.
  *
  * @param g 		The Graphics context used for drawing.
  */
	public void drawScoreAndLives(Graphics g) {
        g.setColor(Color.WHITE); //Set colour
        g.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font

        int score = GameController.getInstance().getScore();
        int lives = GameController.getInstance().getPlayerLives();

        g.drawString("Score: " + score, 10, 20); // Position the score text at the top-left
        g.drawString("Lives: " + lives, 10, 40); // Position the lives text a little below the score
    }

/**
 * Method for taking all entities out a list and putting them in a new one
 * this method is invoked 4 times in the game controller for each list of entities.
 * @param entities 		 the entities still alive in the game.
 */
	public void addSprites(List<? extends Entity> entities) {
		for (Entity entity: entities)
		entityList.add(entity);
	}
/**
 * Same as the other method but just takes in one entity and adds it to the list
 * @param entity		 the entity to be added to the list
 */
	public void addSprite(Entity entity) {
		entityList.add(entity);
	}

	public Screen() {
		canvas.setSize(Constants.BOARD_Width,Constants.BOARD_Height);
		frame.setSize(Constants.BOARD_Width,Constants.BOARD_Height);
		frame.setResizable(false);
		frame.add(canvas);
		frame.setVisible(true);
		//We need to turn on the visibility of our frame
		// Allow the frame to be closed
		     frame.addWindowListener(new WindowAdapter()
		       {public void windowClosing(WindowEvent e)
		          {frame.dispose(); System.exit(0);}
		       }
		     );

	}
	public JFrame getFrame() {
		return frame;
	}

	public int getWidth() {
		return (canvas.getWidth());
	}

	public int getHeight() {
		return (canvas.getHeight());
	}

	public void repaint() {
		canvas.repaint();
	}

	public void setVisible(boolean b) {
		canvas.setVisible(b);
	}

}
