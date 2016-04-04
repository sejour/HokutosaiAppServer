package hokutosai.server.security;

import org.springframework.util.StringUtils;

import hokutosai.server.error.InvalidParameterValueException;

public class ParamValidator {

	public static Integer range(String parameterName, Integer value, int min, int max) throws InvalidParameterValueException {
		if (value == null || value < min || value > max) throw new InvalidParameterValueException(parameterName, value);
		return value;
	}

	public static String text(String parameterName, String value) throws InvalidParameterValueException {
		if (!StringUtils.hasText(value)) throw new InvalidParameterValueException(parameterName, value);
		return value;
	}

	public static String text(String parameterName, String value, Integer maxLength) throws InvalidParameterValueException {
		if (!StringUtils.hasText(value) || value.length() > maxLength) throw new InvalidParameterValueException(parameterName, value);
		return value;
	}

}
