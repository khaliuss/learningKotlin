package org.example.practic.p1

interface PracticeMutableSet<T> {

    val size:Int


    fun add(element:T): Boolean


    fun remove(element:T)


    fun contains(element:T): Boolean


    fun clear()

}