import java.awt.Graphics;
public class gameState extends State{
  private Level level;
  private String LevelFile;
  private int time;
  private int levelIndex;
  private int maxCoinCount;
  public gameState(Handler handler){
    super(handler);
  }
  public void loadLevel(){
    level = new Level(handler,LevelFile,time);
    
    handler.setLevel(level);
    handler.getLevel().setMaxCoinCount(maxCoinCount);
    handler.getLevel().loadLevel(levelIndex);
  }
  public void loadLevel(int levelIndex, String LevelFile, int time, int maxCoinCount){
    this.LevelFile = LevelFile;
    this.levelIndex = levelIndex;
    this.time = time;
    this.maxCoinCount = maxCoinCount;
  }
  public void update(){
    if(changed){
      loadLevel();
      changed=false;
    }
    //System.out.println("here");
    if(!level.getTimer().isStarted()){
      level.getTimer().start();
    }
    if(level.getTimer().isPaused()){
      State.setState(handler.getGame().pState);
      handler.getGame().pState.setChanged();
      return;
    }
    if(level.getTimer().isTimeUp()){
      return;
    }
    level.update();
  }
  
  public void setChanged(String ha){
    System.out.println(ha);
  }
  
  public void render(Graphics g){
      level.render(g);
      handler.getGame().getNotification().render(g);
  }
}