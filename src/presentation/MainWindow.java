package presentation;

import java.awt.EventQueue;
import domain.SaveFileManager;
import domain.Util;
import domain.WindowBase;
import domain.Util.UserType;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class MainWindow extends WindowBase {

	private JTextField idbox;
	private JPasswordField passbox;
	private JComboBox<String> logintype;
	private JButton login;
	private SaveFileManager sm;
	
	private JCheckBox autoLogin;
	private JCheckBox rememberId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public MainWindow() {
		initialize();
		
	}

	private void initialize() {
		super.setHeaderlabel("User Login");
		btnLogout.setVisible(false);
		sm = new SaveFileManager();
		sm.Load();

		idbox = new JTextField();
		idbox.setToolTipText("Enter Issused ID");
		idbox.setHorizontalAlignment(SwingConstants.CENTER);
		idbox.setFont(new Font("Dialog", Font.PLAIN, 15));
		idbox.setBounds(174, 163, 326, 32);
		frame.getContentPane().add(idbox);
		idbox.setColumns(10);
		idbox.setText("2016NET10");

		logintype = new JComboBox<String>();
		logintype.setToolTipText("Please Your User Account Type");
		logintype.setFont(new Font("Dialog", Font.PLAIN, 15));
		logintype.setBounds(221, 313, 243, 32);
		logintype.addItem("[Select Login Type]");
		logintype.addItem("Staff");
		logintype.addItem("Student");
		logintype.addItem("Adminstrator");
		frame.getContentPane().add(logintype);

		JLabel idlabel = new JLabel("ID Number");
		idlabel.setHorizontalAlignment(SwingConstants.CENTER);
		idlabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		idlabel.setBounds(174, 114, 326, 37);
		frame.getContentPane().add(idlabel);

		passbox = new JPasswordField();
		passbox.setToolTipText("Enter Issused Password");
		passbox.setHorizontalAlignment(SwingConstants.CENTER);
		passbox.setFont(new Font("Dialog", Font.PLAIN, 15));
		passbox.setBounds(174, 234, 326, 32);
		frame.getContentPane().add(passbox);

		JLabel passlabel = new JLabel("Password");
		passlabel.setHorizontalAlignment(SwingConstants.CENTER);
		passlabel.setVerticalAlignment(SwingConstants.TOP);
		passlabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		passlabel.setBounds(174, 204, 326, 32);
		frame.getContentPane().add(passlabel);

		login = new JButton("Login");
		login.setToolTipText("Click to Login!");
		login.addActionListener(loginClicked());
		login.setBackground(new Color(59, 89, 182));
		login.setForeground(Color.WHITE);
		login.setFocusPainted(false);
		login.setFont(new Font("Tahoma", Font.BOLD, 12));
		login.setBounds(263, 410, 158, 37);
		frame.getContentPane().add(login);

		JLabel lblIdentity = new JLabel("Identity");
		lblIdentity.setVerticalAlignment(SwingConstants.TOP);
		lblIdentity.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentity.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblIdentity.setBounds(174, 277, 326, 32);
		frame.getContentPane().add(lblIdentity);
		
		autoLogin = new JCheckBox("Auto Login");
		autoLogin.setBackground(Color.WHITE);
		autoLogin.setBounds(562, 473, 100, 23);
		frame.getContentPane().add(autoLogin);
		
		rememberId = new JCheckBox("Remember ID");
		rememberId.setBackground(Color.WHITE);
		rememberId.setBounds(450, 473, 110, 23);
		frame.getContentPane().add(rememberId);
		
	}
	private ActionListener loginClicked(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String id = idbox.getText();
				String password = Util.getPassword(passbox.getPassword());
				UserType user = sm.getStore().Login(id, password, logintype.getSelectedIndex());
				
				if(user != UserType.None)
					MessageBox.Show("Login Sucessful");
					else{
						MessageBox.Error("Login Unsucessful");
						return;
					}
				
				switch(user){
		        case Staff:
		        	StaffWindow.main(null);
		            break;
		        case Student:
		        	StudentWindow.main(id);
		            break;
		        case Adminstrator:
		        	AdminWindow.main(null);
		            break;
				default:
					break;
				}
	        	frame.dispose();
			}
		};
	}
	
}
