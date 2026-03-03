package org.example.collections

interface NumbersMutableList<T> : MyCollection<T>{

    override val size: Int

    override fun add(element: T): Boolean

    fun add(index: Int, element: T)

    fun removeAt(index: Int)

    operator fun get(index: Int): T

    override fun remove(element: T)

    override fun contains(element: T): Boolean

    override fun clear()


}