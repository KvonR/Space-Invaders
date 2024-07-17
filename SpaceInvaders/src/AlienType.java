import java.awt.Image;
/**
 * Enum defining the types of aliens in the game as each alien type has its unique image and point value.
 */
public enum AlienType {
/**
  * All the alien types are defined here with an image+score so
  * that they can be used in the factory method in Alien.java
  */
	Type1(Constants.ALIEN1_Image,Constants.ALIEN1_Points),
	Type2(Constants.ALIEN2_Image,Constants.ALIEN2_Points),
	Type3(Constants.ALIEN3_Image,Constants.ALIEN3_Points);
	
	private final Image image;//Image specific to alien type.
	private final int points;//Points rewarded for killing this alien.
	
	/**
     * Constructs an instance of AlienType with the specified image and point value.
     *
     * @param image  An image representing the alien type.
     * @param points The points awarded for destroying an alien of this type.
     */
	AlienType(Image image, int points) {
		this.image=image;
		this.points=points;
	}

//Getter methods for points and images
	public int getPoints() { return points; }
	public Image getImage() { return image; }
	
}
