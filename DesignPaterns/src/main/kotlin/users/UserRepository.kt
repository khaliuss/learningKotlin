package users

import kotlinx.serialization.json.Json
import observers.MutableObservable
import observers.Observable
import java.io.File

class UserRepository private constructor(){

    private val file = File("users.json")
    private val content = file.readText().trim()


    private val userList: MutableList<User> = loadAllUsers()

    private val _users = MutableObservable(userList.toList())
    val users: Observable<List<User>>
        get() =  _users

    private fun loadAllUsers(): MutableList<User> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        userList.removeIf { it.id == id }
        _users.currentValue = userList
    }

    fun addUser(firstName:String,lastName:String,age:Int){
        val id = userList.maxOf {  it.id }+1
        val user = User(id,firstName,lastName,age)
        userList.add(user)
        _users.currentValue = userList
    }

    fun save(){
        file.writeText(Json.encodeToString(userList))
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