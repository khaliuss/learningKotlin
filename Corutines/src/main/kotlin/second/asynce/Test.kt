package second.asynce

import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    println("Exception caught")
}
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher )

fun main() {
    val def = scope.async {
        method()
    }

    scope.launch {
        runCatching {
            def.await()
        }.apply {
            if (isFailure){
                println("Exception caught")
            }
        }

    }

    scope.launch {
        method2()
    }
}

suspend fun method() {
    delay(3000)
    error("")
}

suspend fun method2() {
    delay(5000)
    println("Method 2 finished")
}