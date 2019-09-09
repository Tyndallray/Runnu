import java.awt.Graphics;
import java.awt.Rectangle;
/*
constructor: sets x and y coordinate and width and height
*/



public abstract class entity{
  float x,y;
  public static final int DEFAULT_HEALTH = 10;
  private int health;
  protected int width, height;
  protected Handler handler;
  protected Rectangle bounds;
  
  protected boolean active = true;
  
  public entity(Handler handler,float x,float y,int width, int height){
    this.handler = handler;
    health = DEFAULT_HEALTH;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    bounds = new Rectangle(0,0,width,height);
  }
  
  public abstract void update();
  public abstract void render(Graphics g);
  
  public boolean checkEntityCollision(float xOffset,float yOffset){
    for(entity e : handler.getLevel().getEntityManager().getEntities()){
      if(e.equals(this))
          continue;
      if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)))
          return true;
    }
    return false;
  }
  
  public Rectangle getCollisionBounds(float xOffset, float yOffset){
    return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset),bounds.width,bounds.height);
  }
  
  public void hurt(int amt){
    health -= amt;
    if(health <= 0){
      die();
    }
  }
  
  public abstract void die();
  
  
  //getters and setters
  public int getWidth(){
    return width;  
  }
  public int getHeight(){
    return height;
  }
  public float getX(){
    return x;
  }
  public float getY(){
    return y;
  }
  public void setWidth(int width){
    this.width=width;
  }
  public void setHeight(int height){
    this.height = height;
  }
  public void setX(float x){
    this.x = x;
  }
  public void setY(float y){
    this.y = y;
  }
  public int getHealth(){
    return health;
  }
  public void setHealth(int health){
    this.health = health;
  }
  public void setActive(boolean active){
    this.active = active;
  }
  public boolean isActive(){
    return active;
  }
  public int isDoor(){
    return 0;
  }
}