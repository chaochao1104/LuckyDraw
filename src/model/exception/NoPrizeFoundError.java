package model.exception;

public class NoPrizeFoundError extends Exception {

	private static final long serialVersionUID = 5961175462954188458L;

	public NoPrizeFoundError() {
		super();
	}
	
	public NoPrizeFoundError(String msg) {
		super(msg);
	}
	
}