import java.sql.*;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.awt.Graphics;
import java.io.FileWriter;

public class Utility{
  
  public static String loadFileAsString(String path){
    StringBuilder builder = new StringBuilder();
    try{
      BufferedReader br= new BufferedReader(new FileReader(path));
      String line;
      while((line = br.readLine()) != null)
        builder.append(line + "\n");
      br.close();
    }catch(IOException e){
      e.printStackTrace();
    }
    return builder.toString();
  }
  
  public static int parseInt(String number){
    try{
      return Integer.parseInt(number);
    }catch(NumberFormatException e){
      e.printStackTrace();
      return 0;
    }
  }
  
  
  
  public static void parseString(Graphics g, Handler handler){
      try{
        int i=0;
        FileInputStream fstream = new FileInputStream("score.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
      
        String strLine;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
          // split string and call your function
          //System.out.println(strLine);
          
          int result = Integer.parseInt(strLine);
                  
          g.drawString(handler.getDatabase().getUsername(result) ,handler.getGame().getWidth()/2 - 350, 200 + (50*i));
          strLine = br.readLine();
          g.drawString(strLine ,handler.getGame().getWidth()/2 - 150, 200 + (50*i));
          strLine = br.readLine();
          g.drawString(strLine,handler.getGame().getWidth()/2 + 50, 200 + (50*i));
          strLine = br.readLine();
          g.drawString(strLine,handler.getGame().getWidth()/2 + 250, 200 + (50*i));
          i++;
        
        }
      }catch(IOException e){
        e.printStackTrace();
      }catch(Exception e){
        e.printStackTrace();
      }
  }
  
  public static void writeFile(ResultSet rs){
    String FILENAME = "score.txt";
    BufferedWriter bw = null;
    FileWriter fw = null;
      try {
        String content = "";
        try{
          int i=0;
          while(rs.next() && i < 5){
            if(rs != null){
              content = content + ""+ rs.getInt("Player_Id") + "\n" + rs.getInt("Score") +"\n" + rs.getInt("Time") + "\n" + rs.getInt("Level") + "\n";
            }
            i++;
          }
          for(;i<5;i++){
            content = content + "-\n-\n-\n-\n";
          }
        }catch(SQLException e){
          e.printStackTrace();
        }
        fw = new FileWriter(FILENAME);
        bw = new BufferedWriter(fw);
        bw.write(content);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          if (bw != null)
            bw.close();
          if (fw != null)
            fw.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    //parseString();
    
  }
}