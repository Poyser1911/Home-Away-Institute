package Entities;
import domain.User;
import domain.Util;

public class Staff extends User {
	
	private String department;

	private String date_employed;

	private String faculty;
	
	
	public Staff(String id,String password,String department,String name,String date_employed,String faculty){
		this.id = id;
		this.password = Util.hashPassword(password);
		this.department = department;
		this.name = name;
		this.date_employed = date_employed;
		this.faculty = faculty;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Util.hashPassword(password);
	}
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_employed() {
		return date_employed;
	}

	public void setDate_employed(String date_employed) {
		this.date_employed = date_employed;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

}
