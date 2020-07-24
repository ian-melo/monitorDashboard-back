package br.com.monitor_dashboard.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Failed authentication")
public class UserAuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserAuthenticationException(Long id) {
		super("Failed authentication [id=" + id + "]");
	}
}
