package id.my.hendisantika.springbookstoreapi.util;

import id.my.hendisantika.springbookstoreapi.common.AccessDeniedException;
import id.my.hendisantika.springbookstoreapi.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-book-store-api
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/13/24
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JwtUtils {

    private static final String secret = "This_is_secret_key_for_jwt_signing_must_be_at_least_256_bits";
    private static final long expiryDuration = 60 * 60;
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateJwt(User user) {
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // generate jwt using claims
        return Jwts.builder()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt)
                .claim("type", user.getUserType())
                .claim("name", user.getName())
                .claim("emailId", user.getEmailId())
                .signWith(secretKey)
                .compact();
    }

    public Claims verify(String authorization) throws Exception {
        try {
            // TODO: Fix JWT parsing with correct version
            throw new AccessDeniedException("JWT verification not implemented");
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
