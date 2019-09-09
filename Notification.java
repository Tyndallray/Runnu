import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;


public class Notification{
  private String message;
  private Timer timer;
  private int width;
  private int height;
  private Font f;
  private boolean started = false;
  public Notification(String message,int width,int height){
    this.message = message;
    timer = new Timer(0);
    this.width = width;
    this.height = height;
    f = new Font("papyrus", Font.BOLD, 28);
  }
  
  public void render(Graphics g){
    if(started){
      timer.update();
      if(!timer.isTimeUp()){
        g.setColor(Color.white);
        g.setFont(f);
        g.drawString(message , (width/2) - (message.length()*7), height-60);
      }
    }    
  }
  public void setNotification(String message){
    timer.setDuration(2);
    this.message = message;
    started = true;
    timer.start();
  }
  public void setNotification(String message, int time){
    timer.setDuration(time);
    this.message = message;
    started = true;
    timer.start();
  }
}