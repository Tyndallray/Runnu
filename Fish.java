import java.awt.Graphics;

public class Fish extends StaticEntity{
  public Fish(Handler handler, float x, float y){
    super(handler, x,y,Tile.TILEWIDTH, Tile.TILEHEIGHT);
    
    bounds.x = 0;
    bounds.y = 0;
    bounds.width = Tile.TILEWIDTH;
    bounds.height = Tile.TILEHEIGHT;
  }
  
  public void update(){
    
  }
  
  public void render(Graphics g){
    g.drawImage(Assets.fish,(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),(int)(width),(int)(height),null);
  }
  
  public boolean isSolid(){
    return true;
  }
  public void die(){
      active = false;
      handler.setNotification("You found a Fish Key!");
      handler.getLevel().getItemManager().addItem(Item.fishKey.createNew((int) x  + (2*Tile.TILEWIDTH),(int) (y + Tile.TILEHEIGHT/2)));
  }
  
}