package org.example.coldFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

object Repository2 {

    val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    val scope = CoroutineScope(dispatcher)

    //Method 1 Cold Flow to --->  Hot Flow
    /*val timer: Flow<Int> = MutableSharedFlow<Int>().apply {
        scope.launch {
            getTimerFlow().collect {
                emit(it)
            }
        }
    }.asSharedFlow()*/

    //Method 2 Cold Flow to --->  Hot Flow
    val timer = getTimerFlow().shareIn(
        scope = scope,
        started = SharingStarted.Eagerly
    )


    private fun getTimerFlow(): Flow<Int> {
        return flow {
            var seconds = 0
            while (true) {
                println("Emitted: $seconds")
                emit(seconds++)
                delay(1000)
            }
        }
    }
}