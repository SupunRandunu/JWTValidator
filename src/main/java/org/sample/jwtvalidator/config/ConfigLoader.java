package org.sample.jwtvalidator.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final String jwksUrl;
    private final String jwt;

    private ConfigLoader(String jwksUrl, String jwt) {
        this.jwksUrl = jwksUrl;
        this.jwt = jwt;
    }

    public static ConfigLoader load() throws Exception {
        Properties props = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalArgumentException("config.properties file not found");
            }
            props.load(input);
        }

        String jwksUrl = props.getProperty("jwks.url");
        String jwt = props.getProperty("jwt");

        if (jwksUrl == null || jwt == null) {
            throw new IllegalArgumentException("jwks.url or jwt missing in config.properties");
        }

        return new ConfigLoader(jwksUrl, jwt);
    }

    public String getJwksUrl() {
        return jwksUrl;
    }

    public String getJwt() {
        return jwt;
    }
}
