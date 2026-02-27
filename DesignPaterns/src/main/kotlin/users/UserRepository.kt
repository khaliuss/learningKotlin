package users

import kotlinx.serialization.json.Json
import observers.MutableObservable
import java.io.File

class UserRepository private constructor(){

    private val file = File("users.json")
    private val content = file.readText().trim()


    private val _users: MutableList<User> = loadAllUsers()

    val users = MutableObservable(_users.toList())



    private fun loadAllUsers(): MutableList<User> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        _users.removeIf { it.id == id }
        users.currentValue = _users
    }

    fun addUser(firstName:String,lastName:String,age:Int){
        val id = _users.maxOf {  it.id }+1
        val user = User(id,firstName,lastName,age)
        _users.add(user)
        users.currentValue = _users
    }

    fun save(){
        file.writeText(Json.encodeToString(_users))
    }



    companion object {
        private val lock = Any()
        private var instance: UserRepository? = null

        fun getInstance(password: String): UserRepository {
            val correctPassword = File("password.txt").readText().trim()

            if (correctPassword != password) throw IllegalArgumentException("Wrong password")

            instance?.let { return it }

            synchronized(lock) {
                instance?.let { return it }

                return UserRepository().also {
                    instance = it
                }
            }
        }
    }

}