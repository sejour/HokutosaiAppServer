package hokutosai.server.util;

import java.nio.file.*;
import java.util.regex.*;

public class EndpointPath {

	private String absolute;

	public EndpointPath(String requestPath) {
		Path path = Paths.get(requestPath);
		StringBuilder pathBuilder = new StringBuilder("/");
		pathBuilder.append(path.getName(0).toString());

		Pattern idPattern = Pattern.compile("^[0-9]+$");

		String elem = null;
		Matcher matcher = null;
		int pathCount = path.getNameCount();
		for (int i = 1; i < pathCount; i++) {
			elem = path.getName(i).toString();
			matcher = idPattern.matcher(elem);
			if (matcher.find()) {
				pathBuilder.append("/{id}");
			} else {
				pathBuilder.append("/");
				pathBuilder.append(elem);
			}
		}

		this.absolute = pathBuilder.toString();
	}

	@Override
	public String toString() {
		return this.absolute;
	}

}
