import java.awt.image.BufferedImage;
import java.awt.Graphics;


public class UIImageButton extends UIObject{
  
  private BufferedImage[] images;
  private ClickListener clicker;
  
  public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker){
    super(x,y,width,height);
    this.images = images;
    this.clicker = clicker;
  }
  
  public void update(){
    
  }
  public void render(Graphics g){
    if(hovering)
      g.drawImage(images[1], (int)x, (int)y,width,height, null);
    else
      g.drawImage(images[0], (int)x, (int)y,width,height, null);
  }
  public void onClick(){
    clicker.onClick();
  }
  public void setImage(BufferedImage[] images){
    this.images = images;
  }
}