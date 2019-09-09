import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class LevelSelectionState extends State{
  
  private UIManager uiManager;
  private gameState gState;
  private Font f,f2;
  
  
  public LevelSelectionState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
    setChanged();
    f = new Font("papyrus", Font.BOLD, 24);
    f2 = new Font("papyrus", Font.BOLD, 18);
  }
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 , handler.getHeight()/2,67,67, Assets.btn_one, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        gState = (gameState)handler.getGame().gState;
        gState.loadLevel(1,"level1.txt", 180,14);
        State.setState(handler.getGame().gState);
        handler.getGame().gState.setChanged();
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 , handler.getHeight()/2 + 90,67,67, Assets.btn_two, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        if(handler.getDatabase().hasMedal(1) == true){
          gState = (gameState)handler.getGame().gState;
          gState.loadLevel(2,"level2.txt", 120,15);
          State.setState(handler.getGame().gState);
          handler.getGame().gState.setChanged();
        }
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 , handler.getHeight()/2 + 180,67,67, Assets.btn_menu, new ClickListener(){
      public void onClick(){
        //handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().mState);
        handler.getGame().mState.setChanged();
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
    g.drawImage(Assets.Poster,0,0,handler.getWidth(),handler.getHeight(),null);
    uiManager.render(g);
    g.setColor(Color.white);
    g.setFont(f);
    g.drawString("Level Selection", handler.getWidth()/2 - 50, handler.getHeight()/2 - 50);
    g.setFont(f2);
    g.drawString("Menu", handler.getWidth()/2 - 60, handler.getHeight()/2 + 210);
  }
  
}