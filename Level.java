import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class Level{
  private int width, height;
  private int[][] tiles;
  private Handler handler;
  private int spawnX, spawnY;
  private boolean countFPS = false;
  private int levelIndex;
  //entity
  private EntityManager entityManager;
  //item
  private int maxCoinCount;
  private ItemManager itemManager;
  private Timer timer;
  private Font f;
  public boolean timeUp = true;
  public Level(Handler handler,String path, int time){
    this.handler= handler;
    timer = new Timer(time);
    entityManager= new EntityManager(handler, new player(handler,100,100));
    itemManager = new ItemManager(handler);
    
    f = new Font("Arial", Font.BOLD, 12);
    loadLevel(path);    
  }
  
  public void loadLevel(int levelIndex){
    this.levelIndex = levelIndex;
    if(levelIndex == 0){
      itemManager.addItem(Item.demoKey.createNew(64,128));
      entityManager.addEntity(new metalDoor(handler,64*10,64,false));
      entityManager.addEntity(new Fish(handler,64*10,64*4));
      entityManager.addEntity(new fishDoor(handler,64*11,64*6,true));
      itemManager.addItem(Item.scoreCoin.createNew(64*12,7*64));
    }
    else if(levelIndex == 1){
      itemManager.addItem(Item.woodenKey.createNew(100,1850));
      itemManager.addItem(Item.metalKey.createNew(300,1850));
      itemManager.addItem(Item.metalKey.createNew(128,320));
      itemManager.addItem(Item.metalKey.createNew(1984,1408));
      itemManager.addItem(Item.boneKey.createNew(1280,1664));
      itemManager.addItem(Item.boneKey.createNew(1472,1856));

      entityManager.addEntity(new woodenDoor(handler,192,1472,false));
      entityManager.addEntity(new fishDoor(handler,1216,1920,false));
      entityManager.addEntity(new Fish(handler,896,1344));
      entityManager.addEntity(new metalDoor(handler,192,960,false));
      entityManager.addEntity(new metalDoor(handler,640,64,false));
      entityManager.addEntity(new metalDoor(handler,512,896,true));
      entityManager.addEntity(new boneDoor(handler,1152,1065,true));
      entityManager.addEntity(new boneDoor(handler,1152,384,false));
      entityManager.addEntity(new boneDoor(handler,1344,768,false));

      itemManager.addItem(Item.boneKey.createNew(1792,768));
      itemManager.addItem(Item.scoreCoin.createNew(128,1280));
      itemManager.addItem(Item.scoreCoin.createNew(4*64,26*64));
      itemManager.addItem(Item.scoreCoin.createNew(10*64,29*64));
      itemManager.addItem(Item.scoreCoin.createNew(23*64,29*64));
      itemManager.addItem(Item.scoreCoin.createNew(31*64,29*64));
      itemManager.addItem(Item.scoreCoin.createNew(24*64,22*64));
      itemManager.addItem(Item.scoreCoin.createNew(27*64,64*22));
      itemManager.addItem(Item.scoreCoin.createNew(22*64,16*64));
      itemManager.addItem(Item.scoreCoin.createNew(1*64,11*64));
      itemManager.addItem(Item.scoreCoin.createNew(16*64,12*64));
      itemManager.addItem(Item.scoreCoin.createNew(23*64,13*64));
      itemManager.addItem(Item.scoreCoin.createNew(33*64,11*64));
      itemManager.addItem(Item.scoreCoin.createNew(6*64,7*64));
      itemManager.addItem(Item.scoreCoin.createNew(26*64,7*64));

    }
    else if(levelIndex == 2){
      itemManager.addItem(Item.metalKey.createNew(2*64,31*64));
      itemManager.addItem(Item.woodenKey.createNew(7*64,64*7));
      itemManager.addItem(Item.boneKey.createNew(22*64,64*15));
      itemManager.addItem(Item.boneKey.createNew(49*64,38*64));
      
      entityManager.addEntity(new Fish(handler,26*64,3*64));
      entityManager.addEntity(new Fish(handler,41*64,11*64));
      entityManager.addEntity(new boneDoor(handler,10*64,32*64,true));
      entityManager.addEntity(new boneDoor(handler,31*64,19*64,false));
      entityManager.addEntity(new woodenDoor(handler,31*64,37*64,false));
      entityManager.addEntity(new woodenDoor(handler,45*64,5*64,true));
      entityManager.addEntity(new metalDoor(handler,7*64,20*64,true));
      entityManager.addEntity(new fishDoor(handler,27*64,8*64,true));
      entityManager.addEntity(new fishDoor(handler,50*64,23*64,true));
      
      itemManager.addItem(Item.scoreCoin.createNew(8*64,31*64));
      itemManager.addItem(Item.scoreCoin.createNew(45*64,16*64));
      itemManager.addItem(Item.scoreCoin.createNew(27*64,10*64));
      itemManager.addItem(Item.scoreCoin.createNew(7*64,36*64));
      itemManager.addItem(Item.scoreCoin.createNew(2*64,22*64));
      itemManager.addItem(Item.scoreCoin.createNew(34*64,38*64));
      itemManager.addItem(Item.scoreCoin.createNew(46*64,38*64));
      itemManager.addItem(Item.scoreCoin.createNew(40*64,2*64));
      itemManager.addItem(Item.scoreCoin.createNew(40*64,6*64));
      itemManager.addItem(Item.scoreCoin.createNew(49*64,21*64));
      itemManager.addItem(Item.scoreCoin.createNew(25*64,30*64));
      itemManager.addItem(Item.scoreCoin.createNew(5*64,11*64));
      itemManager.addItem(Item.scoreCoin.createNew(10*64,2*64));
      itemManager.addItem(Item.scoreCoin.createNew(10*64,7*64));
      itemManager.addItem(Item.scoreCoin.createNew(17*64,17*64));
    }
    entityManager.getPlayer().setX(spawnX);
    entityManager.getPlayer().setY(spawnY);
    
  }
  
  
  public void update(){
    itemManager.update();
    entityManager.update();
    timer.update();
  }

  public void render(Graphics g){
    
    int xStart= (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH);
    
    int yStart= (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT);
    
    int yEnd= (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
    
    int xEnd= (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
    
    for(int y=yStart; y<yEnd; y++){
      for(int x=xStart;x<xEnd;x++){
          getTile(x,y).render(g, (int)(x*Tile.TILEWIDTH - handler.getCamera().getxOffset()) , (int)(y*Tile.TILEHEIGHT- handler.getCamera().getyOffset()));
      }
    }
    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))
      timer.togglePause();
    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F))
      countFPS = !countFPS;
    if(countFPS){
      g.setFont(f);
      g.setColor(Color.white);
      g.drawString("FPS:" + handler.getGame().getFPS(), 30,handler.getGame().getHeight() - 60);
    }
    itemManager.render(g);
    entityManager.render(g);  
    timer.render(g);
    if(timer.isTimeUp() && timeUp && levelIndex != 0){
      handler.updateHighscore();
      handler.getGame().lCState.setChanged();
      State.setState(handler.getGame().lCState);
      timeUp = false;
    }else if(timer.isTimeUp() && timeUp && levelIndex == 0){
      handler.getGame().dCState.setChanged();
      State.setState(handler.getGame().dCState);
      timeUp = false;
    }
  }
  
  public Tile getTile(int x,int y){
    if(x<0 || y<0 || x>= width || y>= height)
      return Tile.grassTile;
    
    Tile t = Tile.tiles[tiles[x][y]];
    if(t == null)
      return Tile.grassTile;
    return t;
  }
  
  private void loadLevel(String path){
    String file = Utility.loadFileAsString(path);
    String[] tokens = file.split("\\s+");
    width = Utility.parseInt(tokens[0]);
    height = Utility.parseInt(tokens[1]);
    spawnX = Utility.parseInt(tokens[2]);
    spawnY = Utility.parseInt(tokens[3]);
    //maxCoinCount = 1;
    tiles = new int[width][height];
    for(int y = 0;y<height;y++)
      for(int x=0;x<width;x++)
        tiles[x][y] = Utility.parseInt(tokens[(x+y*width) + 4]);
    
  }
  
   //getters and setters
  public int getWidth(){
    return width;
  }
  
  public int getHeight(){
    return height;
  }
  
  public EntityManager getEntityManager(){
    return entityManager;
  }
  
  public void setItemManager(ItemManager itemManager){
    this.itemManager=  itemManager;
  }
  public ItemManager getItemManager(){
    return itemManager;
  }
  public void setHandler(Handler handler){
    this.handler = handler;
  }
  public Handler getHandler(){
    return handler;
  }
  public Timer getTimer(){
    return timer;
  }
  public int getMaxCoinCount(){
    return maxCoinCount;
  }
  public void setMaxCoinCount(int maxCoinCount){
    this.maxCoinCount = maxCoinCount;
  }
  public int getLevelIndex(){
    return levelIndex;
  }
}