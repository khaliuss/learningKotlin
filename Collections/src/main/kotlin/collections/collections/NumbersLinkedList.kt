package org.example.collections.collections

import org.example.collections.NumbersMutableList

class NumbersLinkedList : NumbersMutableList {

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        if (size == 0) {
            val node = Node(number)
            first = node
            last = node
            size++
            return
        }
        val newElement = Node(number)
        last?.next = newElement
        last = newElement
        size++
    }


    override fun add(index: Int, number: Int) {
        checkIndexForAdd(index)
        if (index == size) {
            add(number)
            return
        }

        if (index == 0) {
            val newElement = Node(number, first)
            first = newElement
            size++
            return
        }

        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(number, after)
        before.next = newNode
        size++
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        if (index == 0 && size == 1) {
            clear()
            return
        }

        if (index == 0) {
            first = first?.next
            size--
            return
        }

        if (index == size) {
            val node = getNode(index - 1)
            node.next = null
            last = node
            size--
            return
        }

        val before = getNode(index - 1)
        val after = before.next?.next
        before.next = after
        if (after == null) {
            last = before
        }

        size--

    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).item
    }

    private fun getNode(index: Int): Node {
        checkIndex(index)
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        var node = first
        repeat(index) {
            node = node?.next
        }
        return node!!
    }

    override fun remove(number: Int) {
        if (first?.item == number) {
            removeAt(0)
            return
        }

        var before = first

        repeat(size) {

            val node = before?.next
            if (node?.item == number) {
                val after = node.next
                before?.next = after
                if (after == null) {
                    last = before
                }
                size--
                return
            }else{
                before = before?.next
            }
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun checkIndexForAdd(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    override fun contains(number: Int): Boolean {
        var node = first
        repeat(size) {
            if (node?.item == number) {
                return true
            }
            node = node?.next
        }
        return false
    }

    override fun clear() {
        first = null
        last = null
        size = 0
    }

    class Node(
        val item: Int,
        var next: Node? = null
    )

}