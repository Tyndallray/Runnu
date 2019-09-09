import java.awt.Graphics;

/*


this class extends entity class

constructor: initializes health, speed, movement in x and y direction

move() : adds the changes in xMove and yMove

*/


public abstract class creature extends entity{
  
  
  public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;
  protected float DEFAULT_SPEED = 9.0f;
  float speed;
  
  protected float xMove, yMove;
  
  public creature(Handler handler,float x,float y, int width, int height){
    super(handler,x,y,width,height);
    speed = DEFAULT_SPEED;
    xMove =0;
    yMove=0;
  }
  
  
  public void move(){ 
    if(!checkEntityCollision(xMove,0f))
      moveX();
    if(!checkEntityCollision(0f,yMove))
      moveY();
  }
  
  
  public void moveX(){
    if(xMove > 0 ){ // right
      int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
      
      if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
        x += xMove;
      }
    }else if(xMove < 0){ // left
      int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
      
      if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
        x += xMove;
      }
    }
  }
  
  public void moveY(){
    if(yMove < 0){//up
      int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
      if(!collisionWithTile((int)(x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
        y += yMove;
      }
    }else if(yMove>0){//down
       int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
      if(!collisionWithTile((int)(x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
        y += yMove;
      } 
    }
  }
  
  protected boolean collisionWithTile(int x, int y){
    return  (handler.getLevel().getTile(x,y).isSolid());
  }
  
  public void setxMove(float xMove){
    this.xMove = xMove;
  }
  public void setyMove(float yMove){
    this.yMove = yMove;
  }
  public float getxMove(){
    return xMove;
  }
  public float getyMove(){
    return yMove;
  }
  
  
  public abstract void update();
  public abstract void render(Graphics g);
}