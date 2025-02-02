package k20230546.security.k20230546.security.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtUtil
{
    companion object
    {
        private const val EXPIRATION_TIME = 30 * 1000
    }
    @Value("\${jwt.secret-key}")
    private lateinit var base64EncodedSecretKey: String
    private lateinit var key: Key
    @PostConstruct
    fun init()
    {
        val decodedKey = Base64.getDecoder().decode(base64EncodedSecretKey)
        key = Keys.hmacShaKeyFor(decodedKey)
    }
    fun generateToken(username: String): String
    {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
    fun validateToken(token:String): Boolean
    {
        return try
        {
            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
            claims.expiration.after(Date())
        }
        catch (e: Exception)
        {
            false
        }
    }
}