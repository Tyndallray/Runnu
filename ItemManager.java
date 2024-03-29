import java.util.ArrayList;
import java.util.*;
import java.awt.Graphics;

public class ItemManager{
  private Handler handler;
  private ArrayList<Item> items;
  public ItemManager(Handler handler){
    this.handler = handler;
    items = new ArrayList<Item>();
  }
  
  public void update(){
    Iterator<Item> it = items.iterator();
    while(it.hasNext()){
      Item i = it.next();
      i.update();
      if(i.isPickedUp())
        it.remove();
    }
  }
  
  public void render(Graphics g){
    for(Item i : items)
      i.render(g);
  }
  
  public void addItem(Item i){
    i.setHandler(handler);
    items.add(i);
  }
  
  public void removeItem(Item i){
    i.setHandler(handler);
    items.remove(i);
  }
  
  //getters and setters
  
  public void setHandler(Handler handler){
    this.handler = handler;
  }
  public Handler getHandler(){
    return handler;
  }
}