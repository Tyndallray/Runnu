import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Inventory{
  private Handler handler;
  private boolean active = true;
  private ArrayList<Item> inventoryItems;
  private Font f;
  private Item coin;
  private BufferedImage[] medalArr;
  public Inventory(Handler handler){
    this.handler = handler;
    inventoryItems = new ArrayList<Item>();
    f = new Font("papyrus", Font.BOLD,24);
    medalArr = new BufferedImage[3];
    medalArr[0] = medalArr[1] = medalArr[2] = Assets.medal[1];
  }
  
  public void update(){
    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
      active = !active;
    if(!active)
      return;
  }
  
  public void render(Graphics g){
    if(!active)
      return;
    int j = 1;
    g.setFont(f);
    g.setColor(Color.white);
    g.drawString("Inventory", handler.getGame().getWidth() - 120, 20);
    for(Item i : inventoryItems){
      g.drawImage(i.getTexture(), handler.getGame().getWidth() - 135, (55 * j) - 10,50,50,null);
      g.drawString("x"+ i.getCount(), handler.getGame().getWidth() - 80, 55 * j);
      j++;
    }
  }
  public void render(Graphics g,int id){
    
    g.setFont(f);
    g.setColor(Color.white);
    g.drawString("Statistics", (int)handler.getGame().getWidth() / 2 - 100, 150);
    g.drawString("Scores", (int)handler.getGame().getWidth() / 2 + 50, 150);
    for(Item i : inventoryItems){
      if(i.getId() == id){
        g.drawImage(Assets.scoreCoin[0],(int)handler.getGame().getWidth() / 2 - 100, 150 + (40) - 10,15,15,null);
        g.drawString("x"+ i.getCount(), (int)handler.getGame().getWidth() / 2 - 80, 150 + 40 );
        g.drawString("" + i.getCount() * 500, (int)handler.getGame().getWidth() / 2 + 50,150 + 40);
        
      }
    }
    g.drawImage(medalArr[0], (int)handler.getGame().getWidth()/2 - 180, 350,60,60,null);
    g.drawImage(medalArr[1], (int)handler.getGame().getWidth()/2 - 90, 350,60,60,null);
    g.drawImage(medalArr[2], (int)handler.getGame().getWidth()/2, 350,60,60,null);
  }
  
  public long getScore(){
    return getCount(4) * 500;
  }
  public int getMedals(){
    int count= getCount(4);
    if(count == handler.getLevel().getMaxCoinCount()){
      medalArr[2] = medalArr[1] = medalArr[0] = Assets.medal[0];
      return 3;
    }
    else if(count == (handler.getLevel().getMaxCoinCount()-1)){
      medalArr[1] = medalArr[0] = Assets.medal[0];
      return 2;
    }
    else if(count == (handler.getLevel().getMaxCoinCount()-2)){
      medalArr[0] = Assets.medal[0];
      return 1;
    }
    else{
      return 0;
    }
  }
  //inventory methods
  public void addItem(Item item){
    for(Item i : inventoryItems){
      if(i.getId() == item.getId()){
        i.setCount(i.getCount() + item.getCount());
        return;
      }
    }
    inventoryItems.add(item);
  }
  
  public void removeAllItems(){
    for(Item i: inventoryItems){
      i.setCount(0);
    }
  }
  
  public void removeItem(Item item){
    for(Item i : inventoryItems){
      if(i.getId() == item.getId()){
        i.setCount(i.getCount() - 1);
        return;
      }
    }
  }
  
  public void checkLevelComplete(){
    for(Item i : inventoryItems){
      if(i.getId() == 4){
        System.out.println(i.getName() + "count : " + getCount(i));
        if(getCount(i) == handler.getLevel().getMaxCoinCount() && handler.getLevel().getLevelIndex() != 0){
          handler.setMedals(getMedals());
          handler.updateHighscore(getCount(4),getScore());
          
          handler.getGame().lCState.setChanged();
          State.setState(handler.getGame().lCState);
          return;
        }else if(getCount(i) == handler.getLevel().getMaxCoinCount() && handler.getLevel().getLevelIndex() == 0){
          handler.getGame().dCState.setChanged();
          State.setState(handler.getGame().dCState);
          return;
        }
      }
    }
  }
  
  //getters and setters
  public int getCount(Item item){
    for(Item i : inventoryItems){
      if(i.getId() == item.getId()){
        return i.getCount();
      }
    }
    return 0;
  }
  
  public int getCount(int id){
    for(Item i : inventoryItems){
      if(i.getId() == id){
        return i.getCount();
      }
    }
    return 0;
  }
  
  public void setCount(int id, int n){
    for(Item i : inventoryItems){
      if(i.getId() == id){
        i.setCount(n);
      }
    }
  }
  
  public Handler getHandler(){
    return handler;
  }
  public void setHandler(Handler handler){
    this.handler = handler;
  }
}