package computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mvc.Controller;
import data.Farbe;
import data.Karte;

public class ComputerSpieler {
	private Controller controller;

	public ComputerSpieler(Controller controller) {
		this.controller = controller;
	}

	public void run() {
		List<Integer> index = new ArrayList<Integer>();
		for (Karte karte : controller.getSpielerKarten()) {
			if (controller.istKarteSpielbar(karte)) {
				index.add(controller.getSpielerKarten().indexOf(karte));
			}
		}
		Integer no = null;
		if (index.size() == 0) {
			System.out.println("Keine passenden Karten");
		} else if (index.size() == 1) {
			no = 0;
		} else {
			Random rand = new Random();
			no = rand.nextInt(index.size());
			// TODO check einbauen, ob alle Karten Buben sind - wenn nicht, ist
			// es ratsam, nicht den Buben zu spielen.
		}
		controller.doClickOnCard((index.size() == 0) ? null : index.get(no));
	}

	public Farbe askComputerForFarbe() {
		Farbe wunsch = null;
		int maxTreffer = 0;
		for (Farbe farbe : Farbe.values()) {
			int treffer = 0;
			for (Karte karte : controller.getSpielerKarten()) {
				if (karte.getFarbe().equals(farbe)) {
					treffer++;
				}
			}
			if (treffer > maxTreffer) {
				maxTreffer = treffer;
				wunsch = farbe;
			}
		}
		System.out.println("Computer wünscht sich " + wunsch);
		return wunsch;
	}
}
