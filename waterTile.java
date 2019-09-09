public class waterTile extends Tile{
    
  public waterTile(int id){
    super(Assets.waterTile,id);
  }
  public boolean isSolid(){
    return true;
  }
  
}