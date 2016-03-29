package hokutosai.server.data.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ApiAccessCertificate {

	private Date date;

	private String uri;

	private String method;

	private String category;

	private String role;

	private String userId;

	public ApiAccessCertificate(String uri, String method, String category, String role, String userId) {
		this.date = new Date();
		this.uri = uri;
		this.method = method;
		this.category = category;
		this.role = role;
		this.userId = userId;
	}

}
