package Entities;
import domain.Util;
import java.util.ArrayList;
import java.util.List;


public class Student extends User{


	private String contactNumber;

    private String address;
	private int enrollmentStatus;

    private String programCode;

    private String dateEnrolled;
    
    private List<String> courseCodes = new ArrayList<String>();
    
    private List<String> enrolledCourseCodes = new ArrayList<String>();

    public List<String> getEnrolledCourseCodes() {
		return enrolledCourseCodes;
	}
	public void setEnrolledCourseCodes(List<String> enrolledCourseCodes) {
		this.enrolledCourseCodes = enrolledCourseCodes;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = Util.hashPassword(password);
	}
	
    public Student(String id, String password, String contactNumber, String address, String name, int enrollmentStatus,
			String programCode, String dateEnrolled) {
		this.id = id;
		this.password = Util.hashPassword(password);
		this.contactNumber = contactNumber;
		this.address = address;
		this.name = name;
		this.enrollmentStatus = enrollmentStatus;
		this.programCode = programCode;
		this.dateEnrolled = dateEnrolled;
	}
    public Student() {
		// TODO Auto-generated constructor stub
	}

	public String getId ()
    {
        return id;
    }
	public String getLoginId()
    {
        return id.replaceAll("-", "");
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getContactNumber ()
    {
        return contactNumber;
    }

    public void setContactNumber (String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public int getEnrollmentStatus ()
    {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus (int enrollmentStatus)
    {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getProgramCode ()
    {
        return programCode;
    }

    public void setProgramCode (String programCode)
    {
        this.programCode = programCode;
    }

    public String getDateEnrolled ()
    {
        return dateEnrolled;
    }

	public void setDateEnrolled(String dateEnrolled) {
		this.dateEnrolled = dateEnrolled;
	}

	public List<String> getCourseCodes() {
		return courseCodes;
	}
	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}
	public void addCourseCode(String newcode){
		courseCodes.add(newcode);
	}
	
	public void removeCourseCode(String code){
		courseCodes.remove(code);
	}
    
}
			
			
