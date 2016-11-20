package domain;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import Entities.Course;
import Entities.Programme;
import Entities.Staff;
import Entities.Student;

import domain.Util.UserType;

public class Store {

	private static int nextprogramme = 0;
	private List<Staff> Staffs = new ArrayList<Staff>();
	private List<Student> Students = new ArrayList<Student>();
	private List<Programme> Programmes = new ArrayList<Programme>();
	private double costPerCredit = 8300.90;
	private List<Course> Courses = new ArrayList<Course>();


	public List<Staff> getStaffs() {
		return Staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		Staffs = staffs;
	}

	public List<Student> getStudents() {
		return Students;
	}

	public void setStudents(List<Student> students) {
		Students = students;
	}

	public List<Programme> getProgrammes() {
		return Programmes;
	}

	public void setProgrammes(List<Programme> programmes) {
		Programmes = programmes;
	}

	public List<Course> getCourses() {
		return Courses;
	}

	public void setCourses(List<Course> courses) {
		Courses = courses;
	}

	public double getCostPerCredit() {
		return costPerCredit;
	}

	public void setCostPerCredit(double costPerCredit) {
		this.costPerCredit = costPerCredit;
	}
	public DefaultTableModel buildStaffTable() {
		String[] columns = new String[] { "ID Number", "Password", "Name", "Faculty", "Department", "Date Employed" };
		Object[][] rows = new Object[Staffs.size()][6];
		for (int i = 0; i < Staffs.size(); i++) {
			for (int j = 0; j < columns.length; j++)
				switch (j) {
				case 0:
					rows[i][j] = Staffs.get(i).getId();
					break;
				case 1:
					rows[i][j] = Staffs.get(i).getPassword();
					break;
				case 2:
					rows[i][j] = Staffs.get(i).getName();
					break;
				case 3:
					rows[i][j] = Staffs.get(i).getFaculty();
					break;
				case 4:
					rows[i][j] = Staffs.get(i).getDepartment();
					break;
				case 5:
					rows[i][j] = Staffs.get(i).getDate_employed();
					break;
				}
		}
		return new DefaultTableModel(rows, columns) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}


