package Entities;

//u m
public class Course {
	private int Credits;

	private String description;

	private String name;

	private String PreRequisite;

	private String Code;
	
	public Course(){
		
	}
	
	public Course(String code, String name, int credits, String preRequisite, String description) {
		super();
		Code = code;
		this.name = name;
		Credits = credits;
		PreRequisite = preRequisite;
		this.description = description;
	}

	public Course(Course course){
		this.name = course.getName();
		this.PreRequisite = course.getPreRequisite();
		this.Code = course.getCode();
		this.Credits = course.getCredits();
		this.description = course.getDescription();
	}

	public int getCredits() {
		return Credits;
	}

	public void setCredits(int Credits) {
		this.Credits = Credits;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreRequisite() {
		return PreRequisite;
	}

	public void setPreRequisite(String PreRequisite) {
		this.PreRequisite = PreRequisite;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}

	public Course(String code, String name, String preRequisite, int credits, String description) {
		super();
		Code = code;
		this.name = name;
		PreRequisite = preRequisite;
		Credits = credits;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClassPojo [Credits = " + Credits + ", description = " + description + ", name = " + name
				+ ", PreRequisite = " + PreRequisite + ", Code = " + Code + "]";
	}
}
