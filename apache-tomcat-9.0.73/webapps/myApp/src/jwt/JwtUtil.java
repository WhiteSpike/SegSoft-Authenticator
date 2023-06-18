package jwt;

import accessControl.AccessControllerClass;
import accessControl.Permission;
import accessControl.Role;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUtil {

    private static final long EXPIRATION_TIME = 1800000L; // 30 mins in milliseconds
    private static final String SECRET_KEY = "secret-key"; // this is a major vulnerability for now

    public String createJWT(String name) {
        System.out.println("Creating Capabilities for " + name);
        boolean isAdmin = true; // add getting role from the database/file
        try {
            Date now = new Date();
            Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

            // The JWT signature algorithm we will be using to sign the token
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            // We will sign our JWT with our Secret Key
            byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

            // Let's set the JWT Claims
            JwtBuilder builder = Jwts.builder()
                    .setId(name)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .claim("isAdmin", isAdmin)
                    .signWith(signatureAlgorithm, signingKey);

            System.out.println("JWT created successfully");
            return builder.compact();

        } catch (Exception e) {
            System.out.println("Error creating JWT: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    public String createJWT(String name, List<Permission> permissions) {
        System.out.println("Creating Capabilities for " + name);
        try {
            Date now = new Date();
            Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

            // The JWT signature algorithm we will be using to sign the token
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            // We will sign our JWT with our Secret Key
            byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

            // Let's set the JWT Claims
            JwtBuilder builder = Jwts.builder()
                    .setId(name)
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .claim("permissions", permissions)
                    .signWith(signatureAlgorithm, signingKey);

            System.out.println("JWT created successfully");
            return builder.compact();

        } catch (Exception e) {
            System.out.println("Error creating JWT: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static Claims parseJWT(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return null;
    }

}
