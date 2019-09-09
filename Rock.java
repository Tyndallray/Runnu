import java.awt.Graphics;

public class Rock extends StaticEntity{
  public Rock(Handler handler, float x, float y){
    super(handler, x,y,Tile.TILEWIDTH, Tile.TILEHEIGHT);
    
    bounds.x = 15;
    bounds.y = 30;
    bounds.width = 66;
    bounds.height = 66;
  }
  
  public void update(){
    
  }
  
  public void render(Graphics g){
    g.drawImage(Assets.rock,(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),(int)(1.5 * width),(int)(1.5 * height),null);
  }
  
  public boolean isSolid(){
    return true;
  }
  public void die(){
      active = false;
      handler.getLevel().getItemManager().addItem(Item.metalKey.createNew((int) x + (width),(int) y + (height)));
  }
  
}