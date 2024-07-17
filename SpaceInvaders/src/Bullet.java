import java.awt.Image;
/**
  * This class extends Entity and represents a bullet in the game.
  * provides functionalities specific to bullets - vertical movement & damage.
  * 
  */
public class Bullet extends Entity {
	
    private boolean isPlayerBullet; // Flag to indicate whether it's a player or alien bullet
    private int bulletDamage = Constants.BULLET_Damage;
 
/**
  * Constructs a new Bullet with specified parameters.
  * 
  * @param x The initial x-coordinate of the bullet.
  * @param y The initial y-coordinate of the bullet.
  * @param isPlayerBullet Flag to decide whether bullet moves up or down depending on if its a player/alien bullet.
  */
    public Bullet(int x, int y,boolean isPlayerBullet) 
    {
        super(x, y, BULLET_Width, BULLET_Height,BULLET_Speed, BULLET_Image);
        this.isPlayerBullet = isPlayerBullet;
    }

    public void updateBullet() 
    {
        // Update bullet position based on direction (e.g., upwards for player bullets, downwards for aliens)
        if (isPlayerBullet) {
            this.addY(-BULLET_Speed);
        } else {
            this.addY(BULLET_Speed); 
        }
    }
    
    public int getBulletDamage() {
    	return bulletDamage;
    }
    
/**
 * Handles a collision event involving this bullet by destroying it if it is involved in a collision event.
 * If not move down the chain to the next handler
 * 
 * @param event The collision event to handle.
 */

	public void handleCollisionEvent(EntityCollisions event)
	{
        if (event.getEntityA()==this || event.getEntityB()==this) 
        {
        	this.destroyed();
        } 
        else {
            super.handleCollisionEvent(event);
        }
    }
/**
  * Sets the next handler in the chain of responsibility for handling collision events.
  * 
  * @param next The next handler to be set.
  */
    public void setNextHandler(Events next) {this.nextHandler = next;}

}
