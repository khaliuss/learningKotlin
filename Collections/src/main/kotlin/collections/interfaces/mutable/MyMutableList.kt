package org.example.collections.interfaces.mutable

import org.example.collections.interfaces.unmutable.MyList

interface MyMutableList<T> : MyList<T>, MyMutableCollection<T>{

    override val size: Int

    override fun add(element: T): Boolean

    fun add(index: Int, element: T)

    fun removeAt(index: Int)

    override operator fun get(index: Int): T

    override fun remove(element: T)

    override fun contains(element: T): Boolean

    override fun clear()


}