package lab13_2;

public class Student {
	private String id;
	private String name;
	private String phone;
	private String email;

	public Student(String i, String n, String p, String e) {
		id = i;
		name = n;
		phone = p;
		email = e;
	}

	@Override
	public String toString() {
		return String.format("%-5s\t%-5s\t%-11s\t%-14s", id, name, phone, email);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
