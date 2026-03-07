package second.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    println("Exception caught")
}
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {
    scope.launch {
        launch(exceptionHandler) {
            method()
        }
    }

    scope.launch {
        method2()
    }
}

suspend fun method(){
    delay(3000)
    error("")
}

suspend fun method2(){
    delay(5000)
    println("Method 2 finished")
}