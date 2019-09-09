import java.awt.image.BufferedImage;

/*

init() : this uses the object of SpriteSheet class to store an image.

the SpriteSheet object uses its crop function to set BufferedImage elements.

*/

public class Assets{
  
  public static BufferedImage hatchet,hammer,player,tree,rock,fish;
  public static BufferedImage woodenDoor, metalDoor, fishDoor, boneDoor;
  public static BufferedImage woodenDoor2, metalDoor2, fishDoor2, boneDoor2;
  public static BufferedImage fishKey,metalKey,woodenKey,boneKey;
  public static BufferedImage pathTile,grassTile,stoneTile,waterTile;
  public static BufferedImage[] pDown,pUp,pLeft,pRight;
  public static BufferedImage[] aDown,aUp,aLeft,aRight;
  public static BufferedImage[] scoreCoin;
  
  public static BufferedImage[] btn_login, btn_register, btn_logout, btn_play, btn_levelSelection, btn_demo, btn_menu, btn_highscore, btn_restart;
  public static BufferedImage[] btn_one, btn_two, btn_three, btn_four, btn_null;
  public static BufferedImage[] medal;
  public static BufferedImage Poster;
  static final int width=50,height=50;
  static final int btn_width=67, btn_height = 67;
  public static void init(){
    
    SpriteSheet ButtonSet = new SpriteSheet(ImageLoader.loadImages("res/ButtonSet.png"));
    //SpriteSheet Poster = new SpriteSheet()
    Poster = ImageLoader.loadImages("res/Poster.png");
    SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImages("res/elements/spriteSheet.png"));
    SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImages("res/elements/playerSprite.png"));
    SpriteSheet tile = new SpriteSheet(ImageLoader.loadImages("res/elements/FinalTile.png"));
    SpriteSheet door = new SpriteSheet(ImageLoader.loadImages("res/elements/wallSprite.png"));
    SpriteSheet coins = new SpriteSheet(ImageLoader.loadImages("res/elements/coins.png"));
    pDown = new BufferedImage[3];
    
    pDown[0] = sheet2.crop(3*width,4*height,width,height);
    pDown[2] = sheet2.crop(4*width,4*height,width,height);
    pDown[1] = sheet2.crop(5*width,4*height,width,height);
    
    pUp = new BufferedImage[3];
    
    pUp[0] = sheet2.crop(3*width,7*height,width,height);
    pUp[2] = sheet2.crop(4*width,7*height,width,height);
    pUp[1] = sheet2.crop(5*width,7*height,width,height);
    
    pLeft = new BufferedImage[3];
    
    pLeft[0] = sheet2.crop(3*width,5*height,width,height);
    pLeft[2] = sheet2.crop(4*width,5*height,width,height);
    pLeft[1] = sheet2.crop(5*width,5*height,width,height);
    
    pRight = new BufferedImage[3];
    
    pRight[0] = sheet2.crop(3*width,6*height,width,height);
    pRight[2] = sheet2.crop(4*width,6*height,width,height);
    pRight[1] = sheet2.crop(5*width,6*height,width,height);
    
    
    aDown = new BufferedImage[3];
    
    aDown[0] = sheet2.crop(0,4*height,width,height);
    aDown[2] = sheet2.crop(width,4*height,width,height);
    aDown[1] = sheet2.crop(2*width,4*height,width,height);
    
    aUp = new BufferedImage[3];
    
    aUp[0] = sheet2.crop(0,7*height,width,height);
    aUp[2] = sheet2.crop(width,7*height,width,height);
    aUp[1] = sheet2.crop(2*width,7*height,width,height);
    
    aLeft = new BufferedImage[3];
    
    aLeft[0] = sheet2.crop(0,5*height,width,height);
    aLeft[2] = sheet2.crop(width,5*height,width,height);
    aLeft[1] = sheet2.crop(2*width,5*height,width,height);
    
    aRight = new BufferedImage[3];
    
    aRight[0] = sheet2.crop(0,6*height,width,height);
    aRight[2] = sheet2.crop(width,6*height,width,height);
    aRight[1] = sheet2.crop(2*width,6*height,width,height);
    
    scoreCoin = new BufferedImage[6];
    
    scoreCoin[0] = coins.crop(0 * width, 0 * height, width, height);
    scoreCoin[1] = coins.crop(1 * width, 0 * height, width, height);
    scoreCoin[2] = coins.crop(2 * width, 0 * height, width, height);
    scoreCoin[3] = coins.crop(3 * width, 0 * height, width, height);
    scoreCoin[4] = coins.crop(4 * width, 0 * height, width, height);
    scoreCoin[5] = coins.crop(5 * width, 0 * height, width, height);
    
    woodenDoor = door.crop(100,0,100,50);
    metalDoor = door.crop(300,0,96,50);
    boneDoor = door.crop(200,0,100,50);
    fishDoor = door.crop(0,0,100,50);
    fishDoor2 = door.crop(0,50,20,100);
    woodenDoor2 = door.crop(20,50,20,100);
    boneDoor2 = door.crop(40,50,20,100);
    metalDoor2 = door.crop(60,50,20,100);
    
    medal = new BufferedImage[2];
    
    medal[0] = ButtonSet.crop(14*btn_width,2*btn_height,btn_width,btn_height);
    medal[1] = ButtonSet.crop(15*btn_width,2*btn_height,btn_width,btn_height);
    
    btn_play = new BufferedImage[2];
    
    btn_play[0] = ButtonSet.crop(10*btn_width,2*btn_height,btn_width,btn_height);
    btn_play[1] = ButtonSet.crop(11*btn_width,2*btn_height,btn_width,btn_height);
    
    btn_menu = new BufferedImage[2];
    
    btn_menu[0] = ButtonSet.crop(0*btn_width,2*btn_height,btn_width,btn_height);
    btn_menu[1] = ButtonSet.crop(1*btn_width,2*btn_height,btn_width,btn_height);
    
    btn_highscore = new BufferedImage[2];
    
    btn_highscore[0] = ButtonSet.crop(8*btn_width,2*btn_height,btn_width,btn_height);
    btn_highscore[1] = ButtonSet.crop(9*btn_width,2*btn_height,btn_width,btn_height);
    
    btn_restart = new BufferedImage[2];
    
    btn_restart[0] = ButtonSet.crop(10*btn_width,1*btn_height,btn_width,btn_height);
    btn_restart[1] = ButtonSet.crop(11*btn_width,1*btn_height,btn_width,btn_height);
    
    
    btn_demo = new BufferedImage[2];
    
    btn_demo[0] = ButtonSet.crop(6*btn_width,btn_height,btn_width,btn_height);
    btn_demo[1] = ButtonSet.crop(7*btn_width,btn_height,btn_width,btn_height);
    
    btn_one = new BufferedImage[2];
    
    btn_one[0] = ButtonSet.crop(6*btn_width,3*btn_height,btn_width,btn_height);
    btn_one[1] = ButtonSet.crop(7*btn_width,3*btn_height,btn_width,btn_height);
    
    btn_null = new BufferedImage[2];
    
    btn_null[0] = ButtonSet.crop(15*btn_width,3*btn_height,btn_width,btn_height);
    btn_null[1] = ButtonSet.crop(15*btn_width,3*btn_height,btn_width,btn_height);
    
    btn_levelSelection = new BufferedImage[2];
    
    btn_levelSelection[0] = ButtonSet.crop(2*btn_width,2*btn_height,btn_width,btn_height);
    btn_levelSelection[1] = ButtonSet.crop(3*btn_width,2*btn_height,btn_width,btn_height);
    
    btn_two = new BufferedImage[2];
    
    btn_two[0] = ButtonSet.crop(8*btn_width,3*btn_height,btn_width,btn_height);
    btn_two[1] = ButtonSet.crop(9*btn_width,3*btn_height,btn_width,btn_height);
    
    btn_three = new BufferedImage[2];
    
    btn_three[0] = ButtonSet.crop(10*btn_width,3*btn_height,btn_width,btn_height);
    btn_three[1] = ButtonSet.crop(11*btn_width,3*btn_height,btn_width,btn_height);
    
    btn_four = new BufferedImage[2];
    
    btn_four[0] = ButtonSet.crop(12*btn_width,3*btn_height,btn_width,btn_height);
    btn_four[1] = ButtonSet.crop(13*btn_width,3*btn_height,btn_width,btn_height);
    
    btn_login = new BufferedImage[2];
    
    btn_login[0] = ButtonSet.crop(12*btn_width,0,btn_width,btn_height);
    btn_login[1] = ButtonSet.crop(13*btn_width,0,btn_width,btn_height);
    
    btn_register = new BufferedImage[2];
    
    btn_register[0] = ButtonSet.crop(14*btn_width,0,btn_width,btn_height);
    btn_register[1] = ButtonSet.crop(15*btn_width,0,btn_width,btn_height);
    
    btn_logout = new BufferedImage[2];
    
    btn_logout[0] = ButtonSet.crop(2*btn_width,3*btn_height,btn_width,btn_height);
    btn_logout[1] = ButtonSet.crop(3*btn_width,3*btn_height,btn_width,btn_height);
    
    hammer = sheet.crop(0,0,width,height);
    tree = sheet.crop(0,50,width,height);
    rock = sheet.crop(100,50,width,height);
    hatchet = sheet.crop(width,0,width,height);
    boneKey= sheet.crop(2*width,0,width,height);
    fishKey = sheet.crop(3*width,0,width,height);
    metalKey = sheet.crop(4*width,0,width,height);
    woodenKey = sheet.crop(5*width,0,width,height);
    grassTile = tile.crop(0,0,64,64);
    pathTile = sheet.crop(7*width,0,width,height);
    stoneTile = sheet.crop(6*width,0,width,height);
    player = sheet2.crop(6*width,0,width,height);
    waterTile= sheet.crop(width,height,width,height);
    fish = sheet.crop(3*width,height,width,height);
  }
}