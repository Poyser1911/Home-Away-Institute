package Entities;

import java.util.ArrayList;
import java.util.List;

public class Programme {
	private int maxCources;

	private String name;

	private String Code;

	private String Accreditation;

	private String Award;
	
	private List<String> courseCodes = new ArrayList<String>();

	public int getMaxCources() {
		return maxCources;
	}

	public void setMaxCources(int maxCources) {
		this.maxCources = maxCources;
	}

	public Programme(String code, String name, int maxCources, String award, String accreditation) {
		Code = code;
		this.name = name;
		this.maxCources = maxCources;
		Award = award;
		Accreditation = accreditation;
	}

	public Programme() {
		
	}
	public void addCourse(String courseID){
		courseCodes.add(courseID);
	}
	public void removeCourse(String courseID){
		courseCodes.remove(courseID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}

	public String getAccreditation() {
		return Accreditation;
	}

	public void setAccreditation(String Accreditation) {
		this.Accreditation = Accreditation;
	}

	public String getAward() {
		return Award;
	}

	public void setAward(String Award) {
		this.Award = Award;
	}
	
	public List<String> getCourseCodes() {
		return courseCodes;
	}

	public void setCourseCodes(List<String> courseCodes) {
		this.courseCodes = courseCodes;
	}
	
	@Override
	public String toString() {
		return "ClassPojo [maxCources = " + maxCources + ", name = " + name + ", Code = " + Code + ", Accreditation = "
				+ Accreditation + ", Award = " + Award + "]";
	}
}
