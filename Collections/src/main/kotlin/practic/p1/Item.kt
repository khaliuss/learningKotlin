package org.example.practic.p1

class Item(val value :Int ) : Comparable<Item> {

    override fun compareTo(other: Item): Int {
        return if (value > other.value) return 1
        else if(value < other.value) return -1
        else 0
    }
}
