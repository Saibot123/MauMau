package data;

import java.util.Random;

import javax.swing.JButton;

public class Karte extends JButton {
	private static final long serialVersionUID = -4284204191584865075L;
	private Farbe farbe;
	private Zahl zahl;

	public Karte(Farbe farbe, Zahl zahl) {
		this.setFarbe(farbe);
		this.setZahl(zahl);
	}

	public Karte() {
		Random rand = new Random();
		Farbe[] farben = Farbe.values();
		this.setFarbe(farben[rand.nextInt(farben.length)]);

		Zahl[] zahlen = Zahl.values();
		this.setZahl(zahlen[rand.nextInt(zahlen.length - 1)]);

		setSize(100, 50);
		setVisible(true);
		setText(this.toString());
	}

	public Farbe getFarbe() {
		return farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public Zahl getZahl() {
		return zahl;
	}

	public void setZahl(Zahl zahl) {
		this.zahl = zahl;
	}

	public boolean hatSpezielleFunktion() {
		return zahl.getAction() != null;
	}

	@Override
	public String toString() {
		return farbe.name() + " " + zahl.name();
	}

	@Override
	public boolean equals(Object obj) {
		Karte karte = (Karte) obj;
		return karte.getFarbe() == this.farbe && karte.getZahl() == this.zahl;
	}
}
