package start;

import mvc.Controller;
import mvc.Model;
import mvc.View;
import data.Stapel;

public class MauMau {

	public static void main(String[] args) {
		Stapel.getInstance().setInstanceNull();
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
	}
}
