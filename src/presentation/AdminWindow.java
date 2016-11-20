package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import Entities.Staff;
import domain.SaveFileManager;
import domain.Util;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;

public class AdminWindow extends WindowBase{

	
	private JTextField depbox;
	private JPasswordField passbox;
	private JTextField facbox;
	private JTextField namebox;
	private JTextField idbox;
	private JTextField datebox;
	private JTable table;
	
	private JButton addStaff;
	private SaveFileManager sm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminWindow() {
		initialize();
	}

	private void initialize() {
		super.setHeaderlabel("Adminstrator");
		sm = new SaveFileManager();
		sm.Load();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBounds(12, 109, 675, 403);
		tabbedPane.setBackground(Color.white);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		tabbedPane.addTab("Create Staff Account", null, panel, null);
		tabbedPane.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID Number");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(93, 30, 122, 44);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPassword.setBounds(93, 73, 122, 44);
		panel.add(lblPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblName.setBounds(93, 119, 122, 44);
		panel.add(lblName);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFaculty.setBounds(93, 162, 122, 44);
		panel.add(lblFaculty);
		
		JLabel lblDateEmployed = new JLabel("Date Employed");
		lblDateEmployed.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDateEmployed.setBounds(93, 254, 122, 44);
		panel.add(lblDateEmployed);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDepartment.setBounds(93, 211, 122, 44);
		panel.add(lblDepartment);
		
		depbox = new JTextField();
		depbox.setColumns(10);
		depbox.setBounds(211, 209, 311, 30);
		panel.add(depbox);
		
		passbox = new JPasswordField();
		passbox.setColumns(10);
		passbox.setBounds(211, 83, 311, 30);
		panel.add(passbox);
		
		facbox = new JTextField();
		facbox.setColumns(10);
		facbox.setBounds(211, 168, 311, 30);
		panel.add(facbox);
		
		namebox = new JTextField();
		namebox.setColumns(10);
		namebox.setBounds(211, 122, 311, 30);
		panel.add(namebox);
		
		idbox = new JTextField();
		idbox.setColumns(10);
		idbox.setBounds(211, 37, 311, 30);
		idbox.setDocument(Util.setTextLimit(10));
		panel.add(idbox);
		
		
		datebox = new JTextField();
		datebox.setColumns(10);
		datebox.setBounds(211, 264, 311, 30);
		panel.add(datebox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Manage Staff Accounts", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(0, 0, 670, 375);
		panel_1.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(sm.getStore().buildStaffTable());

		scrollPane.setViewportView(table);
		table.setBackground(Color.white);
	
		
		addStaff = new Button("Add");
		addStaff.addActionListener(addStaffClicked());
		addStaff.setBackground(borderColor);
		addStaff.setForeground(Color.WHITE);
		addStaff.setFocusPainted(false);
		addStaff.setFont(new Font("Tahoma", Font.BOLD, 12));
		addStaff.setBounds(206, 327, 239, 37);
		panel.add(addStaff);
		
		
	}
	
	private ActionListener addStaffClicked(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = Util.getPassword(passbox.getPassword());
				
				if(!Util.isValid(idbox.getText(),"Please Enter a Valid ID Number")){
					idbox.grabFocus();
					idbox.selectAll();
					return;
				}
				
				if(!Util.isValidPassword(password)){
					passbox.grabFocus();
					passbox.selectAll();
					return;
				}
				
				if(!Util.isValidName(namebox.getText())){
					namebox.grabFocus();
					namebox.selectAll();
					return;
				}
				
				namebox.setText(namebox.getText().replaceAll("\\s+", " "));
				if(!Util.isValid(facbox.getText(),"Please Enter a Faculty")){
					facbox.grabFocus();
					facbox.selectAll();
					return;
				}
				
				
				if(!Util.isValid(depbox.getText(),"Please Enter a Department")){
					depbox.grabFocus();
					depbox.selectAll();
					return;
				}
				if(!Util.isValidDate(datebox.getText())){
					datebox.grabFocus();
					datebox.selectAll();
					return;
				}
				
				sm.addStaff(new Staff(idbox.getText(), password, depbox.getText(), namebox.getText(), datebox.getText(), facbox.getText()));
				table.setModel(sm.getStore().buildStaffTable());
				
				MessageBox.Show("New Staff ~"+namebox.getText()+"@"+idbox.getText()+" Added Sucessfully");
			}
		};
	}
	
}


