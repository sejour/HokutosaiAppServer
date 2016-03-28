package hokutosai.server.error;

@SuppressWarnings("serial")
public class HokutosaiServerException extends Exception {

	public HokutosaiServerException(String message) {
		super(message);
	}

	public HokutosaiServerException(String message, Throwable nested) {
		super(message, nested);
	}

}
