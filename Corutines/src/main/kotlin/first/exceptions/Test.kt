package org.example.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


/*private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("Exception caught")
}*/

private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    println("Exception caught")
}
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + exceptionHandler)

fun main() {

    /*scope.launch {
        //method1
        *//*try {
            method()
        } catch (e: Exception) {
            println("Exception caught ")
        }*//*

        //method2
        *//*runCatching {
            method()
        }.onSuccess {
            println("Success")
        }.onFailure {
                println("Failure")
            }*//*

    }*/

    //*method 3
    scope.launch {
        method()
    }

}

suspend fun method() {
    delay(2000)
    error("")
}