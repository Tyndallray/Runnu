import java.awt.image.BufferedImage;
public class Animation{
  private int speed, index;
  private long lastTime, timer;
  private BufferedImage[] frames;
  private BufferedImage sendMe;
  
  public Animation(int speed, BufferedImage[] frames){
    this.speed= speed;
    this.frames = frames;
    index = 0;
    timer = 0;
    lastTime = System.currentTimeMillis();
  }
  
  public void update(){
  
    timer += System.currentTimeMillis() - lastTime;
    lastTime = System.currentTimeMillis();
    if(timer > speed){
      index++;
      timer = 0;
      if(index >= frames.length-1)
        index =0;
    }
    sendMe = frames[index];    
  }
  
  public BufferedImage getCurrentFrame(){
    return sendMe;
  }
  
}