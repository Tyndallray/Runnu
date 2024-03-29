import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager{

  private Handler handler;
  private ArrayList<UIObject> objects;

  public UIManager(Handler handler){
    this.handler = handler;
    objects = new ArrayList<UIObject>();
  }
  
  public void update(){
    for(UIObject o: objects)
      o.update();
  }

  public void render(Graphics g){
    for(UIObject o: objects)
      o.render(g);
  }
  
  public void onMouseMove(MouseEvent e){
    for(UIObject o: objects)
      o.onMouseMove(e);
  }
  
  public void onMouseRelease(MouseEvent e){
    for(UIObject o: objects)
      o.onMouseRelease(e);
  }

  
  public void addObject(UIObject o){
    objects.add(o);
  }
  
  public void removeObject(UIObject o){
    objects.remove(o);
  }
  
  public void setHander(Handler handler){
    this.handler= handler;
  }
  
  public void setUIObject(ArrayList<UIObject> objects){
    this.objects = objects;
  }
  
  public Handler getHander(){
    return handler;
  }
  
  public ArrayList<UIObject> getUIObject(){
    return objects;
  }
}