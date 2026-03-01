package org.example.practic.p1

interface PracticeMutableList {

    val size:Int


    fun add(number:Int)
    fun add(index:Int,number:Int)

    fun remove(number:Int)
    fun removeAt(index:Int)

    fun contains(number:Int): Boolean


    operator fun get(index:Int):Int

    fun clear()


}