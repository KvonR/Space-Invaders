import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Image;
/**
 * Abstract class foundation for all game entities. This class provides common properties and functionality
 * for game entities such as position, dimensions, speed, and image handling.
 */
public abstract class Entity implements Constants, Events{
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Image image;
    private boolean alive;
    public Events nextHandler;//Next handler in the chain of resposibility pattern which handles collision events

 // Default constructor that initilaises Entity at a default point(bottom left).
    public Entity() {
        x = BORDER_Left;
        y = BOARD_Height - Ground;
        alive = true;
    }
    
/**
  * Parameterized constructor to initialize an entity with specific properties.
  *
  * @param x      It's x-coordinate.
  * @param y      It's y-coordinate.
  * @param width  Width of the entity.
  * @param height Height of the entity.
  * @param speed  Speed of the entity.
  * @param image  Image representing the entity.
  */
    public Entity(int x, int y, int width, int height, int speed, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = true;
        this.speed = speed;
        this.image = image;
	}
    /**
     * 
     * @param imagePath   		the path of the image file
     * @return loaded Image/null
     */
    public static Image loadImage(String imagePath) {
        Image image = null;
        try {
            File file = new File(imagePath);
            image = ImageIO.read(file);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    	}
    
/**
  * Paints the entity on the given graphics context(the canvas)
  * 
  * @param g The graphics context to paint the entity on.
  */    
	public void paint(Graphics g) {
		//System.out.println("Painting sprite at "+x+" "+y+" width is "+img.getWidth(null));
	        if (image != null) {
	            g.drawImage(image, x, y, null);
	        } else {
	        	//Temporary red rectangle if an image isn't available
	            g.setColor(Color.RED);
	            g.fillRect(x, y, width, height);
	}}
	
	
/**
  * This is part of the chain of responsibility part of the assignment
  * Handles collision events as part of the chain of responsibility
  * If an entity is involved in a collision event it will be dealt with
  * in a chain specific to the entities class.
  * 
  * @param event	 The collision event to handle.
  */
	public void handleCollisionEvent(EntityCollisions event) {
			if(nextHandler!=null) {
				nextHandler.handleCollisionEvent(event);
			}
        }

/**
  * Sets the next handler in the chain of responsibility for handling collision events.
  * 
  * @param next The next handler in the chain of resposibility
  */
    public void setNextHandler(Events next) {
        this.nextHandler = next;
    }


// Method to mark entity as "dead" and triggers assosiated effects
    public void destroyed() {
        this.alive = false;
        /** 	STUB
         * A sound should play here whenever an entity is destroyed.
         * "explosion.png" should also be shown briefly (on a timer) if the game was rendered.
         */
    }

    
    
// Setter methods:
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public void addX(int x) {
        this.x += x;
    }
    public void addY(int y) {
        this.y += y;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

// Getter methods:
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public Image getImage() {
        return image;
    }

    public boolean isAlive() {
        return alive;
    }

}
