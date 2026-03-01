package org.example.practic.p1

interface PracticeMutableSet {

    val size:Int


    fun add(number:Int): Boolean


    fun remove(number:Int)


    fun contains(number:Int): Boolean


    fun clear()

}