package presentation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import Entities.Course;
import domain.SaveFileManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCourseWindow {

	private JFrame frame;
	private JTextField codebox;
	private JTextField namebox;
	private JTextField prereqbox;
	private JTextField desbox;
	private JTextField creditbox;
	private JLabel lblEditCourse;
	private JLabel label;
	private String courseName;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public void OpenDialog(String courseName) {
		try {
			EditCourseWindow window = new EditCourseWindow(courseName);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public EditCourseWindow(String courseName) {
		this.courseName = courseName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SaveFileManager sm = new SaveFileManager();
		sm.Load();
		Course c = sm.getStore().getCourseByName(courseName);
		frame = new JFrame();
		frame.setFont(new Font("Calibri", Font.PLAIN, 50));
		frame.setResizable(false);
		frame.setBounds(100, 100, 383, 382);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JLabel close = new JLabel("\u2716");
		close.setForeground(Color.WHITE);
		close.setVerticalAlignment(SwingConstants.TOP);
		close.setHorizontalAlignment(SwingConstants.CENTER);
		close.setFont(new Font("Dialog", Font.PLAIN, 25));
		close.setBounds(318, -1, 64, 37);
		close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close.setToolTipText("Close");
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}

			public void mouseEntered(MouseEvent e) {
				close.setForeground(Color.yellow);
			}

			public void mouseExited(MouseEvent e) {
				close.setForeground(Color.white);
			}
		});
		frame.getContentPane().add(close);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Course> cources = sm.getStore().Courses;

				c.setCode(codebox.getText());
				c.setName(namebox.getText());
				c.setCredits(Integer.parseInt(creditbox.getText()));
				c.setDescription(desbox.getText());
				c.setPreRequisite(prereqbox.getText());
				for (int i = 0; i < cources.size(); i++)
					if (cources.get(i).getCode().equals(c.getCode())){
						sm.getStore().Courses.set(i, c);
						Course cc = sm.getStore().Courses.get(i);
					}
				sm.SaveChanges();
				frame.dispose();
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSave.setFocusPainted(false);
		btnSave.setBackground(new Color(59, 89, 182));
		btnSave.setBounds(113, 304, 143, 32);
		frame.getContentPane().add(btnSave);

		JLabel lblNewLabel = new JLabel("Code");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(27, 68, 64, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(27, 111, 64, 25);
		frame.getContentPane().add(lblName);

		JLabel lblPreRequisite = new JLabel("Pre Requisite");
		lblPreRequisite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPreRequisite.setBounds(25, 151, 93, 25);
		frame.getContentPane().add(lblPreRequisite);

		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCredit.setBounds(25, 198, 93, 25);
		frame.getContentPane().add(lblCredit);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(27, 239, 93, 25);
		frame.getContentPane().add(lblDescription);

		codebox = new JTextField();
		codebox.setBounds(113, 68, 227, 25);
		codebox.setText(c.getCode());
		frame.getContentPane().add(codebox);
		codebox.setColumns(10);

		namebox = new JTextField();
		namebox.setColumns(10);
		namebox.setBounds(113, 113, 227, 25);
		namebox.setText(c.getName());
		frame.getContentPane().add(namebox);

		prereqbox = new JTextField();
		prereqbox.setColumns(10);
		prereqbox.setBounds(113, 154, 227, 25);
		prereqbox.setText(c.getPreRequisite());
		frame.getContentPane().add(prereqbox);

		desbox = new JTextField();
		desbox.setColumns(10);
		desbox.setBounds(113, 239, 227, 25);
		desbox.setText(c.getDescription());
		frame.getContentPane().add(desbox);

		creditbox = new JTextField();
		creditbox.setColumns(10);
		creditbox.setBounds(113, 198, 227, 25);
		creditbox.setText(String.valueOf(c.getCredits()));
		frame.getContentPane().add(creditbox);

		lblEditCourse = new JLabel("Edit Course");
		lblEditCourse.setForeground(Color.WHITE);
		lblEditCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditCourse.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEditCourse.setBounds(27, -1, 313, 32);
		frame.getContentPane().add(lblEditCourse);

		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setOpaque(true);
		label.setBackground(new Color(59, 89, 182));
		label.setBounds(0, -1, 382, 37);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBackground(new Color(59, 89, 182));
		label_1.setBounds(0, 348, 382, 34);
		frame.getContentPane().add(label_1);


	}

}
