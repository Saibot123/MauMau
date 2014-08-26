package start;

import mvc.Controller;
import mvc.Model;
import mvc.View;

public class MauMau {

	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
	}
}
