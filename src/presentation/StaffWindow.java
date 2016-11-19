package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import Entities.Course;
import Entities.Programme;
import Entities.Student;
import domain.SaveFileManager;
import domain.Util;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StaffWindow extends WindowBase {


	
	//Register Student
    private JTextField datebox;
    private JPasswordField passbox;
    private JTextField addrbox;
    private JTextField namebox;
    private JTextField conbox;
    private JTextField cp_accbox;
    private JTextField cp_awarbox;
    private JTextField cp_codebox;
    private JTextField cp_namebox;
    private JLabel studentid;
    private SaveFileManager sm;
    private JTable coursestable;
    private JTable studentlisttable;
    private JComboBox<String> programcodebox;
    
    //Add Progrmme
    private JButton addProgramme;
    private JComboBox<String> programmetypebox;
    private JLabel program_name;
    private JList<String> addcourselist;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaffWindow window = new StaffWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public StaffWindow() {
        initialize();
    }
    private void initialize() {
    	sm = new SaveFileManager();
        sm.Load();
        setHeaderlabel("Staff");
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setOpaque(true);
        tabbedPane.setBounds(12, 109, 675, 403);
        tabbedPane.setBackground(Color.white);
        frame.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        tabbedPane.addTab("Register Student", null, panel, null);
        tabbedPane.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblName.setBounds(86, 11, 122, 44);
        panel.add(lblName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setBounds(86, 50, 122, 44);
        panel.add(lblPassword);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAddress.setBounds(86, 146, 122, 44);
        panel.add(lblAddress);

        JLabel lblProgrameCode = new JLabel("Programe Code");
        lblProgrameCode.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblProgrameCode.setBounds(86, 189, 122, 44);
        panel.add(lblProgrameCode);

        JLabel lblDateEnrolled = new JLabel("Date Enrolled");
        lblDateEnrolled.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDateEnrolled.setBounds(86, 245, 122, 37);
        panel.add(lblDateEnrolled);

        datebox = new JTextField();
        datebox.setColumns(10);
        datebox.setBounds(204, 248, 311, 30);
        panel.add(datebox);

        passbox = new JPasswordField();
        passbox.setColumns(10);
        passbox.setBounds(204, 60, 311, 30);
        panel.add(passbox);

        studentid = new JLabel("Student ID: ");
        studentid.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        studentid.setHorizontalAlignment(SwingConstants.CENTER);
        studentid.setBounds(0, 356, 567, 24);
        panel.add(studentid);

        addrbox = new JTextField();
        addrbox.setColumns(10);
        addrbox.setBounds(204, 149, 311, 30);
        panel.add(addrbox);

        namebox = new JTextField();
        namebox.setColumns(10);
        namebox.setBounds(204, 18, 311, 30);
        panel.add(namebox);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addStudentClicked());
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        addButton.setFocusPainted(false);
        addButton.setBackground(new Color(59, 89, 182));
        addButton.setBounds(199, 308, 262, 37);
        panel.add(addButton);

        JLabel lblContact = new JLabel("Contact #");
        lblContact.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblContact.setBounds(86, 105, 122, 37);
        panel.add(lblContact);

        conbox = new JTextField();
        conbox.setColumns(10);
        conbox.setBounds(204, 108, 311, 30);
        conbox.addKeyListener(Onlydigits());
        panel.add(conbox);
        
        programcodebox = new JComboBox<String>();
        programcodebox.setBounds(204, 200, 311, 29);
        programcodebox.setModel(getProgramCodes());
        programcodebox.addActionListener(onProgrammeBoxSelectionChanged());
        if(programcodebox.getItemCount() != 0)
        programcodebox.setSelectedIndex(0);
        panel.add(programcodebox);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        tabbedPane.addTab("Programme Administration", null, panel_1, null);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));

        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        panel_1.add(tabbedPane_2);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.WHITE);
        tabbedPane_2.addTab("Create Programme", null, panel_4, null);
        panel_4.setLayout(null);

        JLabel lblCode = new JLabel("Code");
        lblCode.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCode.setBounds(75, 11, 122, 44);
        panel_4.add(lblCode);

        JLabel lblCode_1 = new JLabel("Name");
        lblCode_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCode_1.setBounds(75, 50, 122, 44);
        panel_4.add(lblCode_1);

        JLabel lblAward = new JLabel("Award");
        lblAward.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAward.setBounds(75, 146, 122, 44);
        panel_4.add(lblAward);

        JLabel lblAccreditation = new JLabel("Accreditation");
        lblAccreditation.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAccreditation.setBounds(75, 189, 122, 44);
        panel_4.add(lblAccreditation);

        cp_accbox = new JTextField();
        cp_accbox.setColumns(10);
        cp_accbox.setBounds(193, 195, 311, 30);
        cp_accbox.addKeyListener(Onlydigits());
        panel_4.add(cp_accbox);

        programmetypebox = new JComboBox<String>();
        programmetypebox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        programmetypebox.setModel(new DefaultComboBoxModel<String>(new String[] {
            "Certificate Programme", "Diploma Programme", "Associate Degree Programme"
        }));
        programmetypebox.setBounds(193, 105, 311, 31);
        panel_4.add(programmetypebox);

        cp_awarbox = new JTextField();
        cp_awarbox.setColumns(10);
        cp_awarbox.setBounds(193, 149, 311, 30);
        cp_awarbox.addKeyListener(Onlydigits());
        panel_4.add(cp_awarbox);

        cp_codebox = new JTextField();
        cp_codebox.setColumns(10);
        cp_codebox.setBounds(193, 18, 311, 30);
        panel_4.add(cp_codebox);

        addProgramme = new JButton("Add");
        addProgramme.addActionListener(addProgramme());
        addProgramme.setForeground(Color.WHITE);
        addProgramme.setFont(new Font("Tahoma", Font.BOLD, 12));
        addProgramme.setFocusPainted(false);
        addProgramme.setBackground(new Color(59, 89, 182));
        addProgramme.setBounds(188, 308, 262, 37);
        panel_4.add(addProgramme);

        JLabel lblMaximumCourses = new JLabel("Type");
        lblMaximumCourses.setToolTipText(" Maximum number of courses.");
        lblMaximumCourses.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMaximumCourses.setBounds(75, 105, 111, 37);
        panel_4.add(lblMaximumCourses);

        cp_namebox = new JTextField();
        cp_namebox.setColumns(10);
        cp_namebox.setBounds(193, 66, 311, 30);
        panel_4.add(cp_namebox);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        tabbedPane_2.addTab("Modify Programme Details", null, panel_2, null);
        panel_2.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(190, 40, 475, 213);
        panel_2.add(scrollPane);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(0, 40, 180, 213);
        panel_2.add(scrollPane_2);

        JLabel lblNewLabel = new JLabel("Click to Add Course");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setBounds(0, 11, 180, 29);
        panel_2.add(lblNewLabel);

        JLabel label_4 = new JLabel("");
        label_4.setOpaque(true);
        label_4.setBackground(new Color(59, 89, 182));
        label_4.setBounds(180, 40, 14, 213);
        panel_2.add(label_4);

        JLabel label_5 = new JLabel("");
        label_5.setOpaque(true);
        label_5.setBackground(new Color(59, 89, 182));
        label_5.setBounds(0, 3, 665, 37);
        panel_2.add(label_5);

        JLabel lblSelectProgramme = new JLabel("Select Programme");
        lblSelectProgramme.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectProgramme.setForeground(Color.WHITE);
        lblSelectProgramme.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSelectProgramme.setBounds(243, 262, 180, 31);
        panel_2.add(lblSelectProgramme);

        JLabel label_6 = new JLabel("");
        label_6.setOpaque(true);
        label_6.setBackground(new Color(59, 89, 182));
        label_6.setBounds(0, 250, 665, 43);
        panel_2.add(label_6);

        program_name = new JLabel("<Programme Name>");
        program_name.setHorizontalAlignment(SwingConstants.CENTER);
        program_name.setForeground(Color.BLACK);
        program_name.setBackground(new Color(59, 89, 182));
        program_name.setFont(new Font("Arial", Font.PLAIN, 14));
        program_name.setBounds(190, 303, 297, 29);
        panel_2.add(program_name);

        addcourselist = new JList<String>();
        scrollPane_2.setViewportView(addcourselist);
        addcourselist.setBackground(Color.WHITE);
        addcourselist.addMouseListener(AddCourse());
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem Edit = new JMenuItem("Edit");
        Edit.addActionListener(editClicked());
        popupMenu.add(Edit);
        coursestable = new JTable();
        scrollPane.setViewportView(coursestable);
        coursestable.setBackground(Color.WHITE);
        coursestable.setComponentPopupMenu(popupMenu);
        coursestable.addMouseListener(removeCourse());

        JButton next_programme = new JButton(">>");
        next_programme.addActionListener(nextProgramme());
        next_programme.setForeground(Color.WHITE);
        next_programme.setFont(new Font("Tahoma", Font.BOLD, 12));
        next_programme.setFocusPainted(false);
        next_programme.setBackground(new Color(59, 89, 182));
        next_programme.setBounds(497, 299, 158, 37);
        panel_2.add(next_programme);
        next_programme.doClick();
        JButton button_2 = new JButton("<<");
        button_2.addActionListener(prevProgramme());
        button_2.setForeground(Color.WHITE);
        button_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_2.setFocusPainted(false);
        button_2.setBackground(new Color(59, 89, 182));
        button_2.setBounds(20, 299, 158, 37);
        panel_2.add(button_2);
        button_2.doClick();

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        tabbedPane_2.addTab("Student List", null, panel_3, null);
        panel_3.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 665, 347);
        panel_3.add(scrollPane_1);

        studentlisttable = new JTable();
        studentlisttable.setModel(sm.getStore().buildStudentTable());
        scrollPane_1.setViewportView(studentlisttable);

    }
    
    
    private void generateID() {
        studentid.setText(sm.getStore().generateID(getProgramCode()));
    }
    
    private ActionListener onProgrammeBoxSelectionChanged(){
    	return new ActionListener () {
    	    public void actionPerformed(ActionEvent e) {
    	    	generateID();
    	    }
    	};
    }

    private ActionListener addStudentClicked(){
    	return new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
            	if(programcodebox.getItemCount() == 0){
            		MessageBox.Error("Cannot Register any Student when No Programmes found in database");
            		return;
            	}
            	
            	if(!Util.isValidName(namebox.getText())){
            		namebox.grabFocus();
            		namebox.selectAll();
            		return;
            	}
            	
            	if(!Util.isValidPassword(new String(passbox.getPassword()))){
            		passbox.grabFocus();
            		passbox.selectAll();
            		return;
            	}
            	
            	if(!Util.isValidContact(new String(conbox.getText()))){
            		passbox.grabFocus();
            		passbox.selectAll();
            		return;
            	}
            	if(!Util.isValidAddress(new String(addrbox.getText()))){
            		addrbox.grabFocus();
            		addrbox.selectAll();
            		return;
            	}

            	
            	if(!Util.isValidDate(datebox.getText())){
					datebox.grabFocus();
					datebox.selectAll();
					return;
				}
            	
                sm.addStudent(new Student(studentid.getText().substring(12), new String(passbox.getPassword()), conbox.getText(),
                addrbox.getText(), namebox.getText(), 0, getProgramCode(), datebox.getText()));
                
                sm.Load();
                generateID();
                studentlisttable.setModel(sm.getStore().buildStudentTable());
                
                MessageBox.Show("New Student ~"+namebox.getText()+" Added Sucessfully");
            }
        };
    }
    
    private String getProgramCode(){
    	
    	Pattern p = Pattern.compile("\\((\\w+)\\)");
    	Matcher m = p.matcher(programcodebox.getSelectedItem().toString());
    	String programecode = "";
    	if (m.find())
    		programecode =  m.group(1);
    	
    	return programecode;
    }
    
    private ActionListener addProgramme(){
    	return new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int maxCources = getMaxCources(programmetypebox.getSelectedIndex());
                
                if(!Util.isValid(cp_codebox.getText(),"Please Enter a valid Program Code.")){
                	cp_codebox.grabFocus();
                	cp_codebox.selectAll();
					return;
				}
                
                if(!Util.isValidName(cp_namebox.getText())){
                	cp_namebox.grabFocus();
                	cp_namebox.selectAll();
					return;
				}
                
                if(!Util.isValidAcc(cp_awarbox.getText())){
                	MessageBox.Show("Award must be a number between 0-4");
                	cp_awarbox.grabFocus();
                	cp_awarbox.selectAll();
					return;
				}
                if(!Util.isValidAcc(cp_accbox.getText())){
                	MessageBox.Show("Accreditation must be a number between 0-4");
                	cp_accbox.grabFocus();
                	cp_accbox.selectAll();
					return;
				}
         
                sm.addProgramme(new Programme(cp_codebox.getText(), cp_namebox.getText(), maxCources,
                    cp_awarbox.getText(), cp_accbox.getText()));
                sm.Load();
                //coursestable.setModel(sm.getStore().buildProgrameTable());
                programcodebox.setModel(getProgramCodes());
            }
        };
    }
    
    private void addCourseToProgramme(){
    	Programme p = sm.getStore().getProgrammeByName(program_name.getText());
    	Course c =  sm.getStore().getCourseByName(String.valueOf(addcourselist.getSelectedValue()));
    	if(p.getCourseCodes().size() >= p.getMaxCources()){
    		MessageBox.Error("Program Full\nMaximum Cources ("+p.getMaxCources()+") already added");
    		return;
    	}
    	p.addCourse(c.getCode());
    	
            sm.SaveChanges();
            coursestable.setModel(sm.getStore().buildProgrameCoursesTable(p));
            addcourselist.setModel(sm.getStore().getUnaddedCourses(p));
    }
    
    private MouseAdapter removeCourse(){
    	return new MouseAdapter() {
            public void mousePressed(MouseEvent me) {

                if (me.getClickCount() == 2) {
                    String courseName = coursestable.getValueAt(coursestable.getSelectedRow(), 1).toString();
                    Programme p = sm.getStore().getProgrammeByName(program_name.getText());
                    Course c =  sm.getStore().getCourseByName(courseName);
                    
                    p.removeCourse(c.getCode());
                    
                    sm.SaveChanges();
                    coursestable.setModel(sm.getStore().buildProgrameCoursesTable(p));
                    addcourselist.setModel(sm.getStore().getUnaddedCourses(p));
                }
            }
        };
    }
    
    private DefaultComboBoxModel<String> getProgramCodes(){
    	DefaultComboBoxModel<String> result = new DefaultComboBoxModel<String>();
    	List<Programme> programmes = sm.getStore().getProgrammes();
    	for(Programme p : programmes)
    	result.addElement(p.getName()+"("+p.getCode()+")");
    	
    	return result;
    }
    
    private KeyAdapter Onlydigits(){
    	return new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c))
                    e.consume();
            }
        };
    }
    
    private ActionListener nextProgramme(){
    	return new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Programme p = sm.getStore().getProgramme(true);
                if (p.getCode().equals("-1"))
                    program_name.setForeground(Color.red);
                else
                	 program_name.setForeground(Color.black);
                
                program_name.setText(p.getName());
                coursestable.setModel(sm.getStore().buildProgrameCoursesTable(p));
                addcourselist.setModel(sm.getStore().getUnaddedCourses(p));
            }
        };
    }
    
    private ActionListener prevProgramme(){
    	return new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Programme p = sm.getStore().getProgramme(false);
                program_name.setText(p.getName());
                coursestable.setModel(sm.getStore().buildProgrameCoursesTable(p));
                addcourselist.setModel(sm.getStore().getUnaddedCourses(p));
            }
        };
    }
    
    private MouseAdapter AddCourse(){
    	return new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    if (addcourselist.getSelectedIndex() < 0)
                        return;
                    	
                    addCourseToProgramme();
                }
          }
        };
    }
    
    private ActionListener editClicked(){
    	return new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (coursestable.getSelectedRow() == -1)
                        return;
                    String name = coursestable.getValueAt(coursestable.getSelectedRow(), 1).toString();
                    EditCourseWindow ed = new EditCourseWindow(name);
                    ed.OpenDialog(name);
                    sm.Load();
                    Programme p = sm.getStore().getProgrammeByName(program_name.getText());
                    coursestable.setModel(sm.getStore().buildProgrameCoursesTable(p));
                    addcourselist.setModel(sm.getStore().getUnaddedCourses(p));
                } catch (Exception e) {}
            }
        };
    }
    
    public int getMaxCources(int comboboxindex) {
        switch (comboboxindex) {
            case 0:
                return 4;
            case 1:
                return 6;
            case 2:
                return 8;
        }
        return -1;
    }
    
}