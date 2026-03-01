package org.example.collections

class NumbersLinkedList : NumbersMutableList {

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        val prevLast = last
        last = Node(prevLast,number)
        if (prevLast == null){
            first = last
        }else{
            prevLast.next = last
        }
        size++
    }


    override fun add(index: Int, number: Int) {
        checkIndexForAdd(index)
        if (index == size) {
            add(number)
            return
        }

        if (index == 0) {
            val newElement = Node(null,number, first)
            first?.prev = newElement
            first = newElement
            size++
            return
        }

        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(before,number, after)
        before.next = newNode
        after?.prev = newNode
        size++
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        val node = getNode(index)
        unLink(node)

    }

    private fun unLink(node: Node){
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
        if (after == null){
            last = before
        }
        if (before == null){
            first = after
        }

        size--
    }

    override fun remove(number: Int) {
        var node = first
        repeat(size) {
            if (node?.item == number) {
                unLink(node)
                return
            }else{
                node = node?.next
            }
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).item
    }

    private fun getNode(index: Int): Node {
        checkIndex(index)
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        if (index<size/2){
            var node = first
            repeat(index) {
                node = node?.next
            }
            return node!!
        }else{
            var node = last
            repeat(size - index - 1) {
                node = node?.prev
            }
            return node!!
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
        var prev: Node? = null,
        val item: Int,
        var next: Node? = null
    )

}