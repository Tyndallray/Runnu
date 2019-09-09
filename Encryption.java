import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
public class Encryption{
  
  public Encryption(){
    
  }
  
  public String toMD5(String text){

    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(text.getBytes());  //  16 bytes
      BigInteger number = new BigInteger(1, messageDigest); // 38-40 digits long
      String hashtext = number.toString(16);  // 32 bytes
      return hashtext;
    }
    catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return "";  
  }
}