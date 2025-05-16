package org.sample.jwtvalidator.service;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.sample.jwtvalidator.model.ValidationResult;

import java.net.URL;
import java.util.Date;
import java.util.List;

public class JwtValidator {
    private final RemoteJWKSet<?> jwkSet;

    public JwtValidator(String jwksUrl) throws Exception {
        this.jwkSet = new RemoteJWKSet<>(new URL(jwksUrl));
    }

    public ValidationResult validate(String jwtString) throws Exception {
        ValidationResult result = new ValidationResult();
        SignedJWT signedJWT = SignedJWT.parse(jwtString);
        result.setHeader(signedJWT.getHeader().toString());

        List<JWK> jwks = jwkSet.get(new JWKSelector(JWKMatcher.forJWSHeader(signedJWT.getHeader())), null);
        if (jwks.isEmpty()) {
            result.setSignatureValid(false);
            result.setError("No matching JWK found");
            return result;
        }

        for (JWK jwk : jwks) {
            if (jwk instanceof RSAKey) {
                RSAKey rsaKey = (RSAKey) jwk;
                JWSVerifier verifier = new RSASSAVerifier(rsaKey);
                if (signedJWT.verify(verifier)) {
                    result.setSignatureValid(true);
                    JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
                    result.setClaims(claims);
                    result.validateTimestamps();
                    return result;
                }
            }
        }

        result.setSignatureValid(false);
        result.setError("Signature verification failed");
        return result;
    }
}
