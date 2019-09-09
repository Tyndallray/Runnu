import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Item{
  //handler
  public static Item[] items = new Item[100];
  public static Item woodenKey = new Item(Assets.woodenKey,"Wooden Key",0);
  public static Item metalKey = new Item(Assets.metalKey,"Metallic Key",1);
  public static Item boneKey = new Item(Assets.boneKey,"Bone Key",2);
  public static Item fishKey = new Item(Assets.fishKey,"Fish Key",3);
  public static Item scoreCoin = new Item(Assets.scoreCoin,"Score Coin",4);
  public static Item demoKey = new Item(Assets.fishKey,"Demo Key",5);
  
  //class
  public static final int ITEMWIDTH = 50, ITEMHEIGHT = 50;
  
  protected boolean pickedUp = false;
  protected Handler handler;
  protected BufferedImage texture;
  protected BufferedImage[] texture2;
  protected Animation animTexture;
  protected String name;
  protected final int id;
  protected boolean animatedItem;
  protected int x,y, count;
  
  protected Rectangle bounds;
  
  public Item(BufferedImage texture, String name, int id){
    this.texture = texture;
    this.name = name;
    this.id= id;
    count = 1;
    animatedItem = false;
    bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
    
    items[id] = this;
  }
  
  public Item(BufferedImage[] texture2, String name, int id){
    this.texture2 = texture2;
    animTexture = new Animation(100, texture2);
    this.name = name;
    this.id= id;
    count = 1;
    animatedItem = true;
    bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
    
    items[id] = this;
  }
  
  public void update(){
    if(animatedItem)
      animTexture.update();
    if(handler.getLevel().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)){
      pickedUp = true;
      if(id == 5){
        handler.setNotification("Open door using W(up),S(down),A(left),D(right)", 5);
      }
      handler.getLevel().getEntityManager().getPlayer().getInventory().addItem(this);
      handler.getLevel().getEntityManager().getPlayer().getInventory().checkLevelComplete();
    }
  }
  public void render(Graphics g){
    if(handler == null){
      return;
    }
    render(g,(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()));
  }
  
  public void render(Graphics g, int x, int y){
    g.drawImage(getTexture(),x,y,ITEMWIDTH,ITEMHEIGHT,null);
  }
  
  public void setPosition(int x,int y){
    this.x = x;
    this.y = y;
    bounds.x = x;
    bounds.y = y;
  }
  
  
  public Item createNew(int x, int y){
    Item i;
    if(animatedItem){
      i = new Item(texture2, name, id);
    }else{
      i = new Item(texture, name, id);
    }
    i.setPosition(x,y);
    return i;
  }
   
  
  //getters and setters
  
  public void setCount(int count){
    this.count = count;
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void setHandler(Handler handler){
    this.handler= handler;
  }
  public void setTexture(BufferedImage texture){
    this.texture=  texture;
  }
  public void setTexture(BufferedImage[] texture){
    animTexture =  new Animation(250,texture);
  }
  public void setName(String name){
    this.name=  name;
  }
  public BufferedImage getTexture(){
    if(animatedItem){
      return animTexture.getCurrentFrame();
    }else {
      return texture;
    }
  }
  public int getCount(){
    return count;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public Handler getHandler(){
    return handler;
  }
  public int getId(){
    return id;
  }
  public String getName(){
    return name;
  }
  public boolean isPickedUp(){
    return pickedUp;
  }
}
