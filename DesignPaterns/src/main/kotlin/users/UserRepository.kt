package users

import kotlinx.serialization.json.Json
import java.io.File

class UserRepository private constructor() {

    private val file = File("users.json")
    private val content = file.readText().trim()


    private val _users: MutableList<User> = loadAllUsers()
    val users
        get() = _users.toList()

    private fun loadAllUsers(): MutableList<User> {
        return Json.decodeFromString(content)
    }


    companion object {

        private lateinit var  instance: UserRepository

        fun getInstance(password: String): UserRepository {
            val correctPassword = File("password.txt").readText().trim()

            if (correctPassword != password) throw IllegalArgumentException("Wrong password")

            if (!::instance.isInitialized) {
                instance = UserRepository()
            }

            return instance
        }
    }

}