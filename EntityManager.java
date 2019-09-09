import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
public class EntityManager{
  
  private Handler handler;
  private player Player;
  private ArrayList<entity> entities;
  private Comparator<entity> renderSorter = new Comparator<entity>(){
    
    public int compare(entity a, entity b){
      if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
        return -1;
      return 1;
    }
  
  };
  
  public EntityManager(Handler handler, player Player){
    this.handler = handler;
    this.Player = Player;
    entities = new ArrayList<entity>();
    addEntity(Player);
  }
  
  public void update(){
    Iterator<entity> it = entities.iterator();
    while(it.hasNext()){
      entity e = it.next();
      e.update();
      if(!e.isActive())
        it.remove();
    }
    entities.sort(renderSorter);
  }
  
  public void render(Graphics g){
    for(entity e : entities){
      e.render(g);
    }
  }
  
  public void addEntity(entity e){
    entities.add(e);
  }
  
  
  // getter and setters
  
  public ArrayList<entity> getEntities(){
    return entities;
  }
  
  public void setEntities(ArrayList<entity> entities){
    this.entities = entities;
  }
  
  public player getPlayer(){
    return Player;
  }
  
  public void setPlayer(player Player){
    this.Player = Player;
  }
  
  public Handler getHandler(){
    return handler;
  }
  
  public void setHandler(Handler handler){
    this.handler =handler;
  }
  
}