	public DefaultTableModel buildStudentTable() {
		String[] columns = new String[] { "ID Number", "Name", "Contact Number", "Address", "Enrollment Status",
				"Date Enrolled" };
		Object[][] rows = new Object[Students.size()][columns.length];
		for (int i = 0; i < Students.size(); i++) {
			for (int j = 0; j < columns.length; j++)
				switch (j) {
				case 0:
					rows[i][j] = Students.get(i).getLoginId();
					break;
				case 1:
					rows[i][j] = Students.get(i).getName();
					break;
				case 2:
					rows[i][j] = Students.get(i).getContactNumber();
					break;
				case 3:
					rows[i][j] = Students.get(i).getAddress();
					break;
				case 4:
					rows[i][j] = Students.get(i).getEnrollmentStatus();
					break;
				case 5:
					rows[i][j] = Students.get(i).getDateEnrolled();
					break;
				}
		}
		return new DefaultTableModel(rows, columns) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	public String generateID(String programecode) {

		String firstpart = Year.now().getValue() + "-" + programecode + "-";
		if (Students.size() <= 0)
			return "Student ID: " + firstpart + "0";
		else {
			Student laststudent = Students.get(Students.size() - 1);
			int startindex = laststudent.getId().lastIndexOf('-') + 1;
			String id = String.valueOf(Integer.parseInt(laststudent.getId().substring(startindex)) + 1);

			return "Student ID: " + firstpart + id;
		}
	}
	public int getNumCoursesFound(Programme p){
		if(p.getCourseCodes() == null)
			return 0;
		int rowsFound = 0;
		for (int i = 0; i < Courses.size(); i++)
			if (p.getCourseCodes().contains(Courses.get(i).getCode())) 
				rowsFound++;
		
		return rowsFound;
	}
	
	
	public DefaultTableModel buildProgrameCoursesTable(Programme p) {
		String[] columns = new String[] { "Code", "Name", "PreRequisite", "Credits", "Description" };
		int found = 0;
		int rowsNum = getNumCoursesFound(p);
		
		Object[][] rows = new Object[rowsNum][columns.length];
		for (int i = 0; i < Courses.size(); i++) {
			if (p.getCourseCodes().contains(Courses.get(i).getCode())){
			for (int j = 0; j < columns.length; j++)
					switch (j) {
					case 0:
						rows[found][j] = Courses.get(i).getCode();
						break;
					case 1:
						rows[found][j] = Courses.get(i).getName();
						break;
					case 2:
						rows[found][j] = Courses.get(i).getPreRequisite();
						break;
					case 3:
						rows[found][j] = Courses.get(i).getCredits();
						break;
					case 4:
						rows[found][j] = Courses.get(i).getDescription();
						break;
					}
			found++;
			}
		}
		return new DefaultTableModel(rows, columns) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };
			 
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
		    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
				return false;
		    }
		};
	}

	public DefaultTableModel buildStudentCoursesTable(Student s,boolean isEnrolledCources) {
		String[] columns = new String[] { "Code", "Name", "PreRequisite", "Credits", "Description","Cost","C.Cost" };
		int found = 0;
		List<String> courseCodes = new ArrayList<String>();
		if(!isEnrolledCources)
			courseCodes = s.getCourseCodes();
		else
			courseCodes = s.getEnrolledCourseCodes();
		
		int rowsNum = courseCodes.size();
		Object[][] rows = new Object[rowsNum][columns.length];
		double cc = 0.0;
		for (int i = 0; i < Courses.size(); i++) {
			if (courseCodes.contains(Courses.get(i).getCode())){
			for (int j = 0; j < columns.length; j++)
					switch (j) {
					case 0:
						rows[found][j] = Courses.get(i).getCode();
						break;
					case 1:
						rows[found][j] = Courses.get(i).getName();
						break;
					case 2:
						rows[found][j] = Courses.get(i).getPreRequisite();
						break;
					case 3:
						rows[found][j] = Courses.get(i).getCredits();
						break;
					case 4:
						rows[found][j] = Courses.get(i).getDescription();
						break;
					case 5:	cc+=Courses.get(i).getCredits()*costPerCredit;
						rows[found][j] = "$"+String.format( "%.2f", Courses.get(i).getCredits()*costPerCredit);
						break;
					case 6:
					rows[found][j] = "$"+String.format( "%.2f", cc);
					break;
					}
			found++;
			}
		}
		return new DefaultTableModel(rows, columns) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class };
			 
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
		    public boolean isCellEditable(int row, int column) {
				return false;
		    }
		};
	}
	public DefaultListModel<String> getUnaddedCourses(Programme p){
		
		DefaultListModel<String> result = new DefaultListModel<String>();
		if(p.getCourseCodes() != null)
		for(Course c : Courses)
			if(!p.getCourseCodes().contains(c.getCode()))
				result.addElement(c.getName());
				
		return result;
	}
	public Map<String, String> getStudentFeeStats(Student s){
		Map<String, String> result = new HashMap<String, String>();
		List<Course> programCoursesList = getStudentCourses(s);
		double totalcost = 0.0;
		int totalcredits = 0;
		
		for (int i = 0; i < programCoursesList.size(); i++) {
			if (s.getCourseCodes().contains(programCoursesList.get(i).getCode())){
				totalcost+=Courses.get(i).getCredits()*costPerCredit;
				totalcredits+=Courses.get(i).getCredits();
			}
			}
		result.put("totalcost",String.format( "%.2f", totalcost));
		result.put("totalcredits",String.valueOf(totalcredits));
		
		return result;
	}
	public DefaultListModel<String> getUnaddedCoursesForStudent(Student student){
		
		DefaultListModel<String> result = new DefaultListModel<String>();
		List<Course> programCoursesList = getStudentCourses(student);
		for(Course c : programCoursesList)
			if(!student.getCourseCodes().contains(c.getCode()))
				result.addElement(c.getName());
			
		
		return result;
	}
	public List<Course> getStudentCourses(Student student){
		List<Course> programCoursesList = new ArrayList<Course>();
		List<String> programCourses = getProgrammeByCode(student.getProgramCode()).getCourseCodes();
		
		for(String s : programCourses)
			programCoursesList.add(getCourseByCode(s));
		
		return programCoursesList;
	}
	public Programme getProgrammeByName(String name){
		for(Programme p : Programmes)
			if(p.getName().equals(name))
				return p;
		return new Programme();
	}
	public Course getCourseByName(String name){
		for(Course c : Courses)
			if(c.getName().equals(name))
				return c;
		return new Course();
	}
	public Course getCourseByCode(String code){
		for(Course c : Courses)
			if(c.getCode().equals(code))
				return c;
		return new Course();
	}

	public Programme getProgramme(boolean isNext) {
		if(Programmes.size() == 0)
			return new Programme("-1","No Programes Found",0,"","");
		if (isNext)
			nextprogramme = nextprogramme >= Programmes.size() - 1 ? 0 : nextprogramme + 1;
		else
			nextprogramme = nextprogramme <= 0 ? Programmes.size() - 1 : nextprogramme - 1;

		return Programmes.get(nextprogramme);
	}
	
	public Student getStudentById(String id){
		for(Student s : Students)
			if(s.getLoginId().equals(id))
				return s;
		return new Student();
	}
	public Programme getProgrammeByCode(String code){
		for(Programme p : Programmes)
			if(p.getCode().equals(code))
				return p;
		return new Programme();
	}
	
	public UserType Login(String id,String password,int type){
		
		switch(type){
		case 1: 
			for(Staff s : Staffs)
				if(s.getId().equals(id) && s.getPassword().equals(Util.hashPassword(password)))
				return UserType.Staff;
			break;
		case 2: 
			for(Student s : Students)
				if(s.getLoginId().equals(id) && s.getPassword().equals(Util.hashPassword(password)))
				return UserType.Student;
			break;
		case 3: if(Util.isAdmin(id, password))
						return UserType.Adminstrator;
		}
		return UserType.None;
	}
}
