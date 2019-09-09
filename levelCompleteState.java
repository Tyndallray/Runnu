import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Font;

public class levelCompleteState extends State{
  
  private UIManager uiManager;
  private Graphics2D g2d;
  private Font f;
  
  public levelCompleteState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
    f = new Font("papyrus", Font.BOLD, 18);
  }
  public void update(){
    if(changed){
      init();
    }
    uiManager.update();
  }
  
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 30,handler.getHeight()/2,67,67, Assets.btn_levelSelection, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().lSState);
        handler.getGame().lSState.setChanged();
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 30,handler.getHeight()/2 + 90,67,67, Assets.btn_menu, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().mState);
        handler.getGame().mState.setChanged();
      }
    }));
    changed = false;
    //System.out.println("levelCompleteState");
  }
  
  public void render(Graphics g){
    g2d = (Graphics2D) g;
    handler.getLevel().render(g);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    g2d.setColor(Color.black);
    g2d.fillRect(0,0,handler.getGame().getWidth(),handler.getGame().getHeight());
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    handler.getLevel().getEntityManager().getPlayer().getInventory().render(g,4);    
    uiManager.render(g);
    handler.getLevel().getTimer().setHandler(handler);
    handler.getLevel().getTimer().renderStatistics(g);
    g.setFont(f);
    g.drawString("Menu", handler.getGame().getWidth() / 2 - 100, handler.getGame().getHeight() / 2 + 120);
    g.drawString("Level Selection", handler.getGame().getWidth() / 2 - 170, handler.getGame().getHeight() / 2 + 30);
  }
  
  private boolean isChanged(){
    return changed;
  }
}