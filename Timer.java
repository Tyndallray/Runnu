import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

public class Timer{
  private int durationInSeconds;
  private long startTime;
  private long lastTime;
  private long timeEllapsed = 0;
  private boolean paused = false;
  private boolean stopped = false;
  private boolean started = false;
  private long lastTimePrinted;
  private Font f;
  private Handler handler;
  public Timer(int durationInSeconds){
    this.durationInSeconds = durationInSeconds;
    startTime = System.currentTimeMillis();
    lastTime = System.currentTimeMillis();
    f = new Font("papyrus", Font.BOLD, 18);
  }
  
  public void update(){
    if(paused || stopped){
      return;
    }
    timeEllapsed += (System.currentTimeMillis() - lastTime);
    lastTime = System.currentTimeMillis();
    checkTimeUp();
    if(lastTimePrinted != (durationInSeconds - (timeEllapsed / 1000))){
      lastTimePrinted = durationInSeconds - (timeEllapsed/1000);
    }
  }
  public void render(Graphics g){
    g.setFont(f);
    g.setColor(Color.white);
    g.drawString("Time",20,30);
    g.drawString((String.format("%02d", (int)((durationInSeconds - (timeEllapsed / 1000)) / 60))) + ":" + (String.format("%02d", ((durationInSeconds - (timeEllapsed / 1000)) % 60))),20,50);
  }
  
  public void start(){
    startTime = lastTime = System.currentTimeMillis();
    started = true;
    paused = false;
    stopped = false;
  }
  public void stop(){
    System.out.println("Stopped!");
    stopped = true;
  }
  public void togglePause(){
    if(paused){
      lastTime = System.currentTimeMillis();
    }
    paused = !paused;
  }
  public void restart(){
    startTime = lastTime = System.currentTimeMillis();
  }
  private void checkTimeUp(){
    if((timeEllapsed / 1000) >= durationInSeconds){
      stopped = true;
    }
  }
  
  public void renderStatistics(Graphics g){
    g.drawString("Time Left",(int)handler.getGame().getWidth() / 2 - 255,150+80);
    g.drawString((durationInSeconds - (timeEllapsed / 1000)) +" seconds",(int)handler.getGame().getWidth() / 2 - 100,150+80);
    g.drawString("" + (durationInSeconds - (timeEllapsed / 1000)) * 100,(int)handler.getGame().getWidth() / 2 + 50,150+80);
    g.drawString("Bonus Score",(int)handler.getGame().getWidth() / 2 - 285,150+120);
    
    g.drawString("" + (durationInSeconds - (timeEllapsed / 1000)) * 10 * handler.getLevel().getEntityManager().getPlayer().getInventory().getCount(4),(int)handler.getGame().getWidth() / 2 + 50,150+120);
    g.drawString("Total",(int)handler.getGame().getWidth() / 2 - 255,150+160);
    g.drawString("" + getScore(),(int)handler.getGame().getWidth() / 2 + 50,150+160);
    
  }
  
   public long getScore(){
    return (((durationInSeconds - (timeEllapsed / 1000)) * 10 * handler.getLevel().getEntityManager().getPlayer().getInventory().getCount(4)) + ((durationInSeconds - (timeEllapsed / 1000)) * 100) + handler.getLevel().getEntityManager().getPlayer().getInventory().getScore());
  }
  public long getTimeLeft(){
    return (durationInSeconds - (timeEllapsed / 1000)) * 10;
  }
  public int getTimeEllapsed(){
    return (int)(timeEllapsed/1000);
  }
  public long getScore2(){
    return ((durationInSeconds - (timeEllapsed / 1000)) * 100);
  }
  
  public void init(){
    timeEllapsed = 0;
    paused = false;
    stopped = false;
    started = false;
  }
  public boolean isTimeUp(){
    return stopped;
  }
  public boolean isStarted(){
    return started;
  }
  public boolean isPaused(){
    return paused;
  }
  public void setHandler(Handler handler){
    this.handler = handler;
  }
  public void setDuration(int durationInSeconds){
    this.durationInSeconds = durationInSeconds;
    init();
  }
  public void resetPaused(){
    paused = false;
  }
}