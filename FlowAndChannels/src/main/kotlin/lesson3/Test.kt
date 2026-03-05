package org.example.lesson3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private var lastIndex = 0

private suspend fun loadNext():List<String>{
    delay(3000)
    return (lastIndex..<(lastIndex+10))
        .map { "video: $it" }
        .also {
            lastIndex+=10
            println("Loaded: ${it.joinToString()}")
        }
}

private suspend fun scroll(video: List<String>) {
    delay(video.size * 100L)
    println("Scrolled: ${video.joinToString()}")
}

val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
val scope = CoroutineScope(dispatcher)

fun main() {

    //val numbers = mutableListOf<Int>(1,2,3,3,2,1).asFlow() it's same as we call like this --> flowOf(1,2,3,3,2,1)

    //Difference between flow and sequence:
    //flow can work with coroutines(suspend functions)

    scope.launch {
        flow {
            repeat(10){
                val nextData = loadNext()
                emit(nextData)
            }
        }.collect {
            scroll(it)
        }
    }

}