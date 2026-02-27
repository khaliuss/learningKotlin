package users

import kotlinx.serialization.json.Json
import observers.Observable
import observers.Observer
import java.io.File

class UserRepository private constructor() : Observable<List<User>> {

    private val file = File("users.json")
    private val content = file.readText().trim()


    private val _users: MutableList<User> = loadAllUsers()

    override val currentValue: List<User>
        get() = _users.toList()

    private  val _observers = mutableListOf<Observer<List<User>>>()

    override val observers
        get() = _observers


    override fun registerObserver(observer: Observer<List<User>>){
        _observers.add(observer)
        observer.onChange(currentValue)
    }

    override fun unregisterObserver(observer: Observer<List<User>>) {
        _observers.remove (observer)
    }

    private fun loadAllUsers(): MutableList<User> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        _users.removeIf { it.id == id }
        notifyObservers()
    }

    fun addUser(firstName:String,lastName:String,age:Int){
        val id = currentValue.maxOf {  it.id }+1
        val user = User(id,firstName,lastName,age)
        _users.add(user)
        notifyObservers()
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