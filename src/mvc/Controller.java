package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import computer.ComputerSpieler;

import data.Farbe;
import data.Karte;
import data.Zahl;

public class Controller {
	private View view;
	private Model model;

	private ComputerSpieler computerSpieler;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		setListeners();
		prüfeObersteKarteAufSpezielleFunktion(true);
		checkForComputerSpieler();
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
					if (!model.aktuellerSpielerZiehen()) {
						view.showEndGamePanel(model.getEND_MSG_NO_CARDS());
					}
					generelleAktionen();
				} else {
					String[] farbZahl = ((Karte) e.getSource()).toString().split(" ");
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
		checkForComputerSpieler();
	}

	private void checkForComputerSpieler() {
		if (model.getAktuellenSpieler().isComputerSpieler()) {
			model.getAktuellenSpieler().updatePanel();
			view.updateSpielerKarten();
			computerSpieler = new ComputerSpieler(this);
			computerSpieler.run();
		}
	}

	private void validiereKarte(Karte karte) {
		if (model.validiereGespielteKarte(karte)) {
			model.spieleKarteDesAktuellenSpielers(karte);
			if (model.getAktuellenSpieler().getKarten().size() == 0) {
				view.showEndGamePanel(model.getAktuellenSpieler().getSpielerName() + model.getEND_MSG_WINNING_PLAYER());
			}
			prüfeObersteKarteAufSpezielleFunktion(false);
			model.getAktuellenSpieler().updatePanel();
			view.updateSpielerKarten();
			generelleAktionen();
		}
	}

	private void prüfeObersteKarteAufSpezielleFunktion(boolean isErsteKarte) {
		if (model.checkForSpecialFunctionAndNeedsMoreAction()) {
			Farbe farbe = null;
			if (model.getAktuellenSpieler().isComputerSpieler()) {
				farbe = computerSpieler.askComputerForFarbe();
			} else {
				farbe = view.doWuenschenAction();
			}
			model.setObersteKarte(new Karte(farbe, Zahl.ALL));
		}
		if (isErsteKarte) {
			model.getAktuellenSpieler().updatePanel();
			view.updateSpielerKarten();
			view.updateObersteKarte();
			view.addKartenListener(erstelleListener());
		}
	}

	public List<Karte> getSpielerKarten() {
		return model.getAktuellenSpieler().getKarten();
	}

	public Karte getObersteKarte() {
		return model.getObersteKarte();
	}

	public void doClickOnCard(Integer index) {
		if (index == null) {
			view.doClickOnZiehen();
		} else {
			view.doClickOnCard(index);
		}
	}

	public boolean istKarteSpielbar(Karte karte) {
		return model.validiereGespielteKarte(karte);
	}
}
