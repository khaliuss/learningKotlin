package org.example.practic.p1

class Item(val value :Int ){

    override fun hashCode(): Int {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Item) return false
        return value == other.value
    }
}
