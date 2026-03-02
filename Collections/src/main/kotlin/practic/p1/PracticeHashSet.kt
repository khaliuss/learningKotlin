package org.example.practic.p1

import javax.swing.text.Position
import kotlin.math.abs

class PracticeHashSet<T> : PracticeMutableSet<T> , Iterable<T>{

    companion object {
        const val INITIAL_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    var arrayList = arrayOfNulls<Node<T>>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        if (size >= arrayList.size * LOAD_FACTOR) {
            expandArray()
        }

        return add(element, arrayList).also {
            if (it) {
                size++
            }
        }
    }

    private fun add(element: T, newArray: Array<Node<T>?>): Boolean {

        val newNode = Node(element)
        val position = getPosition(element, newArray.size)
        var existElement = newArray[position]

        if (existElement == null) {
            newArray[position] = newNode
            return true
        } else {
            while (true) {
                if (existElement?.item == element) {
                    return false
                } else {
                    if (existElement?.next == null) {
                        existElement?.next = newNode
                        return true
                    } else {
                        existElement = existElement.next
                    }
                }


            }
        }
    }

    //andrew code
    override fun remove(element: T) {

        val position: Int = getPosition(element, arrayList.size)
        val existedElement :Node<T> = arrayList[position] ?: return
        if (existedElement.item == element) {
            arrayList[position] = existedElement.next
            size--
            return
        }
        var before: Node<T>? = existedElement
        while (before?.next != null) {
            val removingElement : Node<T>? = before.next
            if (removingElement?.item == element) {
                before.next = removingElement.next
                size--
                return
            } else {
                before = before.next
            }
        }
    }

    //my code
    /*override fun remove(element: T) {
        val position = getPosition(element, arrayList.size)

        if (arrayList[position]?.item == element) {
            if (arrayList[position]?.next == null){
                arrayList[position] = null
                size--
                return
            }else{
                arrayList[position] = arrayList[position]?.next
                size--
                return
            }
        }

        var node = arrayList[position]

        while (true) {
            if (node?.next?.item == element){
                node.next = node.next?.next
                size--
                return
            }else{
                node = node?.next
            }
        }
    }*/

    override fun contains(element: T): Boolean {
        val position = getPosition(element, arrayList.size)
        var node = arrayList[position]

        while (node != null) {
            if (node.item == element) {
                return true
            } else {
                node = node.next
            }
        }
        return false
    }

    override fun clear() {
        arrayList = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    private fun expandArray() {
        val newArray = arrayOfNulls<Node<T>>(arrayList.size * 2)

        for (node in arrayList) {

            var currentElement = node

            while (currentElement != null) {
                add(currentElement.item, newArray)
                currentElement = currentElement.next
            }

        }

        arrayList = newArray
    }

    private fun getPosition(item: T, arraySize: Int): Int {
        return abs(item.hashCode() % arraySize)
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T>{

            private var nodeIndex = 0
            private var nextNode = arrayList[nodeIndex]
            private var nextIndex = 0

            override fun next(): T {
                while (nextNode == null){
                    nextNode = arrayList[++nodeIndex]
                }
                return nextNode?.item!!.also {
                    nextIndex++
                    nextNode = nextNode?.next
                }

            }

            override fun hasNext(): Boolean {
                return nextIndex<size
            }

        }
    }

    data class Node<T>(
        var item: T,
        var next: Node<T>? = null
    )

}