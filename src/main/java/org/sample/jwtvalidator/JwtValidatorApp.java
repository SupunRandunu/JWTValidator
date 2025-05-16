package org.sample.jwtvalidator;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import org.sample.jwtvalidator.config.ConfigLoader;
import org.sample.jwtvalidator.model.ValidationResult;
import org.sample.jwtvalidator.service.JwtValidator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class JwtValidatorApp {
    public static void main(String[] args) {
        try {
            var config = ConfigLoader.load();
            var validator = new JwtValidator(config.getJwksUrl());
            ValidationResult result = validator.validate(config.getJwt());

            result.print();

        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}