package hokutosai.server.security;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import hokutosai.server.error.BadRequestException;
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

	public static void checkErrors(Errors errors) throws BadRequestException {
		if (errors.hasErrors()) {
			StringBuilder message = new StringBuilder("[");
			for (ObjectError error : errors.getAllErrors()) {
				message.append(String.format("%s%s", message.length() == 1 ? "" : ",", error.getDefaultMessage()));
			}
			message.append("]");
			throw new BadRequestException(message.toString());
		}
	}

}
