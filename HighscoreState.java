import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.AlphaComposite;
import java.sql.*;

public class HighscoreState extends State{
  
  private UIManager uiManager;
  private Graphics2D g2d;
  private Font f,f2;
  private ResultSet rs;
  //private String values;
  public HighscoreState(Handler handler){
    super(handler);
    uiManager = new UIManager(handler);
    f = new Font("papyrus", Font.BOLD, 24);
    f2 = new Font("papyrus", Font.BOLD, 18);
    /*values= new String[4][5];
    for(int i = 0; i<5; i++)
      for(int j=0; j<4; j++)
        values[i][j] = "-";*/
  }
  public void update(){
    if(changed){
      init();
    }
    uiManager.update();
  }
  
  public void init(){
    handler.getMouseManager().setUIManager(uiManager);
    uiManager.addObject(new UIImageButton(100,50,67,67, Assets.btn_one, new ClickListener(){
      public void onClick(){
        //display highscore for level one | Player number | Score | time Taken | level
        handler.getDatabase().getResultHighscore(1);
      }
    }));
    uiManager.addObject(new UIImageButton(200,50,67,67, Assets.btn_two, new ClickListener(){
      public void onClick(){
        //display highscore for level two
        handler.getDatabase().getResultHighscore(2);
      }
    }));
    uiManager.addObject(new UIImageButton(handler.getWidth() - 100,50,67,67, Assets.btn_menu, new ClickListener(){
      public void onClick(){
        //menu
        State.setState(handler.getGame().mState);
        handler.getGame().mState.setChanged();
      }
    }));
    changed = false;
    //System.out.println("pausedState");
  }
  
  public void render(Graphics g){
    g2d = (Graphics2D) g;
    handler.getGame().mState.render(g);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    g2d.setColor(Color.black);
    g2d.fillRect(0,0,handler.getGame().getWidth(),handler.getGame().getHeight());
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    uiManager.render(g);
    g.setFont(f);
    g.setColor(Color.white);
    
    
    g.drawString("Player Name", handler.getGame().getWidth() / 2 - 350,150);
    g.drawString("Score", handler.getGame().getWidth() / 2 - 150,150);
    g.drawString("Time Taken", handler.getGame().getWidth() / 2 + 50,150);
    g.drawString("Level", handler.getGame().getWidth() / 2 + 250,150);
    
    g.setFont(f2);
    g.drawString("Menu", handler.getGame().getWidth() - 90,120);
    
    Utility.parseString(g, handler);
  }
  /*private void getScoreOne(){
    String file = Utility.loadFileAsString("score.txt");
    String[] tokens = file.split("\\s+");
    
    for(int y = 0;y<5;y++)
      for(int x=0;x<4;x++)
        values[x][y] = Utility.parseInt(tokens[(x+y*4)]);
  }*/
  private boolean isChanged(){
    return changed;
  }
}