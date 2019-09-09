import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.Font;
public class Register{
  private JFrame frame;
  private JButton submit;
  private JTextField username;
  private JPasswordField confirm_password;
  private JLabel label1,label2,label3;
  private JPasswordField password;
  private Handler handler;
  private JPanel p;
  private Font f;
  private gameState gState;
  public Register (Handler handler){
    this.handler = handler;
    frame = new JFrame("Register");
    p = new JPanel();    
    username = new JTextField("",20);
    label1 = new JLabel("Username");
    password = new JPasswordField();
    label2 = new JLabel("Password");
    confirm_password = new JPasswordField();
    label3 = new JLabel("Confirm Password");
    submit = new JButton("Register");    
    f = new Font("papyrus", Font.BOLD, 18);
    
    p.setLayout(null);

    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(!handler.getDatabase().isUsernameTaken(username.getText()) == true && password.getText().equals(confirm_password.getText()) == true && password.getText().length() >= 6 && username.getText().length() >= 4){
          if(handler.getDatabase().insert(username.getText(), password.getText()) == true){
            gState = (gameState)handler.getGame().gState;
            gState.loadLevel(0,"demo.txt", 60,1);
            State.setState(handler.getGame().gState);
            handler.getGame().gState.setChanged();
            handler.setUsername(username.getText());
            handler.setNotification("Press Arrow Keys to Move! Go and pick up the key!",10);
            frame.dispose();
          }else{
            //System.out.println("Unable to register!");
            JOptionPane.showMessageDialog(null, "Unable to register!", "Registration Error", JOptionPane.INFORMATION_MESSAGE);
          }
        }
        else{
          if(handler.getDatabase().isUsernameTaken(username.getText()) == true){
            //System.out.println("The username is already taken, please try another!");
            JOptionPane.showMessageDialog(null, "The username is already taken, please try another!", "Registration Error", JOptionPane.INFORMATION_MESSAGE);
          }
          if(!password.getText().equals(confirm_password.getText()) == true){
            //System.out.println("The password should match");
            JOptionPane.showMessageDialog(null, "The password should match", "Registration Error", JOptionPane.INFORMATION_MESSAGE);
          }else if(password.getText().length() < 6){
            //System.out.println("The password should be atleast 6 characters long");
            JOptionPane.showMessageDialog(null, "The password should be atleast 6 characters long", "Registration Error", JOptionPane.INFORMATION_MESSAGE);
          }
          if(username.getText().length() < 4){
            //System.out.println("The username should be atleast 4 characters long");
            JOptionPane.showMessageDialog(null, "The username should be atleast 4 characters long", "Registration Error", JOptionPane.INFORMATION_MESSAGE);
          }
        }
        //System.out.println(username.getText());
        //System.out.println(password.getPassword());
        //frame.setVisible(false);
      }
    });
    
  }
  public void init(){
    
    
    
    
    label1.setBounds(20,20,200,20);
    username.setBounds(20,50,200,40);
    label2.setBounds(20,120,200,20);
    password.setBounds(20,150,200,40);
    label3.setBounds(20,220,200,20);
    confirm_password.setBounds(20,250,200,40);
    submit.setBounds(20,320,200,30);

    label1.setFont(f);
    username.setFont(f);
    label2.setFont(f);
    password.setFont(f);
    label3.setFont(f);
    confirm_password.setFont(f);
    submit.setFont(f);

    frame.setLocationRelativeTo(null);    
    frame.setSize(240,500);
    frame.setVisible(true);
    frame.setResizable(false);
    
    p.add(label1);
    p.add(username);
    p.add(label2);
    p.add(password);
    p.add(submit);
    p.add(confirm_password);
    p.add(label3);
    
    frame.add(p);
  }
  
  
  
}
