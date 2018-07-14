package lab5_3;

public class Dog extends Animal {
	private String kind;
	public  Dog() {
		kind = "Dog";
	}
	public String animalKind() {
		return kind;
	}
	@Override
	public void talk() {
		System.out.println("Dog talk!");
	}
}
