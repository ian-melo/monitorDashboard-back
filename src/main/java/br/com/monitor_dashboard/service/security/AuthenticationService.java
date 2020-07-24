package br.com.monitor_dashboard.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monitor_dashboard.exception.custom.UserAuthenticationException;
import br.com.monitor_dashboard.repository.security.AuthenticationRepository;

@Service
public class AuthenticationService {

	private AuthenticationRepository repository;

	@Autowired
	public AuthenticationService(AuthenticationRepository repository) {
		this.repository = repository;
	}

	public void authenticate(final long id, final String password) {
		if (repository.authenticate(id, password) == 0)
			throw new UserAuthenticationException(id);
	}
}
