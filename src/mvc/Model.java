package mvc;

import java.util.ArrayList;
import java.util.List;

import data.Karte;
import data.Spieler;
import data.Stapel;

public class Model {
	private final int MAX_KARTEN = 32;
	private final int MAX_SPIELER = 3;
	private List<Spieler> spieler;
	private int aktuellerSpieler;
	private Karte obersteKarte;

	public Model() {
		aktuellerSpieler = 0;
		erstelleSpieler();
		setObersteKarte(null);
	}

	private void erstelleSpieler() {
		int kartenProSpieler = MAX_KARTEN / (2 * MAX_SPIELER);
		spieler = new ArrayList<Spieler>();
		for (int i = 0; i < MAX_SPIELER; i++) {
			spieler.add(new Spieler("Spieler" + (i + 1), kartenProSpieler, Stapel.getInstance()));
		}
	}

	public List<Spieler> getAllSpieler() {
		return spieler;
	}

	public Spieler getAktuellenSpieler() {
		return spieler.get(aktuellerSpieler);
	}

	public void naechsterSpieler() {
		aktuellerSpieler = (aktuellerSpieler + 1) % MAX_SPIELER;
	}

	public void setObersteKarte(Karte karte) {
		if (karte == null) {
			karte = Stapel.getInstance().getKarte();
		}
		obersteKarte = karte;
	}

	public Karte getObersteKarte() {
		return obersteKarte;
	}

}
