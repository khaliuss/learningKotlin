package org.example.test

import kotlinx.serialization.json.Json
import java.io.File

var age: Int? = 20

fun main() {

    age?.let {
        if (it >= 18) {
            "You are an adult"
        } else {
            "You will be an adult in ${18 - it} years"
        }
    }?.let {
        println(it)
    }

    age?.myLet {
        "From my let $it"
    }?.myLet {
        println(it)
    }


}

inline fun<T,R> T.myLet(block:(T)->R):R{
    return block(this)
}

