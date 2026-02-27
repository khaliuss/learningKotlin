package users.temp

import kotlinx.serialization.json.Json
import observers.Observer
import users.User
import java.io.File

class UserRepository2 private constructor() {

    private val file = File("users.json")
    private val content = file.readText().trim()


    private val _users: MutableList<User> = loadAllUsers()
    val users
        get() = _users.toList()

    val observers = mutableListOf<(List<User>)->Unit>()

    fun notifyObserver(){
        for (observer in observers){
            observer(users)
        }
    }

    fun registerObserver(observer: (List<User>)->Unit){
        observers.add(observer)
        observer(users)
    }

    private fun loadAllUsers(): MutableList<User> {
        return Json.Default.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        _users.removeIf { it.id == id }
        notifyObserver()
    }

    fun addUser(firstName:String,lastName:String,age:Int){
        val id = users.maxOf {  it.id }+1
        val user = User(id, firstName, lastName, age)
        _users.add(user)
        notifyObserver()
    }

    fun save(){
        file.writeText(Json.Default.encodeToString(_users))
    }



    companion object {
        private val lock = Any()
        private var instance: UserRepository2? = null

        fun getInstance(password: String): UserRepository2 {
            val correctPassword = File("password.txt").readText().trim()

            if (correctPassword != password) throw IllegalArgumentException("Wrong password")

            instance?.let { return it }

            synchronized(lock) {
                instance?.let { return it }

                return UserRepository2().also {
                    instance = it
                }
            }
        }
    }

}