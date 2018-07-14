package lab11_1;

public class Student {
	private String studentNumber;//学号
	private String name;//姓名
	private String _class;//班级
	private String credits;//学分
	
	public Student(String s1,String s2,String s3,String s4) {
		studentNumber=s1;
		name=s2;
		_class=s3;
		credits=s4;
	}

	@Override
	public String toString() {
		return String.format("%-8s %-8s %-8s %-8s",studentNumber,name,_class,credits);
	}
	public String getStudentNumber() {
		return studentNumber;
	}

	public String getName() {
		return name;
	}

	public String get_class() {
		return _class;
	}

	public String getCredits() {
		return credits;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}
	
}
