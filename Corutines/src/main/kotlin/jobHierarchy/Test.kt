package org.example.jobHierarchy

import kotlinx.coroutines.*
import java.util.concurrent.Executors

//Demon threads is threads that stop working when
//main thread finished their works

//*All dispatchers is a demon threads

private val dispatcherIO = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val parent = Job()
private val scope = CoroutineScope(dispatcherIO + parent)

fun main() {

    /*var job2: Job? = null
    val job = scope.launch {
        launch {
            printNumber(1)
        }
        job2 = launch {
            printNumber(2)
        }
    }

    Thread.sleep(6000)
    job2?.cancel()*/

    scope.launch {
        printNumber(1)
    }
    scope.launch {
        printNumber(2)
    }
    Thread.sleep(6000)
    parent.cancel()

    println("Hello")
}

private suspend fun printNumber(number: Int) {
    while (true) {
        println(number)
        delay(1000)
    }
}