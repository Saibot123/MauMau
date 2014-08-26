package mvc;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Karte;
import data.Spieler;

public class View extends JFrame {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 1000;
	private static final String TITLE = "Mau Mau";

	private Model model;
	private JPanel obersteKarte;
	private JPanel spielerKarten;
	private JButton ziehenButton;

	public View(Model model) {
		this.model = model;
		erstelleGUI();
	}

	private void erstelleGUI() {
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		initObersterKarte();
		initSpielerKarten();
		initZiehen();
	}

	private void initZiehen() {
		ziehenButton = new JButton("ziehen");
		ziehenButton.setBounds(obersteKarte.getX(), spielerKarten.getHeight() + spielerKarten.getY() + 50, 100, 30);
		add(ziehenButton);
	}

	private void initSpielerKarten() {
		spielerKarten = model.getAktuellenSpieler();
		int width = ((Spieler) spielerKarten).getKarten().size() * (model.getObersteKarte().getWidth() + 50);
		spielerKarten.setBounds(getWidth() / 2 - (width / 2), model.getObersteKarte().getHeight() + 100, width, model.getObersteKarte().getHeight());
		add(spielerKarten);
	}

	private void initObersterKarte() {
		obersteKarte = new JPanel();
		obersteKarte.setLayout(new FlowLayout());

		JLabel karte = new JLabel(model.getObersteKarte().toString());
		obersteKarte.add(karte);
		obersteKarte.setBounds(getWidth() / 2 - (model.getObersteKarte().getWidth() / 2), 50, model.getObersteKarte().getWidth(), model.getObersteKarte()
				.getHeight());
		add(obersteKarte);
	}

	public void addZiehenButtonListener(ActionListener al) {
		ziehenButton.addActionListener(al);
	}

	public void addKartenListener(ActionListener al) {
		for (Karte karte : ((Spieler) spielerKarten).getKarten()) {
			karte.addActionListener(al);
		}
	}
}
