package second.cencallation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {

    val job = scope.launch {
        timer()
    }

    Thread.sleep(3000)
    job.cancel()

}

private suspend fun timer(){
    var seconds = 0;
    while (true){
        try {
            println("Some")
            if (!coroutineContext.isActive) throw CancellationException()
                println(seconds++)
                delay(1000)
        } catch (e: CancellationException){
            throw e
        } catch (e: Exception) {

        }
    }
}