package shelter

import kotlinx.serialization.json.Json
import observers.Observable
import observers.Observer
import java.io.File
import java.lang.IllegalArgumentException
import kotlin.collections.maxOf

class ShelterRepository private constructor() : Observable<List<Dog>> {

    private val file = File("dogs.json")
    private val content = file.readText().trim()


    private val _dogs: MutableList<Dog> = loadDogs()
    override val currentValue: List<Dog>
        get() = _dogs.toList()


    private val _observers = mutableListOf<Observer<List<Dog>>>()
    override val observers: List<Observer<List<Dog>>>
        get() = _observers.toList()


    override fun registerObserver(observer: Observer<List<Dog>>){
        _observers.add(observer)
        observer.onChange(currentValue)
    }

    override fun unregisterObserver(observer: Observer<List<Dog>>) {
        _observers.remove(observer)
    }

    private fun loadDogs(): MutableList<Dog> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        _dogs.removeIf { it.id == id }
        notifyObservers()
    }

    fun addUser(firstName:String, lastName:String, weight: Double){
        val id = currentValue.maxOf{  it.id }+1
        val user = Dog(id,firstName,lastName,weight)
        _dogs.add(user)
        notifyObservers()
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