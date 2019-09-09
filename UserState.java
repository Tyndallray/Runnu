
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class UserState extends State{
  private UIManager uiManager;
  private Login login;
  private Register register;
  private Font f;
  private String username;
  private char[] password;
  public UserState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
    register = new Register(handler);
    f = new Font("papyrus", Font.BOLD, 18);
    login = new Login(handler);
    setChanged();
  }
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 50,handler.getHeight()/2 ,67,67, Assets.btn_login, new ClickListener(){
      public void onClick(){
        login.init();
      }
    }));
    
    uiManager.addObject(new UIImageButton(handler.getWidth()/2 - 50,handler.getHeight()/2 + 90,67,67, Assets.btn_register, new ClickListener(){
      public void onClick(){
       register.init(); 
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
    g.drawString("Existing Player", handler.getWidth()/2 - 190, handler.getHeight()/2 + 30);
    g.drawString("New Player?", handler.getWidth()/2 - 170, handler.getHeight()/2 + 120);
  }
  
}