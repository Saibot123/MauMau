package mvc;

import java.util.ArrayList;
import java.util.List;

import data.Karte;
import data.Spieler;
import data.Stapel;
import data.Zahl;

public class Model {
	private final int MAX_KARTEN = 32;
	private final int MAX_SPIELER = 3;
	private final int MAX_KARTEN_PRO_SPIELER = 6;
	private List<Spieler> spieler;
	private int aktuellerSpieler;
	private Karte obersteKarte;
	private int zuZiehendeKarten;
	private boolean letzteKarteSieben;

	public Model() {
		aktuellerSpieler = 0;
		letzteKarteSieben = false;
		zuZiehendeKarten = 0;
		erstelleSpieler();
		setObersteKarte(null);
	}

	private void erstelleSpieler() {
		int kartenProSpieler = MAX_KARTEN / (2 * MAX_SPIELER);
		kartenProSpieler = (kartenProSpieler > MAX_KARTEN_PRO_SPIELER) ? MAX_KARTEN_PRO_SPIELER : kartenProSpieler;
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

	public boolean validiereGespielteKarte(Karte karte) {
		if (obersteKarte.getZahl().equals(Zahl.ALL)) {
			return karte.getFarbe().equals(obersteKarte.getFarbe());
		}
		if (obersteKarte.getZahl().equals(Zahl.SIEBEN) && letzteKarteSieben) {
			return karte.getZahl().equals(Zahl.SIEBEN);
		}
		if (obersteKarte.getZahl().equals(Zahl.BUBE) && karte.getZahl().equals(Zahl.BUBE)) {
			return false;
		}
		if (karte.getZahl().equals(Zahl.BUBE)) {
			return true;
		}

		return karte.getFarbe().equals(obersteKarte.getFarbe()) || karte.getZahl().equals(obersteKarte.getZahl());

	}

	public boolean checkForSpecialFunctionAndNeedsMoreAction() {
		boolean isMoreActionRequired = false;
		if (obersteKarte.getZahl().getAction() != null) {
			switch (obersteKarte.getZahl().getAction()) {
			case ZWEI_ZIEHEN:
				letzteKarteSieben = true;
				zuZiehendeKarten += 2;
				break;
			case AUSSETZEN:
				naechsterSpieler();
				break;
			case WÜNSCHEN:
				isMoreActionRequired = true;
				break;
			default:
				break;
			}
		}
		return isMoreActionRequired;
	}

	public void spieleKarteDesAktuellenSpielers(Karte karte) {
		setObersteKarte(karte);
		spieler.get(aktuellerSpieler).karteAusspielen(karte);
	}

	public void aktuellerSpielerZiehen() {
		if (letzteKarteSieben) {
			for (int i = 0; i < zuZiehendeKarten; i++) {
				spieler.get(aktuellerSpieler).ziehen();
			}
			letzteKarteSieben = false;
		} else {
			spieler.get(aktuellerSpieler).ziehen();
		}
	}
}
