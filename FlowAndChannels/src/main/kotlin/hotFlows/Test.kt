package org.example.hotFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher= Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope= CoroutineScope(dispatcher)


/*
*******                ------>  Горячий Flow  <---------
*******
*******        1. Flow осуществляет emit данных независимо от наличия подписчиков
*******        2. Все подписчики получают одни и те же элементы
*******        3. Если подписчикам не нужны данные, то flow не завершает свою работу
*******        4. Не завершается никогда
*******
* */

fun main() {

    val flow : Flow<Int> = Repository.timer

    scope.launch {
//        flow.collect {
//            println(it)
//        }
    }

    scope. launch {
        flow.take(1)
            .collect {
            println(it)
        }
        println("Finished")
    }

}

