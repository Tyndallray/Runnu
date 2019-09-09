import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class boneDoor extends StaticEntity{
  BufferedImage texture;
  private int width,height;
  private boolean direction;
  public boneDoor(Handler handler, float x, float y, boolean direction){
    super(handler, x,y,Tile.TILEWIDTH, Tile.TILEHEIGHT);
    this.direction = direction;
    if(direction){
      texture = Assets.boneDoor;
      bounds.x = 0;
      bounds.y = 40;
      bounds.width = 124;
      bounds.height = 24;
      width = 2* Tile.TILEWIDTH;
      height = Tile.TILEHEIGHT;
    } else {
      texture = Assets.boneDoor2;
      bounds.x = 0;
      bounds.y = 0;
      bounds.width = 30;
      bounds.height = 100;
      width = 30;
      height = 2 * Tile.TILEHEIGHT;
    }
  }
  
  public void update(){
    
  }
  
  public void render(Graphics g){
    g.drawImage(texture,(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),width,height,null);
  }
  
  public boolean isSolid(){
    return true;
  }
  
  public void die(){
    handler.setNotification("Bone door opened");
    if(!direction){
      texture = Assets.boneDoor;
      width = 2* Tile.TILEWIDTH;
      height = Tile.TILEHEIGHT;
      y -= 40;
    } else {
      texture = Assets.boneDoor2;
      width = 30;
      height = 100;
    }
    bounds.x = 0;
    bounds.y = 0;
    bounds.width = 0;
    bounds.height = 0;
    direction = !direction;
  }
  
  public int isDoor(){
    return 3;
  }
  
}