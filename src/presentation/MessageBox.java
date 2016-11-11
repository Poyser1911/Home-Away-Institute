package presentation;

import javax.swing.JOptionPane;

public class MessageBox {
	
	public static void Show(String text){
		
		JOptionPane.showMessageDialog(null,text, "Home & Away Institute - Info", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void Error(String text){
		
		JOptionPane.showMessageDialog(null,text, "Home & Away Institute - Error", JOptionPane.ERROR_MESSAGE);
	}

}
