/*

creates a Game object

*/

import java.awt.Dimension;
import java.awt.Toolkit;

public class gameLaunch {
    public static void main(String[] args){
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      game myGame = new game("Runnu", (int)screenSize.getWidth(), (int)screenSize.getHeight());
      myGame.start();
    }
}