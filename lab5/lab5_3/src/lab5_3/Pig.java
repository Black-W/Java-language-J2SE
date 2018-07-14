package lab5_3;

public class Pig extends Animal {
	private String kind;
	public  Pig() {
		kind = "Pig";
	}
	public String animalKind() {
		return kind;
	}
	@Override
	public void talk() {
		System.out.println("Pig talk!");
	}
}
