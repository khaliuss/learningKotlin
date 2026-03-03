package org.example.collections.interfaces.mutable

import org.example.collections.interfaces.unmutable.MyCollection

interface MyMutableCollection<T> : MyCollection<T>, MutableIterable<T>{

    override val size: Int

    fun add(element: T): Boolean

    fun remove(element: T)

    override fun contains(element: T): Boolean

    fun clear()



}