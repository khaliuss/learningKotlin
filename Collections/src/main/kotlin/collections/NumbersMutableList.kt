package org.example.collections

interface NumbersMutableList {

    val size: Int

    fun add(number: Int)

    operator fun plus(number:Int)

    operator fun minus(number:Int)

    fun add(index: Int, number: Int)

    fun removeAt(index: Int)

    operator fun get(index: Int): Int

    fun remove(number: Int)

    fun contains(number: Int): Boolean

    fun clear()


}