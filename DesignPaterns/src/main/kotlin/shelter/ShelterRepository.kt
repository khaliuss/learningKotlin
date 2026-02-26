package shelter

import kotlinx.serialization.json.Json
import observers.Observer
import users.User
import java.io.File
import java.lang.IllegalArgumentException
import kotlin.collections.maxOf

class ShelterRepository private constructor() {

    private val file = File("dogs.json")
    private val content = file.readText().trim()

    private val _dogs: MutableList<Dog> = loadDogs()
    val dogs
        get() = _dogs.toList()

    val displayList = mutableListOf<Observer<List<Dog>>>()

    fun registerObserver(observer: Observer<List<Dog>>){
        displayList.add(observer)
        observer.onChange(dogs)
    }

    fun notifyObserver(){
        for (display in displayList){
            display.onChange(_dogs)
        }
    }

    private fun loadDogs(): MutableList<Dog> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        _dogs.removeIf { it.id == id }
        notifyObserver()
    }

    fun addUser(firstName:String, lastName:String, weight: Double){
        val id = dogs.maxOf{  it.id }+1
        val user = Dog(id,firstName,lastName,weight)
        _dogs.add(user)
        notifyObserver()
    }

    fun save(){
        file.writeText(Json.encodeToString(_dogs))
    }

    companion object {

        private var lock = Any()
        private var instance: ShelterRepository? = null

        fun getInstance(pass: String): ShelterRepository {
            val currentPass = File("password.txt").readText().trim()
            if (currentPass != pass) throw IllegalArgumentException("Wrong password")
            instance?.let {
                return it
            }

            synchronized(lock) {
                instance?.let {
                    return it
                }

                return ShelterRepository().also {
                    instance = it
                }
            }
        }

    }

}