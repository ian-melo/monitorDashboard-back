package br.com.monitor_dashboard.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.monitor_dashboard.data.dto.security.AccountCredentialsDTO;
import br.com.monitor_dashboard.service.security.AuthenticationService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationService authenticationService;

	@Autowired
	public AuthController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping(value = "/signin", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	@ApiOperation(value = "Authentication a user by credentials")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void sigin(@RequestBody AccountCredentialsDTO login) {
		authenticationService.authenticate(login.getIdentifier(), login.getPassword());
	}
}
