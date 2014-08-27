package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stapel {
	private Stack<Karte> stapel;
	private int size;
	private static Stapel instance;

	public static Stapel getInstance() {
		if (instance == null) {
			instance = new Stapel();
		}
		return instance;
	}

	public static void setInstanceNull() {
		instance = null;
	}

	private Stapel() {
		stapel = new Stack<Karte>();
		this.size = 32;
		fuelleStapel(new ArrayList<Karte>());
	}

	private void fuelleStapel(List<Karte> momentanImSpiel) {
		for (int i = 0; i < size; i++) {
			boolean kannGezogenWerden = true;
			Karte karte = new Karte();
			for (Karte stapelKarte : stapel) {
				if (karte.vergleiche(stapelKarte)) {
					kannGezogenWerden = false;
					i--;
				}
			}

			for (Karte karteImSpiel : momentanImSpiel) {
				if (karte.vergleiche(karteImSpiel)) {
					kannGezogenWerden = false;
				}
			}
			if (kannGezogenWerden) {
				stapel.push(karte);
			}
		}
	}

	public Stack<Karte> getStapel() {
		return stapel;
	}

	public Karte getKarte() {
		// if (stapel.size() == 0) {
		// List<Karte> momentanImSpiel = new ArrayList<Karte>();
		// List<Spieler> spieler = Controller.getInstance(0).getSpieler();
		// for (Spieler sp : spieler) {
		// momentanImSpiel.addAll(sp.getKarten());
		// }
		// fillStapel(momentanImSpiel);
		// if (stapel.size() == 0) {
		// System.err.println("Es sind alle Karten im Spiel. Spiel wird abgebrochen.");
		// Controller.getInstance(0).finishGame();
		// }
		// }
		return stapel.pop();
	}
}
