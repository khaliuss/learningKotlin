package org.example.collections.interfaces.unmutable

interface MyCollection<T> : Iterable<T>{

    val size: Int

    fun contains(element: T): Boolean

}