package org.sample.jwtvalidator.model;

import com.nimbusds.jwt.JWTClaimsSet;

import java.util.Date;

public class ValidationResult {
    private boolean signatureValid;
    private String error;
    private String header;
    private JWTClaimsSet claims;
    private boolean expired;
    private boolean notYetValid;

    public void validateTimestamps() {
        if (claims == null) return;

        Date now = new Date();
        if (claims.getExpirationTime() != null)
            expired = claims.getExpirationTime().before(now);

        if (claims.getNotBeforeTime() != null)
            notYetValid = claims.getNotBeforeTime().after(now);
    }

    public void print() {
        System.out.println("🔐 JWT Header: " + header);
        if(signatureValid){
            System.out.println("✅ Signature valid");
        } else {
            System.out.println("❌ Signature invalid");
        }

        if (error != null) {
            System.out.println("❌ Error: " + error);
        }

        if (claims != null) {
            System.out.println("📋 Claims:");
            System.out.println("  ➤ Subject: " + claims.getSubject());
            System.out.println("  ➤ Issuer : " + claims.getIssuer());
            System.out.println("  ➤ Audience: " + claims.getAudience());
            System.out.println("  ➤ Expiration: " + claims.getExpirationTime());
            System.out.println("  ➤ Issued At : " + claims.getIssueTime());
            System.out.println("  ➤ Not Before: " + claims.getNotBeforeTime());

            if (expired) System.out.println("❌ Token is expired");
            else if (notYetValid) System.out.println("❌ Token is not yet valid");
            else System.out.println("✅ Token is within valid time range");
        }
    }

    // Getters and setters
    public void setSignatureValid(boolean signatureValid) { this.signatureValid = signatureValid; }
    public void setError(String error) { this.error = error; }
    public void setHeader(String header) { this.header = header; }
    public void setClaims(JWTClaimsSet claims) { this.claims = claims; }

}
