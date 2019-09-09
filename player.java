import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Color;
public class player extends creature{
  private final int WOODEN =0, METAL = 1, BONE = 2, FISH = 3;
  //animation
  private Animation animDown,animUp,animLeft,animRight;
  private Animation animADown,animAUp,animALeft,animARight;
  private int direction;
  //inventory
  private Inventory inventory;
  //attack timer
  private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;
  
  public player(Handler handler, float x, float y){
    super(handler,x,y,DEFAULT_WIDTH, DEFAULT_HEIGHT);
    animDown = new Animation(250, Assets.pDown);
    animUp = new Animation(250, Assets.pUp);
    animLeft = new Animation(250, Assets.pLeft);
    animRight = new Animation(250, Assets.pRight);
    animADown = new Animation(250, Assets.aDown);
    animAUp = new Animation(250, Assets.aUp);
    animALeft = new Animation(250, Assets.aLeft);
    animARight = new Animation(250, Assets.aRight);
    direction = 0;
    bounds.x = 15;
    bounds.y = 30;
    bounds.width = 24;
    bounds.height = 32;
    inventory=new Inventory(handler);
  }
  
  public void die(){
    System.out.println("you lose");
  }
  
  public void update(){
    animDown.update();
    animUp.update();
    animLeft.update();
    animRight.update();
    animADown.update();
    animAUp.update();
    animALeft.update();
    animARight.update();
    move();
    getInput();
    handler.getCamera().centerOnEntity(this);
    
    checkAttacks();
    
    inventory.update();
  }
  
  private void checkAttacks(){
    attackTimer += System.currentTimeMillis() - lastAttackTimer;
    lastAttackTimer = System.currentTimeMillis();
    if(attackTimer < attackCooldown)
      return;
    
    Rectangle cb= getCollisionBounds(0,0);
    Rectangle ar = new Rectangle();
    int arSize = 20;
    ar.width = arSize;
    ar.height = arSize;
    
    if(handler.getKeyManager().aup){
      ar.x = cb.x + cb.width / 2 - arSize / 2;
      ar.y = cb.y - arSize;
      
    }
    else if(handler.getKeyManager().adown){
      ar.x = cb.x + cb.width / 2 - arSize / 2;
      ar.y = cb.y + cb.height;
      
    }
    else if(handler.getKeyManager().aleft){
      ar.x = cb.x - arSize;
      ar.y = cb.y + cb.height/2 - arSize / 20;
      
    }
    else if(handler.getKeyManager().aright){
      ar.x = cb.x + cb.width;
      ar.y = cb.y + cb.height/2 - arSize / 20;
      
    }
    else{
      return;
    }
    
    attackTimer = 0;
    
    for(entity e: handler.getLevel().getEntityManager().getEntities()){
      if(e.equals(this))
        continue;
      if(e.getCollisionBounds(0,0).intersects(ar)){
        if(e.isDoor() == 1){
          if(inventory.getCount(WOODEN) > 0){
            e.hurt(10);
            inventory.setCount(WOODEN, (inventory.getCount(WOODEN) - 1));
            return;
          }
          handler.setNotification("Wooden key not found");
          return;
        }
        else if(e.isDoor() == 2){
          if(inventory.getCount(METAL) > 0){
            e.hurt(10);
            inventory.setCount(METAL, (inventory.getCount(METAL) - 1));
            return;
          }
          if(inventory.getCount(5) > 0){
            e.hurt(10);
            inventory.setCount(5, (inventory.getCount(5) - 1));
            handler.setNotification("Perform action on water [press and hold A key]", 10);
            return;
          }
          handler.setNotification("Metal key not found");
          return;
        }
        else if(e.isDoor() == 3){
          if(inventory.getCount(BONE) > 0){
            e.hurt(10);
            inventory.setCount(BONE, (inventory.getCount(BONE) - 1));
            return;
          }
          handler.setNotification("Bone key not found");
          return;
        }
        else if(e.isDoor() == 4){
          if(inventory.getCount(FISH) > 0){
            e.hurt(10);
            inventory.setCount(FISH, (inventory.getCount(FISH) - 1));
            return;
          }
          handler.setNotification("Fish key not found");
          return;
        } else if(e.isDoor() == 0){
          e.hurt(1);
          return;
        } else{
          System.out.println("Unable to do anything");
          return;
        }
        
      }
    }
  }
  
  private void getInput(){
    xMove =0;
    yMove =0;
    
    if(handler.getKeyManager().up)
      yMove = -speed;
    if(handler.getKeyManager().down)
      yMove = speed;
    if(handler.getKeyManager().left)
      xMove = -speed;
    if(handler.getKeyManager().right)
      xMove = speed;
  }
  
  public void render(Graphics g){
    g.drawImage(getCurrentAnimationFrame(),(int)(x - handler.getCamera().getxOffset()),(int)(y - handler.getCamera().getyOffset()),width,height,null);
    //g.setColor(Color.red);
    //g.fillRect((int) (x+ bounds.x - handler.getCamera().getxOffset()),(int) (y+ bounds.y - handler.getCamera().getyOffset()),bounds.width,bounds.height);
    inventory.render(g);
  }
 
  private BufferedImage getCurrentAnimationFrame(){
    
    if(handler.getKeyManager().isAttackingUp()){
      return animAUp.getCurrentFrame();
    }else if(handler.getKeyManager().isAttackingDown()){
      return animADown.getCurrentFrame();
    }else if(handler.getKeyManager().isAttackingLeft()){
      return animALeft.getCurrentFrame();
    }else if(handler.getKeyManager().isAttackingRight()){
      return animARight.getCurrentFrame();
    }
    
    
    if(xMove < 0){
      direction = 1;
      return animLeft.getCurrentFrame();
    }else if(xMove>0){
      direction = 2;
      return animRight.getCurrentFrame();
    }else if(yMove<0){
      direction = 3;
      return animUp.getCurrentFrame();
    }else if(yMove>0){
      direction = 0;
      return animDown.getCurrentFrame();
    }else{
      switch(direction){
        case 1:
          return Assets.pLeft[2];
        case 2:
          return Assets.pRight[2];
        case 3:
          return Assets.pUp[2];
        default:
          return Assets.pDown[2];
      }
      
    }
  }
  //getters and setters
  
  public void setInventory(Inventory inventory){
    this.inventory = inventory;
  }
  public Inventory getInventory(){
    return inventory;
  }
}