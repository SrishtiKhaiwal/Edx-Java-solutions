import java.util.*;

public class OddsAndEvens {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // criando um novo objeto do tipo Scanner --> "input"
		Random rand = new Random(); // criando um novo objeto do tipo Random --> "rand"
		separator();
		System.out.println("\nLet's play a game called \"Odds and Evens\"!");
		System.out.print("What's your name? ");
		String name = input.nextLine();
		System.out.print("Hi " + name + ", which do you choose? (O)dds or (E)vens? ");
		String usersChoice = input.next();
		String pcChoice = "";

		while(!usersChoice.equalsIgnoreCase("O") && !usersChoice.equalsIgnoreCase("E")) {
				System.out.print("Please, insert just \"O\" to choose Odds or \"E\" to choose Evens! ");
				usersChoice = input.next();
		}

		if (usersChoice.equalsIgnoreCase("O")) {
			System.out.println(name + " has picked Odds! The computer will be Evens.");
			pcChoice = "E";
		} else if (usersChoice.equalsIgnoreCase("E")) {
			System.out.println(name + " has picked Evens! The computer will be Odds.");
			pcChoice = "O";
		}
		separator();


		int usersFingers = 0;
		while (usersFingers < 1 || usersFingers > 5) {
			System.out.print("\nHow many \"fingers\" do you put out? ");
			usersFingers = input.nextInt();
		}
		
		int pcFingers = rand.nextInt(6);

		System.out.println("The computer plays " + pcFingers + " \"fingers\".");

		separator();

		int sum = pcFingers + usersFingers;
		System.out.println("\n" + usersFingers + " + " + pcFingers + " = " + sum);

		boolean oddOrEven = (sum % 2 == 0);
		if (oddOrEven) {
			System.out.println(sum + " is ...Even!");
			if (usersChoice.equalsIgnoreCase("E")) {
				System.out.println("You win!!! Nice shot!");
			} else {
				System.out.println("Computer wins! Try again...");
			}
		} else {
			System.out.println(sum + " is ...Odd!");
			if (usersChoice.equalsIgnoreCase("O")) {
				System.out.println("You win!!! Nice shot!");
			} else {
				System.out.println("Computer wins! Try again...");
			}
		}

		separator();
	}	

	public static void separator() {
		System.out.println();
		for (int i = 0; i < 100; i++) {
		System.out.print("-");
		}
	}
}
