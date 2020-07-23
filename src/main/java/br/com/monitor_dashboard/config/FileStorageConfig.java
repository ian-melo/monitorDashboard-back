package br.com.monitor_dashboard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

    @Getter
    @Setter
	private String uploadDir;

}
