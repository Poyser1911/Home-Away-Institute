package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import presentation.MessageBox;

public class Util {
	
	public enum UserType{
		
		None,
		Staff,
		Student,
		Adminstrator
	}
	
	public static  String getPassword(char[] password){
		//String resultt = new String(password); ._.
		StringBuilder result = new StringBuilder();
		for(char c : password)
			result.append(c);
		return result.toString();
	}
	
	public static boolean isValid(String input,String errorMsg){
		if(!input.trim().matches("^\\w+$")){
			MessageBox.Error(errorMsg+"\n-Only (alphanumeric characters) allowed.\n-Must Not contain any significant (Whitespaces) or (Symbols).");
			return false;
		}
		return true;
	}
	public static boolean isValidPassword(String pass){
		if(!pass.trim().matches("^(\\S+){6}$")){
			MessageBox.Error("Please Enter a Password\n-More than six(6) characters\n-Only (alphanumeric characters) and (Symbols) allowed.\n-Must Not contain any significant (Whitespaces)");
			return false;
		}
		return true;
	}
	public static boolean isValidName(String name){
		if(!name.trim().matches("^[A-Za-z-']+\\s+[A-Za-z-']+$")){
			MessageBox.Show("Please Enter a valid name format.\n-(FirstName and LastName): eg: (John Doe)\n-(-) and (') characters allowed");
			return false;
		}
		else
			return true;
	}
	
	
	public static boolean isValidDate(String date){
		if(!date.matches("^(\\d{1,2}\\/){2}(\\d{2}|\\d{4})$")){
			MessageBox.Show("Please Enter a valid date format: dd/mm/yy");
			return false;
		}
		else
			return true;
	}
	
	public static boolean isValidContact(String contact){
		if(!contact.matches("^(\\d{3,4}|\\d\\-\\d{3}|)\\-?\\d{3}\\-?\\d{4}$")){
			MessageBox.Show("Please Enter a valid date format: dd/mm/yy");
			return false;
		}
		else
			return true;
	}
	public static boolean isValidAddress(String addr){
		if(!addr.matches("^\\d+ \\w+\\s\\w+\\s?(,\\s?(\\w+\\s?){1,5})?(,\\s?(\\w+\\s?){1}\\d{1,2})?$")){
			MessageBox.Show("Please Enter a address format: <street number> <street name>, <city>, <zone>\n-Required: <street number>, <street name>\n-Optional: <city>, <zone>");
		return false;
	}
	else
		return true;
	}
	
	public static boolean isValidAcc(String input){
		if(!input.trim().matches("^[0-4]$"))
			return false;
		return true;
	}
	  public static String hashPassword(String password) {
	        MessageDigest md;
	        StringBuffer sb = new StringBuffer();;
			try {
				md = MessageDigest.getInstance("SHA");
	        md.update(password.getBytes());
	        byte[] b = md.digest();
	        for(byte b1 : b)
	            sb.append(Integer.toHexString(b1 & 0xff).toString());
			} catch (NoSuchAlgorithmException e) {
				MessageBox.Error("Error while encrypting password.\nDetails: "+e.getMessage());
				return password;
			}
			 return sb.toString();
	    }
	  
		public static boolean isAdmin(String id,String password){
			
			return (id.equals("1337") & password.equals("1337")) ? true :false;
		}
		
		public static JTextFieldLimit setTextLimit(int maxlen){
			return new JTextFieldLimit(maxlen);
		}
		
		public static class JTextFieldLimit extends PlainDocument {

			private static final long serialVersionUID = 1L;
			private int limit;
			  public JTextFieldLimit(int limit) {
			    super();
			    this.limit = limit;
			  }

			  JTextFieldLimit(int limit, boolean upper) {
			    super();
			    this.limit = limit;
			  }

			  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			    if (str == null)
			      return;

			    if ((getLength() + str.length()) <= limit) {
			      super.insertString(offset, str, attr);
			    }
			  }
			}

}
