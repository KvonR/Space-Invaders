import java.awt.Image;
import java.io.*;
import javax.imageio.ImageIO;
/**
 * Interface defining constants used throughout the game.
 * This interface includes dimensions, image paths, and other constants 
 * for various entities such as aliens, player, bullets, and walls.
 */
public interface Constants {
//Board Dimensions:
int BOARD_Width = 800;
int BOARD_Height = 800;
int BORDER_Right = 30;
int BORDER_Left = 5;
int Ground = 620;



//Delay before re-updating game state:
int Delay = 10;



//Alien Properties:
int NUMBER_OF_ALIENS_TO_Destroy = 54;
int ALIEN_Height = 30;
int ALIEN_Width = 30;
int ALIEN_INIT_X = 150;
int ALIEN_INIT_Y = 5;
int ALIEN_Speed = 4;
int ALIEN_X_Gap = 20;
int ALIEN_Y_Gap  = 20;
int ALIEN1_Points = 10;
int ALIEN2_Points = 20;
int ALIEN3_Points = 40;
//Alien Image Paths + Loaded for each AlienType -
String ALIEN1_String = "/alien1.png";
String ALIEN2_String = "/alien2.png";
String ALIEN3_String = "/alien3.png";

Image ALIEN1_Image = loadImage(ALIEN1_String);
Image ALIEN2_Image = loadImage(ALIEN2_String);
Image ALIEN3_Image = loadImage(ALIEN3_String);



//Player Properties:
int PLAYER_Width = 50;
int PLAYER_Height = 50;
int PLAYER_Lives = 3;
int PLAYER_Speed = 5;
String PLAYER_String = "/player.png";
Image PLAYER_Image = loadImage(PLAYER_String);



//Bullet Properties:
int BULLET_Width = 5;
int BULLET_Height = 10;
int BULLET_Speed = 4;
int BULLET_Damage = 1;
String BULLET_String = "/bullet.png";
Image BULLET_Image = loadImage(BULLET_String);



//Wall properties:
int NUMBER_OF_Walls = 3;
int WALL_Height=50;
int WALL_Width=100;
int WALL_Strength=5;
int WALL_Y_Gap = 30;
String WALL_String = "/wall.png";
Image WALL_Image = loadImage(WALL_String);
// Wall image paths for different damage stages -
String WALL_String1 = "/wall1.png";
String WALL_String2 = "/wall2.png";
String WALL_String3 = "/wall3.png";
String WALL_String4 = "/wall4.png";

Image WALL_Damaged_1 = loadImage(WALL_String1);
Image WALL_Damaged_2 = loadImage(WALL_String2);
Image WALL_Damaged_3 = loadImage(WALL_String3);
Image WALL_Damaged_4 = loadImage(WALL_String4);



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

}
