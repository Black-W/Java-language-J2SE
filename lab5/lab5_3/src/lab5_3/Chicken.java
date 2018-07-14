package lab5_3;

public class Chicken extends Animal {
	private String kind;
	public  Chicken() {
		kind = "Chicken";
	}
	public String animalKind() {
		return kind;
	}
	@Override
	public void talk() {
		System.out.println("Chicken talk!");
	}
}
