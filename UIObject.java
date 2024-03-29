import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public abstract class UIObject{
  
  protected float x, y;
  protected int width, height;
  protected boolean hovering = false;
  protected Rectangle bounds;
  
  public UIObject(float x, float y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height= height;
    bounds = new Rectangle((int) x,(int)y, width, height);
  }
  public abstract void update();
  
  public abstract void render(Graphics g);
  
  public abstract void onClick();
  
  public void onMouseMove(MouseEvent e){
    if(bounds.contains(e.getX(), e.getY()))
      hovering=true;
    else
      hovering=false;
  }
  
  public void onMouseRelease(MouseEvent e){
    if(hovering)
      onClick();
  }
  

  
  // getters and setters
  public float getX(){
    return x;
  }
  public float getY(){
    return y;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
  public boolean getHovering(){
    return hovering;
  }
  
  public void setX(float x){
    this.x = x;
  }
  public void setY(float y){
    this.y = y;
  }
  public void setWidth(int width){
    this.width = width;
  }
  public void setHeight(int height){
    this.height = height;
  }
  public void setHovering(boolean hovering){
    this.hovering = hovering;
  }
}