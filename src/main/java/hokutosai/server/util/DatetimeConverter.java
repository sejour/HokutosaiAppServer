package hokutosai.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hokutosai.server.config.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatetimeConverter {

	@Autowired
	private Config config;

	private SimpleDateFormat formatter;
	private SimpleDateFormat getFormatter() {
		if (this.formatter == null) {
			this.formatter = new SimpleDateFormat(this.config.getDatetimeFormat());
		}
		return this.formatter;
	}

	public Date stringToDate(String dateStr) throws ParseException {
	    SimpleDateFormat sdf = this.getFormatter();
	    return sdf.parse(dateStr);
	}

}
