import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.AlphaComposite;

public class PauseState extends State{
  
  private UIManager uiManager;
  private Graphics2D g2d;
  private Font f,f2;
  
  public PauseState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
    f = new Font("papyrus", Font.BOLD, 24);
    f2 = new Font("papyrus", Font.BOLD, 18);
  }
  public void update(){
    if(changed){
      init();
    }
    uiManager.update();
    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
      handler.getMouseManager().setUIManager(null);
      State.setState(handler.getGame().gState);
    }
  }
  
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,handler.getHeight()/2,67,67, Assets.btn_play, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        handler.getLevel().getTimer().resetPaused();
        State.setState(handler.getGame().gState);
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,handler.getHeight()/2 +90,67,67, Assets.btn_menu, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().mState);
        handler.getGame().mState.setChanged();
      }
    }));
    changed = false;
    //System.out.println("pausedState");
  }
  
  public void render(Graphics g){
    g2d = (Graphics2D) g;
    handler.getLevel().render(g);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    g2d.setColor(Color.black);
    g2d.fillRect(0,0,handler.getGame().getWidth(),handler.getGame().getHeight());
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    uiManager.render(g);
    g.setFont(f);
    g.setColor(Color.white);
    g.drawString("Paused", handler.getGame().getWidth() / 2 - 60, handler.getGame().getHeight() / 2 - 50);
    g.setFont(f2);
    g.drawString("Resume", handler.getWidth()/2 - 90, handler.getHeight()/2 + 30);
    g.drawString("Main Menu", handler.getWidth()/2 - 120, handler.getHeight()/2 + 120);
  }
  
  private boolean isChanged(){
    return changed;
  }
}