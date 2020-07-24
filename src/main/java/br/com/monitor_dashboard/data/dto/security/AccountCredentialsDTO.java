package br.com.monitor_dashboard.data.dto.security;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private Long identifier;
	private String password;
}
