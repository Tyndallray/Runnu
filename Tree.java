import java.awt.Graphics;

public class Tree extends StaticEntity{
  public Tree(Handler handler, float x, float y){
    super(handler, x,y,Tile.TILEWIDTH, Tile.TILEHEIGHT);
    
    bounds.x = 15;
    bounds.y = 30;
    bounds.width = 66;
    bounds.height = 66;
  }
  
  public void update(){
    
  }
  
  public void render(Graphics g){
    g.drawImage(Assets.tree,(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),(int)(1.5 * width),(int)(1.5 * height),null);
  }
  
  public boolean isSolid(){
    return true;
  }
  public void die(){
      active = false;
      handler.getLevel().getItemManager().addItem(Item.woodenKey.createNew((int) x + (width),(int) y + (height)));
  }
  
}