package elevator;

import java.util.*;

public class Elevator {
	private int currentLevel = 1;
	private List<Integer> levelsToReach;
	
	public Elevator() {
		levelsToReach = new ArrayList<Integer>(); 
	}
	
	public void addLevel(int level) {
		if (levelsToReach.size() < 4) {
			if (level < -3 || level > 12) {
				System.out.println("Nur Stockwerke von -3 bis 12 sind mit dem Fahrstuhl erreichbar.");
			} else {
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
	
	public void startElevator() {
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
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!! TODO: 
				if (diffMinToCurrLevel > diffMaxToCurrLevel) {
					// aktuelles Stockwerk näher an oberstes Stockwerk
					// => fahre ins oberste gegebene Stockwerk
					int stepDirection = currentLevel > maxLevel ? -1 : 1;
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
					currentLevel = minLevel;
				} else {
					// aktuelles Stockwerk näher an unterstes Stockwerk
					// => fahre ins unterste gegebene Stockwerk
					int stepDirection = currentLevel > minLevel ? -1 : 1;
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
					currentLevel = maxLevel;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				levelsToReach.clear();
			}
		}
	}
	
}
