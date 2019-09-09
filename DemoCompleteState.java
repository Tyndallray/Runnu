import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

public class DemoCompleteState extends State{
  
  private UIManager uiManager;
  private Graphics2D g2d;
  
  public DemoCompleteState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
  }
  public void update(){
    if(changed){
      init();
    }
    uiManager.update();
  }
  
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,400,67,67, Assets.btn_levelSelection, new ClickListener(){
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
    g2d.setColor(Color.white);
    g2d.drawString("Demo Sucessfully completed!", handler.getWidth()/2-80, handler.getHeight()/2);  
    uiManager.render(g);
    
  }
  
  private boolean isChanged(){
    return changed;
  }
}