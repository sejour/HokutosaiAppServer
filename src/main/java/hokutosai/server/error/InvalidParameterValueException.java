package hokutosai.server.error;

@SuppressWarnings("serial")
public class InvalidParameterValueException extends BadRequestException {

	public InvalidParameterValueException(String parameterName, Object parameterValue) {
		this(parameterName, parameterValue.toString());
	}

	public InvalidParameterValueException(String parameterName, String parameterValue) {
		super(String.format("Invalid value. [%s=%s]", parameterName, parameterValue));
	}

}
