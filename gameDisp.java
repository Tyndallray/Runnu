import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;


/*



constructor: sets title, width and height
              calls createDisplay()
  
  
createDisplay() : creates JFrame and Canvas



*/



public class gameDisp {
    
  private JFrame frame;
  private String title;
  private int width, height;
  private Canvas canvas;
  
  public gameDisp(String title, int width, int height){
    this.title = title;
    this.width = width;
    this.height= height;

	createDisplay();
  }
  
  public void createDisplay(){
    frame = new JFrame(title);
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    canvas = new Canvas();
    canvas.setPreferredSize(new Dimension(width, height)); // Dimension jimish = new Dimenstion(100,200);
    canvas.setMaximumSize(new Dimension(width, height));
    canvas.setMinimumSize(new Dimension(width, height));
    canvas.setFocusable(false);
    
    frame.add(canvas);
    frame.pack();
  }
  
  public Canvas getCanvas(){
    return canvas;
  }

  public JFrame getFrame(){
    return frame;
  }

}