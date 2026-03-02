package org.example.collections

interface NumbersMutableList<T> {

    val size: Int

    fun add(element: T)

    fun add(index: Int, element: T)

    fun removeAt(index: Int)

    operator fun get(index: Int): T

    fun remove(element: T)

    fun contains(element: T): Boolean

    fun clear()


}