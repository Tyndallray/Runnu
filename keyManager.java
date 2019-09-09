import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class keyManager implements KeyListener {
  
  private boolean[] keys, justPressed, cantPress;
  public boolean up,down,left,right;
  public boolean attack, aup,adown,aleft,aright;
  public boolean space;
  
  public keyManager(){
    keys = new boolean[256];
    justPressed = new boolean[keys.length];
    cantPress = new boolean[keys.length];
  }
  
  public void update(){
    for(int i =0;i < keys.length; i++){
      if(cantPress[i] && !keys[i]){
        cantPress[i] = false;
      }
      else if(justPressed[i]){
        cantPress[i] = true;
        justPressed[i] = false;
      }
      if(!cantPress[i] && keys[i]){
        justPressed[i] = true;
      }
    }
    
    up = keys[KeyEvent.VK_UP];
    down = keys[KeyEvent.VK_DOWN];
    left = keys[KeyEvent.VK_LEFT];
    right= keys[KeyEvent.VK_RIGHT];
    
    aup= keys[KeyEvent.VK_W];
    adown= keys[KeyEvent.VK_S];
    aleft= keys[KeyEvent.VK_A];
    aright = keys[KeyEvent.VK_D];
    
    space = keys[KeyEvent.VK_SPACE];
  }
  
  public void keyPressed(KeyEvent e){
    if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
      return;
    keys[e.getKeyCode()] = true;
  }
  
  public boolean keyJustPressed(int keyCode){
    if(keyCode < 0 || keyCode >= keys.length)
      return false;
    return justPressed[keyCode];
  }
  
  public void keyReleased(KeyEvent e){
    keys[e.getKeyCode()] = false;
  }
  
  public void keyTyped(KeyEvent e){
    
  }
  //space key
  public boolean isSpace(){
    return space;
  }
  //attacking
  public boolean isAttackingUp(){
    return aup;
  }
  public boolean isAttackingDown(){
    return adown;
  }
  public boolean isAttackingLeft(){
    return aleft;
  }
  public boolean isAttackingRight(){
    return aright;
  }
  
  
}