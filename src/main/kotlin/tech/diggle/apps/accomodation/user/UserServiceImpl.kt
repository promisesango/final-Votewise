package tech.diggle.apps.accomodation.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import tech.diggle.apps.accomodation.role.Role
import tech.diggle.apps.accomodation.role.RoleRepository

import java.util.Arrays
import java.util.HashSet


@Service("userService")
class UserServiceImpl(
        @Qualifier("userRepository")
        @Autowired
        private val userRepository: UserRepository,
        @Qualifier("roleRepository")
        @Autowired
        private val roleRepository: RoleRepository,
        @Autowired
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserService {

    override fun findUserByEmail(email: String): User? {
        return userRepository!!.findByEmail(email)
    }

    override fun saveUser(user: User) {
        user.password = bCryptPasswordEncoder!!.encode(user.password!!)
        user.active = true
        val userRole = roleRepository!!.findByRole("USER")
        user.roles = mutableSetOf(userRole)
        userRepository!!.save(user)
        if (user.email == "promise@dtech.com") {
            val adminRole = roleRepository.findByRole("ADMIN")
            val userRoles = user.roles
            userRoles!!.add(adminRole)
            user.roles = userRoles
            userRepository.save(user)
        }
    }

    fun addRole(roleName: String) {

    }

}