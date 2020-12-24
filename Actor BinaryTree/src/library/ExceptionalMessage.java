package library;

public class ExceptionalMessage extends Message {

	private Exception exception;
	
	public ExceptionalMessage(Exception e) {
		this.exception = e;
	}

	public Exception getException() {
		return exception;
	}

}
