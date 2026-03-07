package second.superviserJob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val parentJob = SupervisorJob()
private val handlerException = CoroutineExceptionHandler { _, _ ->
    println("Exception caught")
}
private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher + parentJob + handlerException)

fun main() {
    scope.launch {
        longOperation(3000,1)
        error("")
    }

    scope.launch {
        longOperation(4000,2)
    }
}

private suspend fun longOperation(timeMillis: Long,number: Number){
    println("Started: $number")
    delay(timeMillis)
    println("Finished: $number")
}