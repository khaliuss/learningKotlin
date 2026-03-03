package org.example.collections

interface MyCollection<T> : MutableIterable<T>{

    val size: Int

    fun add(element: T): Boolean

    fun remove(element: T)

    fun contains(element: T): Boolean

    fun clear()



}