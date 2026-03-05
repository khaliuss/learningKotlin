package superVisorJob

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

//With SupervisorJob() -->
//Failure one 1 child does not affect on other children work
//Like it occur with Job()
private val parentJob= SupervisorJob()
val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    println("Exception caught")
}
val scope = CoroutineScope(parentJob + exceptionHandler + dispatcher)

fun main() {

    scope.launch {
        longOperation(3000,1)
        error("")
    }

    scope.launch {
        longOperation(4000,2)
    }

}

private suspend fun longOperation(timeMillis: Long,number:Int){
    println("Started: $number")
    delay(timeMillis)
    println("Finished: $number")
}