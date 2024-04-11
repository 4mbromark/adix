package it.maramb.adix.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.SignatureException;
import it.maramb.adix.entity.User;
import it.maramb.adix.security.exceptions.ApplicationReloadedSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 * Classe per la gestione dei token jwt
 *
 * @see <a href="https://www.javainuse.com/spring/boot-jwt">Fonte classe JWT</a>
 * @author Marco Ambrosi <maramb>
 */
@Component
@Slf4j
public class AdixSecurityJwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static Key key = MacProvider.generateKey();

    public String getId(String token) {
        return getClaim(token, Claims::getId);
    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Boolean isExpired(String token) {
        Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public String generate(User user) {
        return Jwts.builder().setId(user.getId().toString()).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key).compact();
    }

    public Boolean validate(String token) {
        try {
            return Objects.nonNull(token) && !token.isBlank() && !token.equals("null") && !isExpired(token);
        } catch (SignatureException e) {
            //throw new ApplicationReloadedSecurityException();
            return false;
        }
    }
}