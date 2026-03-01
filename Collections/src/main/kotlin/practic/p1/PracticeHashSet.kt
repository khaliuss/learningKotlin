package org.example.practic.p1

import javax.swing.text.Position
import kotlin.math.abs

class PracticeHashSet : PracticeMutableSet {

    companion object {
        const val INITIAL_CAPACITY = 16
        const val LOAD_FACTOR = 0.75
    }

    var arrayList = arrayOfNulls<Node>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(number: Int): Boolean {
        if (size >= arrayList.size * LOAD_FACTOR) {
            expandArray()
        }

        return add(number, arrayList).also {
            if (it) {
                size++
            }
        }
    }

    private fun add(number: Int, newArray: Array<Node?>): Boolean {

        val newNode = Node(number)
        val position = getPosition(number, newArray.size)
        var existElement = newArray[position]

        if (existElement == null) {
            newArray[position] = newNode
            return true
        } else {
            while (true) {
                if (existElement?.item == number) {
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
    /*override fun remove(number: Int) {

        val position: Int = getPosition(number, arrayList.size)
        val existedElement :Node = arrayList[position] ?: return
        if (existedElement.item == number) {
            arrayList[position] = existedElement.next
            size--
            return
        }
        var before: Node? = existedElement
        while (before?.next != null) {
            val removingElement : Node? = before.next
            if (removingElement?.item == number) {
                before.next = removingElement.next
                size--
                return
            } else {
                before = before.next
            }
        }
    }*/

    //my code
    override fun remove(number: Int) {
        val position = getPosition(number, arrayList.size)

        if (arrayList[position]?.item == number) {
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
            if (node?.next?.item == number){
                node.next = node.next?.next
                size--
                return
            }else{
                node = node?.next
            }
        }
    }

    override fun contains(number: Int): Boolean {
        val position = getPosition(number, arrayList.size)
        var node = arrayList[position]

        while (node != null) {
            if (node.item == number) {
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
        val newArray = arrayOfNulls<Node>(arrayList.size * 2)

        for (node in arrayList) {

            var currentElement = node

            while (currentElement != null) {
                add(currentElement.item, newArray)
                currentElement = currentElement.next
            }

        }

        arrayList = newArray
    }

    private fun getPosition(item: Int, arraySize: Int): Int {
        return abs(item % arraySize)
    }

    data class Node(
        var item: Int,
        var next: Node? = null
    )

}