package shelter

import kotlinx.serialization.json.Json
import java.io.File
import java.lang.IllegalArgumentException

class ShelterRepository private constructor(){

    private val file = File("dogs.json")
    private  val content = file.readText().trim()

    private val _dogs: MutableList<Dog> = loadDogs()
    val dogs
        get() = _dogs.toList()

    private fun loadDogs(): MutableList<Dog>{
        return Json.decodeFromString(content)
    }

    companion object{

        private var instance: ShelterRepository? = null

        fun getInstance(pass: String): ShelterRepository{
            val currentPass = File("password.txt").readText().trim()
            if (currentPass != pass) throw IllegalArgumentException("Wrong password")
            if (instance == null){
                instance = ShelterRepository()
            }
            return instance!!;
        }

    }

}