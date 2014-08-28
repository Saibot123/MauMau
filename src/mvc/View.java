package mvc;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import start.MauMau;
import data.Farbe;
import data.Karte;
import data.Spieler;

public class View extends JFrame {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 1000;
	private static final String TITLE = "Mau Mau";

	private Model model;
	private JPanel obersteKarte;
	private JPanel kartenPanel;
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
		ziehenButton.setBounds(obersteKarte.getX(), kartenPanel.getHeight() + kartenPanel.getY() + 50, 100, 30);
		add(ziehenButton);
	}

	private void initSpielerKarten() {
		kartenPanel = new JPanel();
		kartenPanel.setLayout(new CardLayout());
		int width = 0;
		for (Spieler spieler : model.getAllSpieler()) {
			spieler.updatePanel();
			kartenPanel.add(spieler, spieler.getSpielerName());
			width = (spieler.getKarten().size() * (model.getObersteKarte().getWidth() + 50));
		}
		kartenPanel.setBounds(getWidth() / 2 - (width / 2), model.getObersteKarte().getHeight() + 100, width, model.getObersteKarte().getHeight() * 2);
		add(kartenPanel);
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
		for (Karte karte : model.getAktuellenSpieler().getKarten()) {
			if (karte.getActionListeners().length == 0) {
				karte.addActionListener(al);
			}
		}
	}

	public void updateObersteKarte() {
		obersteKarte.removeAll();
		repaint();
		JLabel karteLabel = new JLabel(model.getObersteKarte().toString());
		obersteKarte.add(karteLabel);
		validate();
	}

	public void updateSpielerKarten() {
		CardLayout cl = (CardLayout) kartenPanel.getLayout();
		cl.show(kartenPanel, model.getAktuellenSpieler().getSpielerName());
	}

	public Farbe doWuenschenAction() {
		Object[] options = Farbe.values();
		int wahl = JOptionPane.showOptionDialog(this, "Welche Farbe wünschst du dir?", "Farbe wählen", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, null);
		return (Farbe) options[wahl];
	}

	public void showEndGamePanel(String msg) {
		Object[] options = { "Ja", "Nein" };
		int wahl = JOptionPane.showOptionDialog(this, msg, "Ende", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		if (wahl == 1) {
			System.exit(0);
		} else {
			this.removeAll();
			this.dispose();
			MauMau.main(null);
		}
	}
}
