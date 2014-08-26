package data;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spieler extends JPanel {
	private static final long serialVersionUID = -4032195953189702670L;
	private List<Karte> karten;
	private String spieler;

	public Spieler(String spieler, int maxKarten, Stapel kartenStapel) {
		if (maxKarten < 1 || maxKarten > kartenStapel.getStapel().size()) {
			throw new IllegalArgumentException("Nicht genügend Karten verfügbar");
		}
		this.spieler = spieler;
		karten = new ArrayList<Karte>();
		for (int i = 0; i < maxKarten; i++) {
			karten.add(kartenStapel.getStapel().pop());
		}

		setLayout(new FlowLayout());
	}

	public void updatePanel() {
		removeAll();
		repaint();
		add(new JLabel(spieler + ":"));
		for (Karte karte : karten) {
			add(karte);
		}
	}

	@Override
	public String toString() {
		String ausgabe = "Karten von " + spieler + ":";
		for (Karte karte : karten) {
			ausgabe += " | " + karte.toString() + " | ";
		}
		return ausgabe;
	}

	public List<Karte> getKarten() {
		return karten;
	}

	public void ziehen() {
		karten.add(Stapel.getInstance().getKarte());
	}

	public Karte karteAusspielen(Karte karte) {
		karten.remove(karte);
		return karte;
	}

	public String getSpielerName() {
		return spieler;
	}
}
