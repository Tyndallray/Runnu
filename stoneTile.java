public class stoneTile extends Tile{

  public stoneTile(int id){
    super(Assets.stoneTile,id);
  }

  public boolean isSolid(){
    return true;
  }
}