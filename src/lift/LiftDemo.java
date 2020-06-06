package lift;

import java.util.Scanner;

public class LiftDemo {
	static Scanner in = new Scanner(System.in);

	
	public static void main(String[] args) {
		// erstelle neuen Lift
		Lift lift = new Lift();
		// Variable, die das Zielstockwerk beinhaltet
		int targetLevel = -1; 
		
		System.out.println("1. Etage");
		// führe unendlich lang aus
		while (true) {
			System.out.println("Ihre Eingaben bitte:");
			System.out.print("Eingabe: ");
			// lies den nächsten Integer-Wert ein
			try {
				targetLevel = in.nextInt();
				// lies Integer-Werte ein bis '99' eingegeben wird
				while (targetLevel != 99) {
					// füge Zielstockwerk der List hinzu
					lift.addLevel(targetLevel);
					System.out.print("Eingabe: ");
					// lies neues Stockwerk vom Benutzer ein
					targetLevel = in.nextInt();
				}
				// starte Lift
				lift.startLift();
			} catch (Exception ex) {
				System.out.println("Ungültige Eingabe.");
			} 			 
		}
	}

}
