package org.example.collections

class NumbersLinkedList<T> : NumbersMutableList<T>, Iterable<T> {

    private var first: Node<T>? = null
    private var last: Node<T>? = null

    override var size: Int = 0
        private set

    override fun add(element: T) {
        val prevLast = last
        last = Node(prevLast,element)
        if (prevLast == null){
            first = last
        }else{
            prevLast.next = last
        }
        size++
    }


    override fun add(index: Int, element: T) {
        checkIndexForAdd(index)
        if (index == size) {
            add(element)
            return
        }

        if (index == 0) {
            val newElement = Node(null,element, first)
            first?.prev = newElement
            first = newElement
            size++
            return
        }

        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(before,element, after)
        before.next = newNode
        after?.prev = newNode
        size++
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        val node = getNode(index)
        unLink(node)

    }

    private fun unLink(node: Node<T>){
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

    override fun remove(element: T) {
        var node = first
        repeat(size) {
            if (node?.item == element) {
                unLink(node)
                return
            }else{
                node = node?.next
            }
        }
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return getNode(index).item as T
    }

    private fun getNode(index: Int): Node<T> {
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

    override fun contains(element: T): Boolean {
        var node = first
        repeat(size) {
            if (node?.item == element) {
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

    override fun iterator(): Iterator<T> {
        return object : Iterator<T>{

            var nextElement = first

            override fun next(): T {
                val item = nextElement?.item
                nextElement = nextElement?.next
                return item!!
            }

            override fun hasNext(): Boolean {
                return nextElement != null
            }

        }
    }

    class Node<T>(
        var prev: Node<T>? = null,
        val item: T,
        var next: Node<T>? = null
    )

}