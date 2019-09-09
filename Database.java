import java.sql.*;

public class Database{

  private Connection con;
  private Encryption encryption;
  private Statement st;
  private String query;
  private ResultSet rs;
  private int i=0;
  private int id;
  private Handler handler;
  public Database(Handler handler){
    this.handler=  handler;
    encryption = new Encryption();
    try{
      Class.forName("com.mysql.jdbc.Driver");
       
      con=DriverManager.getConnection("jdbc:mysql://localhost/runnu","root","");

      st = con.createStatement();
    }catch(Exception ex){
      System.out.println("Error :  "+ex);
    }
    setI();
  }

  public void insert(long score, String username){
    try{
      query = "INSERT INTO highscore (`Player_Id`, `Score`, `Time`, `Level`) VALUES ('"+ getPlayerId(username) +"', '"+score+"', '7', '1')";
      st.executeUpdate(query);
    }catch(Exception ex){
      System.out.println("Error insert " + ex);
    }
  }
  public boolean insert(String name, String password){
    try{
      query = "INSERT INTO player_information (`Player_Id`, `User_Name`, `Password`) VALUES ('"+ (++i) +"', '"+name+"', '"+encryption.toMD5(password)+"')";
      st.executeUpdate(query);
      query = "INSERT INTO highscore (`Player_Id`, `Score`, `Time`, `Level`, `Medal`) VALUES ('"+ (i) +"' , '0', '0', '1', '0')";
      st.executeUpdate(query);
      query = "INSERT INTO highscore (`Player_Id`, `Score`, `Time`, `Level`, `Medal`) VALUES ('"+ (i) +"' , '0', '0', '2', '0')";
      st.executeUpdate(query);
      query = "INSERT INTO highscore (`Player_Id`, `Score`, `Time`, `Level`, `Medal`) VALUES ('"+ (i) +"' , '0', '0', '3', '0')";
      st.executeUpdate(query);
      id = i;
      
      return true;
    }catch(Exception ex){
      System.out.println("Error insert " + ex);
      return false;
    }
  }
  
  public void setI(){
    try{
      String query = "SELECT * FROM `player_information`";
      rs = st.executeQuery(query);
      while(rs.next()){
        i = rs.getInt("Player_Id");
      }
    }catch(SQLException ex){
        System.out.println(ex);
    }
  }
  
  public void update(long score, String username){
    int count = 0;
    
    System.out.println("scores updated");
    try {
      query = "UPDATE highscore SET Score ="+score+" where Player_Id =" + getPlayerId(username);
      System.out.println("Done");
      count = st.executeUpdate(query);
      System.out.println("Updated queries: "+count);
	}catch (SQLException e){
      e.printStackTrace();
    }
  }
  public void updateHighscore(long score,int timeEllapsed, int levelIndex, int medal, String username){
    int count = 0;
    
    System.out.println("scores updated");
    try {
      String query = "UPDATE highscore SET Score ="+score+", Time ="+ timeEllapsed+", Medal ="+medal+" where Level =" + levelIndex +" AND Player_Id ="+ getPlayerId(username);
      count = st.executeUpdate(query);
	}catch (SQLException e){
      e.printStackTrace();
    }
  }
  
  public void select(){
    try{
      String query = "SELECT * FROM `player_information`";
      rs = st.executeQuery(query);
      System.out.println("Records Displayed");
      while(rs.next()){
        String name=rs.getString("User_Name");
        
        int levelflag = rs.getInt("Level_flag");
        
      }
    }catch(SQLException ex){
        System.out.println(ex);
    }
  }
  
  
  public boolean isUsernameTaken(String name){
    try{  
      String query = "SELECT * FROM `player_information`";
      rs = st.executeQuery(query);
      while(rs.next()){
        if(name.toUpperCase().equals(rs.getString("User_Name").toUpperCase())){
          return true;
        }
      }
    }catch(SQLException ex){
      System.out.println(ex);
      return true;
    }
    return false;
  }
  
  public boolean isPasswordValid(String username, String password){
    try{  
      String query = "SELECT * FROM `player_information`";
      rs = st.executeQuery(query);
      while(rs.next()){
        if(username.toUpperCase().equals(rs.getString("User_Name").toUpperCase())){
          if(encryption.toMD5(password).equals(rs.getString("Password"))){
            id = i;
            return true;
          }
        }
      }
    }catch(SQLException ex){
      System.out.println(ex);
      return true;
    }
    return false;
  }
  
  public int getPlayerId(String username){
    try{  
      String query = "SELECT * FROM `player_information`";
      rs = st.executeQuery(query);
      while(rs.next()){
        if(username.toUpperCase().equals(rs.getString("User_Name").toUpperCase())){
          return rs.getInt("Player_Id");
        }
      }
    }catch(SQLException ex){
      System.out.println(ex);
    }
    return 0;
  }
  
  public String getUsername(int id){
    try{  
      String query = "SELECT * FROM `player_information`";
      rs = st.executeQuery(query);
      while(rs.next()){
        if(rs.getInt("Player_Id") == id){
          return rs.getString("User_Name");
        }
      }
    }catch(SQLException ex){
      System.out.println(ex);
    }
    return "0";
  }
  
  public long getCurrentScore(int levelIndex){
    try{  
      String query = "SELECT * FROM `highscore`";
      rs = st.executeQuery(query);
      while(rs.next()){
        if(rs.getInt("Player_Id") == id && rs.getInt("Level") == levelIndex){
          return rs.getInt("Score");
        }
      }
    }catch(SQLException ex){
      System.out.println(ex);
    }
    return 0;
  }
  
  public boolean hasMedal(int levelIndex){
    try{  
      String query = "SELECT * FROM `highscore`";
      rs = st.executeQuery(query);
      while(rs.next()){
        if(rs.getInt("Player_Id") == id && rs.getInt("Level") == levelIndex){
          if(rs.getInt("Medal") > 0){
            return true;
          }
        }
      }
    }catch(SQLException ex){
      System.out.println(ex);
    }
    return false;
  }
  //fetching from database ----------> highscore <------------
  public ResultSet getResultHighscore(int levelIndex){
    try{
      String sql = "SELECT Player_Id, Score, Time, Level FROM Highscore where Level="+levelIndex+" ORDER BY Score DESC";
      ResultSet rs = st.executeQuery(sql);
      Utility.writeFile(rs);
      return rs;
    }catch(SQLException ex){
      System.out.println(ex);
    }
    return null;
  }
  
  public int getId(){
    return id;
  }
  
  
}

