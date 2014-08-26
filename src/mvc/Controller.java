package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	}

	private ActionListener erstelleListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("ziehen")) {
					model.getAktuellenSpieler().ziehen();
					model.naechsterSpieler();
					view.updateSpielerKarten();
				} else {
				}
			}
		};
	}
}
