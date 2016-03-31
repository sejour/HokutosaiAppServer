package hokutosai.server.security;

import hokutosai.server.error.InvalidParameterValueException;

public class ParamValidator {

	public static void range(String parameterName, Integer value, int min, int max) throws InvalidParameterValueException {
		if (value == null || value < min || value > max) throw new InvalidParameterValueException(parameterName, value);
	}

}
