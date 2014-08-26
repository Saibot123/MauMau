package data;

public enum Zahl {
	SIEBEN(Action.ZWEI_ZIEHEN), ACHT(null), NEUN(null), ZEHN(null), BUBE(Action.W�NSCHEN), DAME(null), K�NIG(null), ASS(Action.AUSSETZEN), ALL(null);

	private final Action action;

	private Zahl(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}
}
