package multithreading

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    var clock = true

    print("Enter number from 0 to 1_000_000_000: ")
    val toGuess = readln().toInt()

    thread {
        while (true) {
            val guess = Random.nextInt()
            if (toGuess == guess) {
                println("I win. Your number is: $guess")
                clock = false
                break
            }
        }
    }

    thread {
        var count = 1
        while(clock){
            println(count++)
            Thread.sleep(1000)
        }
    }





}