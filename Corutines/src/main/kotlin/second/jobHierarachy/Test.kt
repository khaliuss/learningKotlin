package second.jobHierarachy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val parentJob = Job()
private val scope = CoroutineScope(dispatcher + parentJob)

fun main() {

    scope.launch {
        printNumber(1)
    }

    scope.launch {
        printNumber(2)
    }

    Thread.sleep(3000)
    parentJob.cancel()
}

private suspend fun printNumber(number: Int) {
    while (true) {
        println("Number: $number")
        delay(1000)
    }
}