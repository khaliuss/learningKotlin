package multithreading

import kotlin.concurrent.thread

fun main() {

    // 1
//    val secondThread = Thread(object : Runnable{
//        override fun run() {
//            repeat(100_000){
//                print("0")
//            }
//        }
//
//    })
//    secondThread.start()

    // 2
//    Thread {
//        repeat(100_000) {
//            print("0")
//        }
//    }.start()

    // 3
    thread {
        repeat(100_000) {
            print("0")
            Thread.sleep(1000)
        }
    }


    repeat(100_000){
        print("*")
    }



}