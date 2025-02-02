package k20230546.security.k20230546.security.service

import k20230546.security.k20230546.security.model.User
import k20230546.security.k20230546.security.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository)
{
    private val encoder = BCryptPasswordEncoder()
    fun saveUser(username: String, password: String): User
    {
        if (userRepository.existsById(username))
            throw RuntimeException("User already exists")
        val encodePassword = encoder.encode(password)
        val user = User(username = username, password = encodePassword)
        return userRepository.save(user)
    }
    fun authenticate(username: String, password: String) : Boolean
    {
        val user = userRepository.findByUsername(username) ?: return false
        return encoder.matches(password, user.password)
    }
}