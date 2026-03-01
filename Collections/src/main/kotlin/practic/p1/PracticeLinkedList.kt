package org.example.practic.p1

class PracticeLinkedList : PracticeMutableList {

    override var size: Int = 0
        private set

    var first: Node? = null
    var last: Node? = null

    override fun add(number: Int) {
        val newNode = Node(number)

        if (size == 0) {
            first = newNode
            last = newNode
            size++
            return
        }

        last?.next = newNode
        last = newNode
        size++
        return
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdd(index)

        if (index == size) {
            add(number)
            return
        }

        if (index == 0) {
            val newNode = Node(number, first)
            first = newNode
            size++
            return
        }

        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(number, after)
        before.next = newNode
        size++
    }

    override fun remove(number: Int) {
        if (first?.item == number) {
            removeAt(0)
            return
        }

        /*if (last?.item == number){
            removeAt(size-1)
            return
        }*/

        var before = first

        repeat(size) {

            val node = before?.next

            if (node?.item == number) {
                val after = node.next
                before.next = after
                if (after == null) {
                    last = before
                }
                size--
                return

            } else {
                before = before?.next
            }
        }
    }

    override fun removeAt(index: Int) {
        checkIndex(index)


        if (index == 0 && size == 1) {
            clear()
        }

        if (index == 0) {
            first = first?.next
            size--
            return
        }

        if (index == size) {
            val beforeDeletableItem = getNode(index - 1)
            beforeDeletableItem.next = null
            last = beforeDeletableItem
            size--
            return
        }

        val before = getNode(index - 1)
        val delete = before.next
        val after = delete?.next
        before.next = after
        if (after == null) {
            last = before
        }
        size--

    }

    override fun contains(number: Int): Boolean {
        var node = first
        repeat(size) {
            if (node?.item == number) {
                return true
            } else {
                node = node?.next
            }
        }
        return false
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).item
    }

    override fun clear() {
        first = null
        last = null
        size = 0
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

    data class Node(
        val item: Int,
        var next: Node? = null
    )
}