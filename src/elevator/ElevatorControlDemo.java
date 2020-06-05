package elevator;

import java.util.Scanner;

public class ElevatorControlDemo {
	static Scanner in = new Scanner(System.in);

	
	public static void main(String[] args) {
		Elevator lift = new Elevator();
		int targetLevel = -1; 
		
		System.out.println("1. Etage");
		while (true) {
			System.out.println("Ihre Eingaben bitte:");
			System.out.print("Eingabe: ");
			targetLevel = in.nextInt();
			while (targetLevel != 99) {
				lift.addLevel(targetLevel);
				System.out.print("Eingabe: ");
				targetLevel = in.nextInt();
			}
			lift.startElevator(); 
		}
	}

}
