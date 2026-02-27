package shelter

import kotlinx.serialization.json.Json
import observers.MutableObservable
import observers.Observable
import java.io.File
import java.lang.IllegalArgumentException
import kotlin.collections.maxOf

class ShelterRepository private constructor() {

    private val file = File("dogs.json")
    private val content = file.readText().trim()


    private val dogsList: MutableList<Dog> = loadDogs()

    private val _dogs = MutableObservable(dogsList.toList())
    val dogs: Observable<List<Dog>>
        get()= _dogs



    private fun loadDogs(): MutableList<Dog> {
        return Json.decodeFromString(content)
    }

    fun deleteUser(id : Int){
        dogsList.removeIf { it.id == id }
        _dogs.currentValue = dogsList
    }

    fun addUser(firstName:String, lastName:String, weight: Double){
        val id = dogsList.maxOf{  it.id }+1
        val user = Dog(id,firstName,lastName,weight)
        dogsList.add(user)
        _dogs.currentValue = dogsList
    }

    fun save(){
        file.writeText(Json.encodeToString(dogsList))
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