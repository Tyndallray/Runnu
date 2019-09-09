import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/*


constructor: sets width, height and title of the window and initializes keyManager object

init() :  adds a display object and adds a keyListener to its frame
          calls Asset class initialize function
          initializes State object and calls setState function 
          
update() :  calls keyManager update function
            calls state update function
            
render() :  creates buffer strategy
            draws buffer strategy into graphics object
            clears the screen
            calls update function of state class
            calls bufferstrategy show() and graphics dispose()

run() : initialization()
        fps algorithm
        update()
        render()
        stop()
        
start():  checks if the object has already started, if not then thread starts

stop():   checks if the object has already stoped, if not then thread stops

*/



public class game implements Runnable{
  
  private gameDisp display;
  private int countFPS =0;
  public int width, height;
  private boolean running = false;
  private Thread thread;
  private String title;
  private BufferedImage testImage;
  private SpriteSheet sheet;
  private BufferStrategy bs;
  private Graphics g;
  
  public State pState;
  public State gState;
  public State mState;
  public State lCState;
  public State uSState;
  public State lSState;
  public State dCState;
  public State hState;
  
  private keyManager km;
  private Database database;
  private MouseManager mouseManager;
  //game cam
  private GameCamera cam;
  
  //handler
  private Handler handler;
  
  //Notification
  private Notification notification;
  
  public game(String title, int width, int height)
  {
    this.width= width;
    this.height= height;
    this.title =title;
    km = new keyManager();
    mouseManager= new MouseManager(); 
    database = new Database(handler);
  }
  
  void init(){
    handler = new Handler(this);
    display = new gameDisp(title, width, height);
    display.getFrame().addKeyListener(km);   
    display.getFrame().addMouseListener(mouseManager);
    display.getFrame().addMouseMotionListener(mouseManager);
    display.getCanvas().addMouseListener(mouseManager);
    display.getCanvas().addMouseMotionListener(mouseManager);
    Assets.init();
    cam = new GameCamera(handler,0,0);
    gState = new gameState(handler);
    mState = new MenuState(handler);
    pState = new PauseState(handler);
    lSState = new LevelSelectionState(handler);
    uSState = new UserState(handler);
    lCState = new levelCompleteState(handler);
    dCState = new DemoCompleteState(handler);
    hState = new HighscoreState(handler);
    State.setState(uSState);
    notification = new Notification("",width,height);
  }
  
  void update(){
    km.update();
    if(State.getState() != null)
      State.getState().update();
  }
  
  void render(){
    bs = display.getCanvas().getBufferStrategy();
    if(bs == null){
      display.getCanvas().createBufferStrategy(3);
        return;
    }
    g = bs.getDrawGraphics();
    g.clearRect(0,0,width,height); // to clear the screen
    
    // drawing
    
    if(State.getState() != null)
      State.getState().render(g);
    // drawing ends here
    
    bs.show();
    g.dispose();
    notification.render(g);
  }
  
  public keyManager getKeyManager(){
    return km;
  } 
  public Notification getNotification(){
    return notification;
  }
  public void setNotification(String message){
    notification.setNotification(message);
  }
  public void setNotification(String message, int time){
    notification.setNotification(message,time);
  }
  public MouseManager getMouseManager(){
    return mouseManager;
  } 
  
  public GameCamera getCamera(){
    return cam;
  }
  
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
  
  public void run(){
    
    init();
    
    int fps=50;
    double timePerUpdate = 1000000000/fps;
    long now;
    long lastTime= System.nanoTime();
    int count=0;
    double countTime=0;
    
    while(running){
      now = System.nanoTime();
      if((now-lastTime) > timePerUpdate)
      {
        update();
        render();
        countTime += (now-lastTime);
        lastTime = now;
        count++;
      }
      if(countTime >= 1000000000){
        //System.out.println("FPS: " + count);
        countFPS = count;
        count = 0 ;
        countTime = 0;
      }
      
    }
    stop();
  }
  public synchronized void start(){
    
    if(running)
        return;
    running= true;
    thread = new Thread(this);
    thread.start();
  }
  public synchronized void stop(){
    if(!running)
        return;
    running = false;
    try{
      thread.join();    
    }catch(InterruptedException e){
      e.printStackTrace();
    }
    
  }
  
  public int getFPS(){
    return countFPS;
  }
  public Database getDatabase(){
    return database;
  }
}