import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class metalDoor extends StaticEntity{
  BufferedImage texture;
  private int width,height;
  private boolean direction;
  private Graphics g;
  public metalDoor(Handler handler, float x, float y, boolean direction){
    super(handler, x,y,Tile.TILEWIDTH, Tile.TILEHEIGHT);
    this.direction = direction;
    if(direction){
      texture = Assets.metalDoor;
      bounds.x = 0;
      bounds.y = 40;
      bounds.width = 124;
      bounds.height = 24;
      width = 2* Tile.TILEWIDTH;
      height = Tile.TILEHEIGHT;
    } else {
      texture = Assets.metalDoor2;
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
    this.g = g;
    g.drawImage(texture,(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),width,height,null);
  }
  
  public boolean isSolid(){
    return true;
  }
  
  public void die(){
    handler.setNotification("Metal door opened");
    if(!direction){
      texture = Assets.metalDoor;
      width = 2* Tile.TILEWIDTH;
      height = Tile.TILEHEIGHT;
      y -= 40;
    } else {
      texture = Assets.metalDoor2;
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
    return 2;
  }
  
}