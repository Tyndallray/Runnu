public class Handler{
  private game Game;
  private Level level;
  private String username;
  private int medals;

  public Handler(game Game){
    this.Game = Game;
  }
  
  public Database getDatabase(){
    return Game.getDatabase();
  }
  public void updateHighscore(int count, long invyScore){
    long total = count;
    total *= getLevel().getTimer().getTimeLeft();
    total += getLevel().getTimer().getTimeLeft() * 10;
    total += invyScore;
    System.out.println("total : "+ total);
    //getDatabase().update(total,username);
    if(getDatabase().getCurrentScore(getLevel().getLevelIndex()) < total){
      updateHighscore(total);
    }
  }
  public void updateHighscore(){
    int count = getLevel().getEntityManager().getPlayer().getInventory().getCount(4);
    medals = getLevel().getEntityManager().getPlayer().getInventory().getMedals();
    long invyScore = getLevel().getEntityManager().getPlayer().getInventory().getScore();
    long total = count;
    total *= getLevel().getTimer().getTimeLeft();
    total += getLevel().getTimer().getTimeLeft() * 10;
    total += invyScore;
    System.out.println("total : "+ total);
    //getDatabase().update(total,username);
    if(getDatabase().getCurrentScore(getLevel().getLevelIndex()) < total){
      updateHighscore(total);
    }
  }
  private void updateHighscore(long score){
    getDatabase().updateHighscore(score,getLevel().getTimer().getTimeEllapsed(), getLevel().getLevelIndex(),medals,username);  
  }
  
  public game getGame(){
    return Game;
  }
  public GameCamera getCamera(){
    return Game.getCamera();
  }
  public keyManager getKeyManager(){
    return Game.getKeyManager();
  }
  public MouseManager getMouseManager(){
    return Game.getMouseManager();
  }
  public Level getLevel(){
    return level;
  }
  public void setGame(game Game){
    this.Game = Game;
  }
  public void setLevel(Level level){
    this.level = level;
  }
  public int getWidth(){
    return Game.width;
  }
  public int getHeight(){
    return Game.height;
  }
  public Notification getNotification(){
    return Game.getNotification();
  }
  public void setNotification(String message){
    Game.setNotification(message);
  }
  public void setNotification(String message, int time){
    Game.setNotification(message, time);
  }
  public long getScore(){
    return level.getTimer().getScore();
  }
  public void setUsername(String username){
    this.username = username;
  }
  
  public String getUsername(){
    return username;
  }
  public void setMedals(int medals){
    this.medals = medals;
  }
}