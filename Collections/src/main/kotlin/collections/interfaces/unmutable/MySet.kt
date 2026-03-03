package org.example.collections.interfaces.unmutable

interface MySet<T> : MyCollection<T>{

    override val size: Int

    override fun contains(element: T): Boolean

}