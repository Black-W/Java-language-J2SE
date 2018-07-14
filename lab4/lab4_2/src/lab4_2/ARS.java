package lab4_2;

import java.util.Scanner;

public class ARS {
	private static boolean[] seat = new boolean[10]; // array seat represent the seating chart of the plane
	private static Scanner input = new Scanner(System.in);
	private static int seat_number;
	private static boolean economy_seat_full = false;
	private static boolean firstclass_seat_full = false;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			seat[i] = false; // false indicates that all the seats are empty

		while (true) {
			if (firstclass_seat_full && economy_seat_full) {
				System.out.println("Sorry,all seat is sold.Next flight leaves in 3 hours");
				break;
			}
			System.out.println("Please type 1 for First Class or type 2 for Economy");
			int choice = input.nextInt();

			switch (choice) {
			case 1:
				seat_number = assign_firstclass_section();
				break;
			case 2:
				seat_number = assign_economy_section();
				break;
			}// end swtich
			if (seat_number != -1) {
				System.out.println("Assign success.Your seat number is " + seat_number + " in the "
						+ (seat_number <= 5 ? "first class section" : "economy section"));
			}

		} // end while()
		input.close();
	}// end main

	public static int assign_economy_section() {
		seat_number = -1; // seat_number==-1 represents economy section is full
		if (!economy_seat_full) {
			for (int i = 0; i < 5; i++) {
				if (seat[i] == true)
					continue;
				else {
					seat_number = i + 1;
					seat[i] = true;
					break;
				}
			}
		}
		if (seat_number == -1) {
			economy_seat_full = true;
			if (!firstclass_seat_full) {
				System.out.println("Sorry,the economy section is full.Is it acceptable for you to "
						+ "be placed in the first-class section?Please type yes or no.");
				String choice = input.next();
				if (choice.equals("yes"))
					assign_firstclass_section();
				else
					System.out.println("Next flight leaves in 3 hours");
			}
		}
		return seat_number;
	}// end assign_economy_section()

	public static int assign_firstclass_section() {
		seat_number = -1; // seat_number==-1 represents firstclass_section is full
		if (!firstclass_seat_full) {
			for (int i = 5; i < 10; i++) {
				if (seat[i] == true)
					continue;
				else {
					seat_number = i + 1;
					seat[i] = true;
					break;
				}
			}
		}
		if (seat_number == -1) {
			firstclass_seat_full = true;
			if (!economy_seat_full) {
				System.out.println("Sorry,the first class section is full.Is it acceptable for you to "
						+ "be placed in the economy section?Please type yes or no.");
				String choice = input.next();
				if (choice.equals("yes"))
					assign_economy_section();
				else
					System.out.println("Next flight leaves in 3 hours");
			}
		}
		return seat_number;
	}// end assign_firstclass_section()
}
