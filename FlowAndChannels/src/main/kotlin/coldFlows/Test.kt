package org.example.coldFlows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher= Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope= CoroutineScope(dispatcher)


/*
*******                ------>  Особенности холодных Flow  <---------
*******
*******        1. Flow осуществляют emit данных только при наличии подписчиков
*******        2. Холодные flow завершают работу, если подписчику данные не нужны
*******        3. Flow завершает работу, если все данные прошли заэмичены
*******        4. При каждой подписке создается новый поток данных
* */

fun main() {

    val flow : Flow<Int> = Repository.timer

    scope.launch {
//        flow.collect {
//            println(it)
//        }
    }

    scope. launch {
        flow//.take(3)
            .collect {
            println(it)
        }
        println("Finished")
    }

}

