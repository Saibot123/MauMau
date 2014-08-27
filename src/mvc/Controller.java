package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.Farbe;
import data.Karte;
import data.Zahl;

public class Controller {
	private View view;
	private Model model;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		setListeners();
	}

	private void setListeners() {
		view.addZiehenButtonListener(erstelleListener());
		view.addKartenListener(erstelleListener());
	}

	private ActionListener erstelleListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("ziehen")) {
					model.getAktuellenSpieler().ziehen();
					generelleAktionen();
				} else {
					String[] farbZahl = e.getActionCommand().split(" ");
					Karte karte = new Karte(Farbe.valueOf(farbZahl[0].trim()), Zahl.valueOf(farbZahl[1].trim()));
					validiereKarte(karte);
				}
			}
		};
	}

	private void generelleAktionen() {
		model.naechsterSpieler();
		model.getAktuellenSpieler().updatePanel();
		view.updateSpielerKarten();
		view.updateObersteKarte();
		view.addKartenListener(erstelleListener());
	}

	private void validiereKarte(Karte karte) {
		if (model.validiereGespielteKarte(karte)) {
			model.spieleKarteDesAktuellenSpielers(karte);
			generelleAktionen();
		}
	}
}
