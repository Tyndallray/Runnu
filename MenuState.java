import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class MenuState extends State{
  
  private UIManager uiManager;
  private Login login;
  private String username;
  private Font f;
  private char[] password;
  public MenuState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
    f = new Font("papyrus", Font.BOLD, 18);
    setChanged();
  }
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,handler.getHeight()/2,67,67, Assets.btn_play, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().lSState);
        handler.getGame().lSState.setChanged();
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,handler.getHeight()/2 + 90,67,67, Assets.btn_logout, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().uSState);
        handler.getGame().uSState.setChanged();
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,handler.getHeight()/2 + 180,67,67, Assets.btn_highscore, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().hState);
        handler.getGame().hState.setChanged();
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2,handler.getHeight()/2 + 270,67,67, Assets.btn_demo, new ClickListener(){
      public void onClick(){
        gameState gState;
        //handler.getMouseManager().setUIManager(null);
        gState = (gameState)handler.getGame().gState;
        gState.loadLevel(0,"demo.txt",60,1);
        
        State.setState(handler.getGame().gState);
        handler.getGame().gState.setChanged();
        handler.setNotification("Press Arrow Keys to Move! Go and pick up the key!",10);
      }
    }));
  }
  
  public void update(){
    uiManager.update();
    if(changed){
      init();
      changed = false;
    }
    //handler.getMouseManager().setUIManager(null);
  }
  public void render(Graphics g){
    g.setFont(f);
    g.setColor(Color.white);
    g.drawImage(Assets.Poster,0,0,handler.getWidth(),handler.getHeight(),null);
    uiManager.render(g);
    g.drawString("Play", handler.getWidth()/2 -55, handler.getHeight()/2 + 30);
    g.drawString("Logout", handler.getWidth()/2 - 80, handler.getHeight()/2 + 120);
    g.drawString("Highscore", handler.getWidth()/2 - 100, handler.getHeight()/2 + 210);
    g.drawString("Demo", handler.getWidth()/2 - 65, handler.getHeight()/2 + 300);
  }
  
}