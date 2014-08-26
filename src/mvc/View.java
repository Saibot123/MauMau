package mvc;

import javax.swing.JFrame;

public class View extends JFrame {
	private Model model;

	public View(Model model) {
		this.model = model;
		erstelleGUI();
	}

	private void erstelleGUI() {
		setLayout(null);
		setSize(1000, 500);
		setTitle("Mau Mau");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
