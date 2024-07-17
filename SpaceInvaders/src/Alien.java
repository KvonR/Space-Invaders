import java.awt.Image;
/**
 * This Class extends the Entity Class and represents an Alien in the game.
 * It provides specific functionalities(movement & shooting) for the alien entities
 */
public class Alien extends Entity {
	/**
	 * Indicates the direction of horizontal movement for all instances of the Alien class using static
	 * When true, Aliens move to the right; when false, they move to the left.
	 * This will prevent overlapping as all aliens will move left/right in unison, saving me from having to code a method for checking overlapping
	 */
    private static boolean movingRight = true; // Flag to track the direction of movement
   

    private int points;//The points awarded for destroying this Alien.
    private Events nextHandler;//The next handler in the chain of responsibility for handling collision events.

/**
     * Constructs a new Alien with specified position, image, and point value.
     * 
     * @param x 		The x-coordinate.
     * @param y 		The y-coordinate.
     * @param image 	The image representing the alien.
     * @param points 	The points awarded for destroying this alien(10,20,40)
**/
    public Alien(int x, int y, Image image, int points) {
        super(x, y, ALIEN_Width, ALIEN_Height, ALIEN_Speed, image);
        this.setAlive(true);
        this.points=points;
    }
    

/**
  * Controls the back-and-forth horizontal movement of the alien.
  * Aliens move either to the right or to the left based on the current direction.
 **/
    public void move() {
        if (movingRight) {
            if (getX()+ getWidth() + ALIEN_Speed <= BOARD_Width - BORDER_Right) { //Checks if player has reached the right border yet
                addX(ALIEN_Speed);
            } 
            else {
                movingRight = false; // move left when right border is reached
            }
        } 
        else {
            if (getX() - ALIEN_Speed >= BORDER_Left) {//Checks if the player has reached the left border  yet
                addX(-ALIEN_Speed); // Move left
            } 
            else {
                movingRight = true;// move right when the left boarder is reached
            }
        }
    }
/**
  * Creates a bullet object representing the alien's shot.
  * 
  * @return New Bullet object initiated from the alien's position.
  */
    public Bullet shoot() {

    	int bx = this.getX() + (this.getWidth()/2); //x position of the middle of the alien
    	int by = this.getY(); //y position

    	return new Bullet(bx,by,false);//false because it is not a player bullet
    }
    
/**
  * Factory method to create alien instances based on the specified type.
  * 
  * @param type 	The type of the alien to be created.
  * @param x 		The x-coordinate.
  * @param y 		The y-coordinate.
  * @return 		New instance of Alien of the specified type.
  */
    public static Alien createAlien(AlienType type, int x, int y) {
                return new Alien(x, y, type.getImage(), type.getPoints());

        }  
 /**
   * Returns the points awarded for destroying this alien which is useful for updating our score.
   * 
   * @return  The Alien's point value  
   */
    public int getPoints() {
    	return points;
    }

/**
  * Handles a collision event involving this alien by destroying the alien if it is involved in the collision event
  * if not it will move down the Chain.
  * 
  * @param event The collision event to handle.
  */
	public void handleCollisionEvent(EntityCollisions event) {
        if (event.getEntityA()==this || event.getEntityB()==this) {
        	this.destroyed();
        } 
        else {
            super.handleCollisionEvent(event);
        }
    }
/**
  * Sets the next handler in the chain of responsibility for handling collision events.
  * 
  * @param next  Next handler to be set
  */
    public void setNextHandler(Events next) {
        this.nextHandler = next;
    }
}