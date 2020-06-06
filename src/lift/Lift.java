package lift;

import java.util.*;

public class Lift {
	// Variable, die immer aktuelles Stockwerk anzeigt
	private int currentLevel = 1;
	// Liste, die die anzufahrendes Stockwerke beinhaltet
	private List<Integer> levelsToReach;
	
	// Konstruktor der Lift-Klasse
	public Lift() {
		// Initialisiert Liste der anzufahrenden Stockwerke
		levelsToReach = new ArrayList<Integer>(); 
	}
	
	// Funktion, um Stockwerk hinzuzufügen
	public void addLevel(int level) {
		// füge nur Stockwerke hinzu, wenn es bisher weniger als 4 sind (insgesamt maximal 4)
		if (levelsToReach.size() < 4) {
			// Stockwerknummer muss sich zwischen -3 und 12 befinden
			if (level < -3 || level > 12) {
				System.out.println("Nur Stockwerke von -3 bis 12 sind mit dem Fahrstuhl erreichbar.");
			} else {
				// aktuelles Stockwerk darf nicht wieder ausgewählt werden
				if (level == currentLevel) {
					System.out.println(String.format("Fahrstuhl befindet sich bereits im %-3s. Stockwerk.", level));
				} else {
					levelsToReach.add(level);
				}
			}
		} else {
			System.out.println("Es sind bereits 4 Stockwerke angegeben. Bitte starten Sie den Fahrstuhl.");
		}
	}
	
	public void startLift() {
		// starte Lift nur, wenn es mehr als 1 neues STockwerk gibt
		if (levelsToReach.size() == 0) {
			System.out.println("Kein Stockwerk angegeben. Bitte fügen Sie Stockwerke hinzu.");
		} else {
			// sortiere Stockwerke von unten nach oben
			Collections.sort(levelsToReach);
			// merke oberstes und unterstes Stockwerk
			int minLevel = levelsToReach.get(0);
			int maxLevel = levelsToReach.get(levelsToReach.size() - 1);
			// berechne Differenz zwischen untersten und obersten Stockwerk im Vergleich zum aktuellen
			int diffMinToCurrLevel = Math.abs(minLevel - currentLevel);
			int diffMaxToCurrLevel = Math.abs(maxLevel - currentLevel);
					
			try {
				if (diffMinToCurrLevel > diffMaxToCurrLevel) {
					// aktuelles Stockwerk näher an oberstes Stockwerk
					// => fahre ins oberste gegebene Stockwerk
					// bestimme dafür, ob das neue "oberste" Stockwerk ober- oder unterhalb des jetzigen liegt
					int stepDirection = currentLevel > maxLevel ? -1 : 1;
					// gehe solange in Richtung des neuen obersten Stockwerkes bis wir uns ein Stockwerk davor befinden
					while (Math.abs(currentLevel - maxLevel) > 1) {
						Thread.sleep(1000);
						System.out.println(String.format("%s. Etage", currentLevel += stepDirection));
					}
					// wir befinden uns ein Stockwerk unter dem höchsten gegebenen Stockwerk
					currentLevel = maxLevel - 1;
					Thread.sleep(1000);
					System.out.println(String.format("%s. Etage: Aussteigen bitte", ++currentLevel));
					// nun fahre ins unterste gegebene Stockwerk
					for (int level = currentLevel - 1; level >= minLevel; level--) {
						Thread.sleep(1000);
						// Falls das aktuelle Stockwerk ein anzufahrendes Stockwerk ist: Aussteigen
						if (levelsToReach.contains(level)) {
							System.out.println(String.format("%s. Etage: Aussteigen bitte", level));
						} else {
							System.out.println(String.format("%s. Etage", level));
						}
					}
					// aktuelle Stockwerk = unterstes Stockwerk der vorherigen Fahr
					currentLevel = minLevel;
				} else {
					// aktuelles Stockwerk näher an unterstem Stockwerk
					// => fahre ins unterste gegebene Stockwerk
					// bestimme dafür, ob das neue "unterste" Stockwerk ober- oder unterhalb des jetzigen liegt
					int stepDirection = currentLevel > minLevel ? -1 : 1;
					// gehe solange in Richtung des neuen untersten Stockwerkes bis wir uns ein Stockwerk davor befinden
					while (Math.abs(currentLevel - minLevel) > 1) {
						Thread.sleep(1000);
						System.out.println(String.format("%s. Etage", currentLevel += stepDirection));
					}
					// wir befinden uns ein Stockwerk über dem untersten gegebenen Stockwerk
					currentLevel = minLevel + 1;
					Thread.sleep(1000);
					System.out.println(String.format("%s. Etage: Aussteigen bitte", --currentLevel));
					// nun fahre ins unterste gegebene Stockwerk
					for (int level = currentLevel + 1; level <= maxLevel; level++) {
						Thread.sleep(1000);
						// Falls das aktuelle Stockwerk ein anzufahrendes Stockwerk ist: Aussteigen
						if (levelsToReach.contains(level)) {
							System.out.println(String.format("%s. Etage: Aussteigen bitte", level));
						} else {
							System.out.println(String.format("%s. Etage", level));
						}
					}
					// aktuelle Stockwerk = oberstes Stockwerk der vorherigen Fahr
					currentLevel = maxLevel;
				}
			} catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			} finally {
				// lösche nach jeder Fahrt die bisherige Liste
				levelsToReach.clear();
			}
		}
	}
	
}
