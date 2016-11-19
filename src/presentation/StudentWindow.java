package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import Entities.Course;
import Entities.Programme;
import Entities.Student;
import domain.SaveFileManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;


public class StudentWindow extends WindowBase{

	private JTable coursetable;
	private String studentId;
	
	JList<String> courselist;
	JLabel lblTotalCredits;
	JLabel lblTotalCost;
	JLabel lblNoOfCourses;
	JButton btnEnroll;
	
	private SaveFileManager sm;
	private Student student;
	private Programme programme;
	private JTable progresstable;

	public static void main(String studentId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentWindow window = new StudentWindow(studentId);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentWindow(String studentId) {
		this.studentId = studentId;
		initialize();
	}

	private void initialize() {
		sm = new SaveFileManager();
		sm.Load();
		student = sm.getStore().getStudentById(studentId);
		programme = sm.getStore().getProgrammeByCode(student.getProgramCode());
		
		setHeaderlabel("Hello "+student.getName().split("\\s+?")[0]);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBounds(12, 109, 675, 403);
		tabbedPane.setBackground(Color.white);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		tabbedPane.addTab("View Programme Details", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblYourCources = new JLabel("Your Courses");
		lblYourCources.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourCources.setForeground(Color.WHITE);
		lblYourCources.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblYourCources.setBounds(196, 0, 474, 38);
		panel.add(lblYourCources);
		
		JLabel proname = new JLabel();
		proname.setForeground(Color.WHITE);
		proname.setHorizontalAlignment(SwingConstants.LEFT);
		proname.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		proname.setBounds(1, 0, 288, 38);
		panel.add(proname);
		proname.setText("Cources Avaliable in: "+programme.getName()+" Programme");
		
		JLabel lblFeeBreakdown = new JLabel("Fee Breakdown for Semester");
		lblFeeBreakdown.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeeBreakdown.setForeground(Color.WHITE);
		lblFeeBreakdown.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFeeBreakdown.setBounds(1, 225, 669, 26);
		panel.add(lblFeeBreakdown);
		
		JLabel lblClickToEnroll = new JLabel("Click to Enroll!");
		lblClickToEnroll.setForeground(Color.BLACK);
		lblClickToEnroll.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickToEnroll.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblClickToEnroll.setBounds(517, 261, 153, 32);
		panel.add(lblClickToEnroll);
		
		getEnrolled();
		
		btnEnroll.addActionListener(enrolledClicked());
		btnEnroll.setForeground(Color.WHITE);
		btnEnroll.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEnroll.setFocusPainted(false);
		btnEnroll.setBounds(529, 304, 121, 49);
		panel.add(btnEnroll);
		
		lblTotalCredits = new JLabel("Total Credits: ");
		lblTotalCredits.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCredits.setForeground(Color.BLACK);
		lblTotalCredits.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotalCredits.setBounds(11, 262, 153, 32);
		panel.add(lblTotalCredits);
		
		lblTotalCost = new JLabel("Total Cost: ");
		lblTotalCost.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCost.setForeground(Color.BLACK);
		lblTotalCost.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotalCost.setBounds(11, 293, 153, 32);
		panel.add(lblTotalCost);
		
		lblNoOfCourses = new JLabel("No. of Courses: ");
		lblNoOfCourses.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoOfCourses.setForeground(Color.BLACK);
		lblNoOfCourses.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNoOfCourses.setBounds(11, 322, 153, 32);
		panel.add(lblNoOfCourses);
		
		courselist = new JList<String>();
		courselist.setBackground(Color.WHITE);
		courselist.setBounds(1, 38, 178, 187);
		courselist.setModel(sm.getStore().getUnaddedCoursesForStudent(student));
		courselist.addMouseListener(addCourseClicked());
		
		panel.add(courselist);
		
		JLabel label_4 = new JLabel("");
		label_4.setOpaque(true);
		label_4.setBackground(new Color(59, 89, 182));
		label_4.setBounds(0, 0, 670, 37);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setOpaque(true);
		label_5.setBackground(new Color(59, 89, 182));
		label_5.setBounds(1, 225, 670, 26);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setOpaque(true);
		label_6.setBackground(new Color(59, 89, 182));
		label_6.setBounds(180, 37, 14, 188);
		panel.add(label_6);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 38, 474, 187);
		panel.add(scrollPane);
		
		
		coursetable = new JTable();
		scrollPane.setViewportView(coursetable);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Progress Report", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 670, 375);
		panel_1.add(scrollPane_1);
		
		progresstable = new JTable();
		scrollPane_1.setViewportView(progresstable);
		coursetable.addMouseListener(removeCourseClicked());
		
		refreshStats();
	}

	private ActionListener enrolledClicked(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(student.getEnrollmentStatus() == 0){
					btnEnroll.setText("Enrolled");
					btnEnroll.setBackground(Color.decode("#E8E9EB"));
					student.setEnrollmentStatus(1);
					student.setEnrolledCourseCodes(student.getCourseCodes());
					btnEnroll.setEnabled(false);
				}
				List<Student> students = sm.getStore().getStudents();
				for (int i = 0; i < students.size(); i++)
					if (students.get(i).getId().equals(student.getId()))
						students.set(i, student);
				
				sm.SaveChanges();
				progresstable.setModel(sm.getStore().buildStudentCoursesTable(student,true));
			}
		};
	}
		
	private MouseAdapter addCourseClicked(){
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					if (courselist.getSelectedIndex() < 0)
						return;
					List<Student> students = sm.getStore().getStudents();
					Course c = sm.getStore().getCourseByName(String.valueOf(courselist.getSelectedValue()));
					student.addCourseCode(c.getCode());
					for (int i = 0; i < students.size(); i++)
						if (students.get(i).getId().equals(student.getId())){
							students.set(i, student);
						}
					sm.SaveChanges();
					refreshStats();
				}
			}
		};
	}
	
	private MouseAdapter removeCourseClicked(){
		return new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2) {
					if(coursetable.getSelectedRow() == -1)
						return;
					String courseCode = coursetable.getValueAt(coursetable.getSelectedRow(), 0).toString();
					
					student.removeCourseCode(courseCode);
					List<Student> students = sm.getStore().getStudents();
					for (int i = 0; i < students.size(); i++)
						if (students.get(i).getId().equals(student.getId())){
							students.set(i, student);
						}
					sm.SaveChanges();
					refreshStats();
				}
			}
		};
	}
	
	private void refreshStats(){
		Map<String,String> stats = sm.getStore().getStudentFeeStats(student);
		courselist.setModel(sm.getStore().getUnaddedCoursesForStudent(student));
		coursetable.setModel(sm.getStore().buildStudentCoursesTable(student,false));
		progresstable.setModel(sm.getStore().buildStudentCoursesTable(student,true));
		lblTotalCredits.setText("Total Credits: "+stats.get("totalcredits"));
		lblTotalCost.setText("Total Cost: $"+stats.get("totalcost"));
		lblNoOfCourses.setText("No. of Courses: "+student.getCourseCodes().size());
	}
	
	private void getEnrolled(){
		btnEnroll = new JButton("Enroll");
		if(student.getEnrollmentStatus() == 0){
			btnEnroll.setText("Enroll");
			btnEnroll.setBackground(new Color(59, 89, 182));
		}
		else{
			btnEnroll.setText("Enrolled");
			btnEnroll.setBackground(Color.decode("#E8E9EB"));
			student.setEnrollmentStatus(1);
			btnEnroll.setEnabled(false);
		}
	}
}
