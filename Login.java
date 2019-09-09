import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.Font;
public class Login{
  private JFrame frame;
  private JButton submit;
  private JTextField username;
  private JLabel label1,label2;
  private JPasswordField password;
  private Handler handler;
  private JPanel p;
  private Font f;
  public Login(Handler handler){
    this.handler = handler;
    frame = new JFrame("Login");
    
    p = new JPanel();
    p.setLayout(null);
    
    f = new Font("papyrus", Font.BOLD, 18);
    
    username = new JTextField("",20);
    label1 = new JLabel("Username");
    password = new JPasswordField();
    label2 = new JLabel("Password");
    submit = new JButton("Login");
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(handler.getDatabase().isUsernameTaken(username.getText()) == true){
          if(handler.getDatabase().isPasswordValid(username.getText(), password.getText()) == true){
            State.setState(handler.getGame().mState);
            handler.getGame().mState.setChanged();
            handler.setUsername(username.getText());
            frame.dispose();
          }
          else{
            
            JOptionPane.showMessageDialog(null, "Wrong Password! please try again!", "InfoBox: Error!", JOptionPane.INFORMATION_MESSAGE);
          }
        }
        else{
          
          JOptionPane.showMessageDialog(null, "Invalid Username", "InfoBox: Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        //frame.setVisible(false);
      }
    });
  }
  
  public void init(){
    
    
    label1.setBounds(20,20,200,20);
    username.setBounds(20,50,200,40);
    label2.setBounds(20,120,200,20);
    password.setBounds(20,150,200,40);
    submit.setBounds(20,220,200,30);

    label1.setFont(f);
    username.setFont(f);
    label2.setFont(f);
    password.setFont(f);
    submit.setFont(f);
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the Runnu
    frame.setLocationRelativeTo(null);    
    frame.setSize(240,290);
    frame.setVisible(true);
    frame.setResizable(false);
    
    p.add(label1);
    p.add(username);
    p.add(label2);
    p.add(password);
    p.add(submit);
    
    frame.add(p);
  }
  
 
  
}
