package users

import kotlinx.serialization.json.Json
import observers.Observer
import java.io.File

class UserRepository private constructor() {

    private val file = File("users.json")
    private val content = file.readText().trim()


    private val _users: MutableList<User> = loadAllUsers()
    val users
        get() = _users.toList()

    val observers = mutableListOf<Observer<List<User>>>()

    fun notifyObserver(){
        for (display in observers){
            display.onChange(users)
        }
    }

    fun registerObserver(observer: Observer<List<User>>){
        observers.add(observer)
        observer.onChange(users)
    }

    private fun loadAllUsers(): MutableList<User> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        _users.removeIf { it.id == id }
        notifyObserver()
    }

    fun addUser(firstName:String,lastName:String,age:Int){
        val id = users.maxOf {  it.id }+1
        val user = User(id,firstName,lastName,age)
        _users.add(user)
        notifyObserver()
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