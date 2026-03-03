package org.example.collections

interface NumbersMutableSet<T> : MyCollection<T>{

    override val size: Int

    fun add(number: Int): Boolean

    fun remove(number: Int)

    fun contains(number: Int): Boolean

    override fun clear()


}