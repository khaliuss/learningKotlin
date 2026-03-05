package cancellation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.coroutines.cancellation.CancellationException

val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
val scope = CoroutineScope(dispatcher)

fun main() {

    val job = scope.launch {
        timer()
    }

    Thread.sleep(3000)
    job.cancel()

}

private suspend fun timer() {

    withContext(dispatcher){
        var seconds = 0

        while (true) {
            try {
                if (!coroutineContext.isActive) throw CancellationException() //or use ----> ensureActive()
                println(seconds++)
                delay(1000)

            }catch (e: CancellationException){
                throw e
            }
            catch (e: Exception) {

            }
        }
    }


}