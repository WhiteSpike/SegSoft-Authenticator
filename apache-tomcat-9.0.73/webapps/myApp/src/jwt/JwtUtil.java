package jwt;

import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class JwtUtil {

    private static final String NAME = "TestUser";
    private static final String TYPE = "user";
    private static final long EXPIRATION_TIME = 1800000L; // 30 mins in milliseconds
    private static final String SECRET_KEY = "secret-key"; // this is major vulnerability for now

    public static String createJWT(String name) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(name)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(jwt)
                    .getBody();

            return claims;
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return null;
    }
}
