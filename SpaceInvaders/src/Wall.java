import java.awt.Image;

/**
 * This class extends Entity and represents a wall in the game. This class includes additional
 * attributes and methods for managing the wall's strength and appearance.
 */
public class Wall extends Entity {
    private int strength; // Strength/health of the wall

    
/**
  * Constructs a new Wall entity with specified position and default strength. 
  * I was able to reduce the amount of parameters in the entities by using the defaults in the super method.
  *
  * @param x 		The x-coordinate of the wall.
  * @param y 		The y-coordinate of the wall.
  */
    public Wall(int x, int y) {
        super(x, y, WALL_Width, WALL_Height, 0, WALL_Image); // Walls don't move so speed is set to 0
        this.strength = WALL_Strength;
    }

    /**
     * Reduces the wall's strength by the specified damage and updates its appearance calling setImage() method
     *
     * @param damage 		Amount of damage to inflict on the wall.
     */
    public void takeHit(int damage) {
        strength -= damage;
        setImage(); //Update wall appearance based on health
        if (strength <= 0) {
            // If the wall's strength goes below or equal to zero, the wall is destroyed
            destroyed();
        }
    }

    // Getters and setters for wall strength
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    
/**
 * This method is to change the image of the wall every time it is hit. If the wall strength is altered then this method should also be altered.
 * If the game was rendered I would implement a different method where the image changes it's texture or size based on the point of impact of the bullet.
 */
    private void setImage() {
    	if (strength == 4) {
    		this.setImage(WALL_Damaged_1);
    	}
    	else if (strength == 3) {
    		this.setImage(WALL_Damaged_2);
    	}
    	else if (strength == 2) {
    		this.setImage(WALL_Damaged_3);
    	}
    	else if (strength == 1) {
    		this.setImage(WALL_Damaged_4);
    	}
    	else if (strength == 0) {
    		this.setImage(null);
    	}
    }
    
    
/**
  * Handles a collision event involving the wall by reducing it's strength and changing it's appearance per hit.
  *
  * @param event		 The collision event to handle.
  */
	public void handleCollisionEvent(EntityCollisions event) {
        if (event.getEntityA()==this || event.getEntityB()==this) {
        	this.takeHit(BULLET_Damage);
        } 
        else {
            super.handleCollisionEvent(event);
        }
    }
	
/**
  * Sets next handler in the chain of responsibility for handling collision events.
  *
  * @param next 		The next handler in the chain.
  */
    public void setNextHandler(Events next) {
        this.nextHandler = next;
    }
}