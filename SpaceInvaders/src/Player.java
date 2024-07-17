import java.awt.Image;

/**
 * This class extends Entity and represents the player in the game. Includes player-specific 
 * attributes like lives and score, as well as methods for player movement and actions.
 */
public class Player extends Entity {
    private int lives; // Number of lives the player has
    private int score; // Player's score


    /**
     * Constructs a new Player with default settings.
     * Player is positioned in the middle of the board, slightly above ground, default width, height, speed, and image.
     */	
    public Player() {
        super(BOARD_Width / 2, Ground + PLAYER_Height + 10,
        	  PLAYER_Width, PLAYER_Height, PLAYER_Speed, PLAYER_Image);
        
        this.lives = PLAYER_Lives;
        this.score = 0; //Initialising score to zero
        this.setAlive(true);
    }


/**
  * Moves the player in the specified direction while ensuring the player stays within the game boundaries.
  *
  * @param direction The direction and magnitude of movement. Negative for left, positive for right.
  */ 
    public void move(int direction) {
        this.addX(direction);
        
        // Ensure player stays within the game boundaries
        if (this.getX() <= Constants.BORDER_Left) {
            this.addX(+1);
        } else if (this.getX() >= BORDER_Right) {
            this.addX(-1);
        }
    }
//Methods to move player left and right:
    public void moveLeft() { move(-PLAYER_Speed); }
    public void moveRight() { move(PLAYER_Speed); }


    
/**
  * Handles the action of the player shooting a bullet.
  *
  * @return New Bullet object representing the shot fired by the player
  */
    public Bullet shoot() {
    	
    	int bx = this.getX() + (this.getWidth()/2); //x position for bullet to shoot from middle of the player
    	int by = this.getY(); //y position

    	return new Bullet(bx,by,true);
    }

/** Updates the player's score by adding the specified points.
  *
  * @param points The number of points to add to the player's score.
  */
    public void updateScore(int points) {
        score += points;
    }

// Method to handle player losing a life
    public void loseLife() {
        lives--;
    }


// Getters for player's lives and score
    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }
    
    

/**
  * Handles a collision event involving the player by removing a life if hit, if not moves down the Chain of Resposibility.
  *
  * @param event 	The collision event to be handle.
   */
	public void handleCollisionEvent(EntityCollisions event) {
        if (event.getEntityA()==this || event.getEntityB()==this) {
        	this.loseLife();
        } 
        else {
            super.handleCollisionEvent(event);
        }
    }
/**  
  * Sets the next handler in the chain of responsibility for handling collision events.
  *
  * @param next 	The next handler in the chain.
  */
    public void setNextHandler(Events next) {
        this.nextHandler = next;
    }
}
