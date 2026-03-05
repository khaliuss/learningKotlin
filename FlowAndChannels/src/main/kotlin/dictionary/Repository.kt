package org.example.dictionary

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

object Repository {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/dictionary?word="
    private const val API_KEY = "634Zi3p46C6WG2OzRqIwZEXM7uZ3r53PkOXbXtiA"
    private const val HEADER_KEY = "X-Api-Key"

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun loadingDefinition(word:String): List<String>{
        return withContext(Dispatchers.IO){
            var connection:HttpURLConnection? =  null
            try {
                val urlString = BASE_URL+word
                val url = URL(urlString)
                connection = (url.openConnection() as HttpURLConnection).apply {
                    addRequestProperty(HEADER_KEY,API_KEY)
                }

                val response = connection.getInputStream().bufferedReader().readLine()

                val definition = json.decodeFromString<Definition>(response)
                definition.mapDefinitionToList()
            } catch (e: Exception) {
                println(e)
                listOf()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun Definition.mapDefinitionToList(): List<String>{
        return this.definition.split(Regex("\\d. ")).map { it.trim() }.filter { it.isNotEmpty() }
    }
}
