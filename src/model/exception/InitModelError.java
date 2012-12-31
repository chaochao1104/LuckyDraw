package model.exception;

public class InitModelError extends Exception {

	private static final long serialVersionUID = 255866846750137688L;

	public InitModelError() {
		super();
	}
	
	public InitModelError(String msg) {
		super(msg);
	}
}
