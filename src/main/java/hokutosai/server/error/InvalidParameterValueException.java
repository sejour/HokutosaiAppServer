package hokutosai.server.error;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("serial")
public class InvalidParameterValueException extends BadRequestException {

	public InvalidParameterValueException(Object parameter) {
		this(parameter.getClass().getAnnotation(RequestParam.class).value(), parameter.toString());
	}

	public InvalidParameterValueException(String parameterName, Object parameterValue) {
		this(parameterName, parameterValue.toString());
	}

	public InvalidParameterValueException(String parameterName, String parameterValue) {
		super(String.format("Invalid value. [%s=%s]", parameterName, parameterValue));
	}

}
