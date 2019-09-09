import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile{
  
  //static BS below
  
  public static Tile[] tiles = new Tile[100];
  public static Tile waterTile = new waterTile(2);
  public static Tile grassTile = new grassTile(1);
  public static Tile stoneTile = new stoneTile(0);
  //BS below
  
  public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
  
  protected BufferedImage texture;
  protected final int id;
  
  public Tile(BufferedImage texture, int id){
    this.texture = texture;
    this.id = id;
    
    tiles[id] = this;
  }

  public boolean isSolid(){
    return false;
  }
  
  public void update(){
    
    
  }
  public void render(Graphics g,int x,int y){
    g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
  }
  public int getId(){
    return id;
  }
}