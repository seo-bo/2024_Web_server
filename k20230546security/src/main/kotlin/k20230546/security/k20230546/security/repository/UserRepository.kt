package k20230546.security.k20230546.security.repository

import k20230546.security.k20230546.security.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User,String>
{
    fun findByUsername(username: String): User?
}