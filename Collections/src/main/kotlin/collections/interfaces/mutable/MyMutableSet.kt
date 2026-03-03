package org.example.collections.interfaces.mutable

import org.example.collections.interfaces.unmutable.MySet

interface MyMutableSet<T> : MySet<T>, MyMutableCollection<T>{

    override val size: Int

    fun add(number: Int): Boolean

    fun remove(number: Int)

    fun contains(number: Int): Boolean

    override fun clear()


}