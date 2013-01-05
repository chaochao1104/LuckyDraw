package model.exception;

public class NoCandidateListFoundError extends Exception {

	private static final long serialVersionUID = 6245995891484463889L;

	public NoCandidateListFoundError() {
		super();
	}
	
	public NoCandidateListFoundError(String msg) {
		super(msg);
	}
	
}