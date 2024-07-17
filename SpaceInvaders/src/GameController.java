import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;


/**
 * Main controller for the game, responsible for managing game entities, handling game logic,
 * and updating the game state. Implements the singleton pattern to ensure only one instance is used.
 */
public class GameController {
    // Game entities:
    private Player player;
    private List<Alien> aliens;
    private List<Bullet> playerBullets;
    private List<Bullet> alienBullets;
    private List<Wall> walls;
    
    // Movement flags:
    private boolean moveLeft=false;
    private boolean moveRight=false;
    
    //Chain of responsibility:
    private Events eventChain;
    
    //Game Screen:
	private Screen screen;

//Using a singleton design pattern to create an instance of the game
    private static GameController instance;

/**
  * Private constructor for GameController to support Singleton pattern.
  * Initialises game entities and sets up the game environment.
  */
    private GameController() 
    {
        // Initialise player, aliens, walls, and bullets:
        player = new Player();
        aliens = new ArrayList<>();
        playerBullets = new ArrayList<>();
        alienBullets = new ArrayList<>();
        walls = new ArrayList<>();
        this.screen = new Screen();
        initialiseAliens();
        initialiseWalls();
        
        
        }


/**
  * Provides access to the singleton instance of the GameController.
  *
  * @return Singleton instance of GameController.
  */
    public static GameController getInstance() 
    {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

/**
 * Begins the game loop
 */
    public void startGame() 
    {
        while (true) {
            updateGameState();
            handleInput();
            render();// re-render the game after updating the game states and handling inputs to keep the game updated to what is occuring.
            try {
                Thread.sleep(Constants.Delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
     
/**
 * Reset logic for when player dies
 * Clears all the lists then reinitilises all entities
 */
    public void resetGame() {
    	aliens.clear();
    	playerBullets.clear();
    	alienBullets.clear();
    	walls.clear();
    	player.setAlive(true);
        initialiseAliens();
        initialiseWalls();
    	updateGameState();
    	handleInput();
    }
    
 /** Stub **
  * Ends the game, clears everything, shows score
  */
    public void gameOver() {
    	//Stub -- render would be done here to clear everything, show the game is over and show the score.
    }
    
    
/**
  * Builds event chain for handling collision events using the Chain of Responsibility pattern.
  */
    private void buildEventChain()
    {
    	Events currentHandler = player;
    	
        // Link each alien in the list
        for (Alien alien : aliens) {
            currentHandler.setNextHandler(alien);
            currentHandler = alien;
        }
        for (Wall wall : walls) {
            currentHandler.setNextHandler(wall);
            currentHandler = wall;
        }
        for (Bullet playerBullet : playerBullets) {
            currentHandler.setNextHandler(playerBullet);
            currentHandler = playerBullet;
        }
        for (Bullet alienBullet : alienBullets) {
            currentHandler.setNextHandler(alienBullet);
            currentHandler = alienBullet;
        }

        // Set the start of the chain
        this.eventChain = player;
    }
    
/**
 * Updating Game State & Entity Activity
 */
    private void updateGameState() {
        updatePlayer();
        updateAliens();
        updateBullets();
        checkCollisions();
        buildEventChain();
        removeDeadAliens();
        removeDestroyedWalls();
        removeDestroyedBullets();
    }

//Updates player's position:
    private void updatePlayer() {
        handleInput();
    }
// Update aliens' positions:
    private void updateAliens() {
        for (Alien alien : aliens) {
            alien.move(); // Alien movement logic
        }
    }
// Update player & alien bullets' position:
    private void updateBullets() {
        for (Bullet bullet : playerBullets) {
            bullet.updateBullet();
        }

        for (Bullet bullet : alienBullets) {
            bullet.updateBullet(); 
        }
    }

/**
 * Checks for collisions between different entities in the list then starts a collision
 *  even where the chain handles what specific actions happen to specific entities.
 *  This method iterates through all relevant entity combinations to check for collisions
 */
    private void checkCollisions() {
        // Check collisions between player bullets & aliens
        for (Bullet playerBullet : playerBullets) {
            for (Alien alien : aliens) {
                if (collision(playerBullet, alien)) {
                    // Handle collision between player bullet & alien
                    EntityCollisions event = new EntityCollisions(playerBullet,alien);
                    eventChain.handleCollisionEvent(event);
                    player.updateScore(alien.getPoints());//update the score by adding the aliens points to the score
                }
            }
        }

        // Check collisions between alien bullets & player
        for (Bullet alienBullet : alienBullets) {
            if (collision(alienBullet, player)) {
                // Handle collision between alien bullet and player
                EntityCollisions event = new EntityCollisions(alienBullet,player);
                eventChain.handleCollisionEvent(event);
                resetGame();
            }
        }
        // Check collisions between player bullets & walls
        for (Bullet playerBullet : playerBullets) {
            for (Wall wall : walls) {
                if (collision(playerBullet, wall)) {
                    EntityCollisions event = new EntityCollisions(playerBullet,wall);
                    eventChain.handleCollisionEvent(event);
                }
            }
        }

        // Check collisions between alien bullets & walls
        for (Bullet alienBullet : alienBullets) {
            for (Wall wall : walls) {
                if (collision(alienBullet, wall)) {
                    EntityCollisions event = new EntityCollisions(alienBullet,wall);
                    eventChain.handleCollisionEvent(event);
                }
            }
        }
    }
    
/**
 * The logic behind the collision checks, checks if 2 entities collided
 * Collision depends on whether bounds have crossed between entities.
 * @param entity1				1st entity to check
 * @param entity2				2nd entity to check 
 * @return true/false 			if collision detected/if not detected
 */
    private boolean collision(Entity entity1, Entity entity2) 
    {
        return entity1.getX() < entity2.getX() + entity2.getWidth() &&
                entity1.getX() + entity1.getWidth() > entity2.getX() &&
                entity1.getY() < entity2.getY() + entity2.getHeight() &&
                entity1.getY() + entity1.getHeight() > entity2.getY();
    }
   
    						
/**
 * Initialisation of Aliens - Uses Predefined constants from Constants interface. (Number of aliens, dimensions, speed,etc)
 * 2/5 Aliens are Type1(10 points)
 * 2/5 Aliens are Type2(20 points)
 * 1/5 Aliens are Type3(40 points)
 */
    private void initialiseAliens() {
    	int numberOfAliens = Constants.NUMBER_OF_ALIENS_TO_Destroy;
    	int alienWidth = Constants.ALIEN_Width;
    	int alienHeight = Constants.ALIEN_Height;
    	int alienx = Constants.ALIEN_INIT_X;
    	int alieny = Constants.ALIEN_INIT_Y;
    	int alienGapX= Constants.ALIEN_X_Gap;
    	int alienGapY = Constants.ALIEN_Y_Gap;
    	int offside = Constants.BOARD_Width*3/5;//Aliens will start on a new layer once 3/5 of the board is exceeded to allow movement of the aliens left and right regardless of the board width or number of aliens
    	int proportionOfAliens = numberOfAliens/5; //Aliens will be proportioned into 1/5 like the game so aliens type 3 will be 1/5 of the aliens and the other 2 will be 2/5 each
    	
    	//This method just proportions how the aliens to split them up into 1/5 type3, 2/5 type2 & 2/5 type1
        for (int i = 0; i < numberOfAliens; i++) {
            if (i < proportionOfAliens) {    //i<1/5 will fill the line up with type3 aliens
                aliens.add(Alien.createAlien(AlienType.Type3, alienx, alieny));
            } 
            else if (i < proportionOfAliens * 3) {//i<3/5 (because 1/5 has already been filled up by type3 so 3/5-1/5=2/5)
                aliens.add(Alien.createAlien(AlienType.Type2, alienx, alieny));
            } 
            else {// last 2/5 filled up with type1
                aliens.add(Alien.createAlien(AlienType.Type1, alienx, alieny));
            }

            alienx += alienWidth + alienGapX;
            //if alien exceeds 3/5 of the board it'll start a new line
            if (alienx > offside) {
                alienx = Constants.ALIEN_INIT_X;//re-initilise x for the new line
                alieny += alienHeight + alienGapY;//starts a new line
            }
        }
    }

 /**
  * Initialisation of Walls - Walls are initiated equally spaced out based on how many of them their are
  * Uses predefined configurations from the Constants Interface
  */
     private void initialiseWalls() {
    	 int wallWidth = Constants.WALL_Width;
    	 int numberOfWalls = Constants.NUMBER_OF_Walls;
    	 int boardWidth = Constants.BOARD_Width;
    	 
    	 for (int i = 1; i <= numberOfWalls;i++) {
    		 /**
    		  * wallx: calculation ensures the walls are evenly spaced and positions them closer to the player end.
    		  *        it considers the board width to distribute the base x-coordinate then i* to space it out by the number of walls
    		  */
    		 int wallx = (i* (boardWidth / (numberOfWalls + 1)))-(wallWidth/2);
    		 int wally = player.getY()-120;
    		 
    		 Wall wall = new Wall (wallx,wally);
    		 walls.add(wall);
    	 	}
	 }

     
/**
 * Removing destroyed aliens from the game.
 * This method updates the list of aliens by removing those that aren't alive
 */
    private void removeDeadAliens() {
    	List<Alien> deadAliens = new ArrayList<>();
    	
    	for (Alien alien : aliens) {
    		if (!alien.isAlive()) {
    			deadAliens.add(alien);//Add not alive aliens to a list of dead aliens
    			player.updateScore(1);//Add 1 point to the score for every alien that is dead
    		}
    	}
    	aliens.removeAll(deadAliens);//Remove dead aliens from list of aliens 
    }
    /**
     * Removing destroyed walls from the game.
     * This method updates the list of walls by removing those that aren't alive
     */   
    private void removeDestroyedWalls() {
    	List<Wall> destroyedWalls = new ArrayList<>();
    	
    	for (Wall wall : walls) {
    		if (!wall.isAlive()) {
    			destroyedWalls.add(wall);//Add not alive walls to a list of destroyed walls
    		}
    	}
    	walls.removeAll(destroyedWalls);//Remove destroyed walls from list of walls
    }
    
/**
  * Removing destroyed bullets from the game.
  * This method does the same as the other 2 but checks 2 entities instead
  */   
    private void removeDestroyedBullets() {
    	List<Bullet> destroyedPlayerBullets = new ArrayList<>();
    	List<Bullet> destroyedAlienBullets = new ArrayList<>();
    	for (Bullet bullet : playerBullets) {
    		if (!bullet.isAlive()) {
    			destroyedPlayerBullets.add(bullet);//Add not alive bullets to a list of destroyed bullets
    		}
    	}
    	playerBullets.removeAll(destroyedPlayerBullets);//Remove dead bullets from list of bullets 
    	
    	
    	for (Bullet bullet : alienBullets) {
    		if (!bullet.isAlive()) {
    			destroyedAlienBullets.add(bullet);//Add not alive bullets to a list of dead bullets
    		}
    	}
    	alienBullets.removeAll(destroyedAlienBullets);//Remove dead bullets from list of bullets 
    }
     
/**
  * Handles player input to control movement and actions of the player in the game.
  * Processes input flags to move the player left or right, and to shoot bullets.
  */
    private void handleInput() {
        // This method will use the move method from Player class allowing the player to move by changing coordinates
        if (moveLeft) {
            player.moveLeft(); // Move left by decrementing X
        } else if (moveRight) {
            player.moveRight(); // Move right by incrementing X
        }
    }
/**
  * Processes key press events to update movement flags and trigger player actions.
  * This method is called in the Main.java class for constant input functionality.
  *
  * @param e 	The KeyEvent representing the key press.
  */ 
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {//Move player left when left key is clicked
            moveLeft = true;
        }

        if (key == KeyEvent.VK_RIGHT) {//Move player right when right key is clicked
            moveRight = true;
        }
        if (key == KeyEvent.VK_SPACE) {//Shoot playerBullet when space bar is clicked
            playerBullets.add(player.shoot());
        }
    }
/**
  * Processes key release events to update movement flags. This method is called in the Main.java class for constant input functionality.
  *
  * @param e 	The KeyEvent representing the key release.
  */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {//Stop moving player left when left key is released
            moveLeft = false;
        }

        if (key == KeyEvent.VK_RIGHT) {//Stop moving player right when right key is released
            moveRight = false;
        }
    }

    /**
     * Renders the current state of the game by updating the screen with the latest positions
     * and states of all game entities.
     */  
    private void render() {
    	screen.addSprites(aliens);
    	screen.addSprites(playerBullets);
    	screen.addSprites(alienBullets);
    	screen.addSprites(walls);
    	screen.addSprite(player);
    	screen.repaint();
    	

    }
    
    //Getters:
    public boolean isMoveLeft() {
        return moveLeft;
    }
    public boolean isMoveRight() {
        return moveRight;
    }
    
	public int getScore() {
		return player.getScore();
	}
	public int getPlayerLives() {
		return player.getLives();
}



	
}
