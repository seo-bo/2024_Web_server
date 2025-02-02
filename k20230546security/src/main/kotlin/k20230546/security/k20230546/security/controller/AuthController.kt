package k20230546.security.k20230546.security.controller

import io.jsonwebtoken.Jwt
import k20230546.security.k20230546.security.service.UserService
import k20230546.security.k20230546.security.util.JwtUtil
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController
(
    private val jwtUtil: JwtUtil,
    private val userService: UserService,
)
{
    @PostMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): Map<String,String>
    {
        return if(userService.authenticate(username,password))
        {
            val token = jwtUtil.generateToken(username)
            mapOf("token" to token)
        }
        else
        {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패")
        }
    }
    @PostMapping("/register")
    fun register(@RequestParam username: String, @RequestParam password: String): Map<String,String>
    {
        val user = userService.saveUser(username, password)
        val token = jwtUtil.generateToken(username)
        return mapOf("token" to token)
    }
}
