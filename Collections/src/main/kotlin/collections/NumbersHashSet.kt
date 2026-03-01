package org.example.collections

import kotlin.math.abs
import org.example.collections.NumbersHashSet.Node

class NumbersHashSet : NumbersMutableSet {

    private var elements = arrayOfNulls<Node>(INITIAL_CAPACITY)


    override var size: Int = 0
        private set

    override fun add(number: Int): Boolean {

        if (size>=elements.size*LOAD_FACTOR){
            increaseArray()
        }

        return add(number,elements).also {added->
            if (added){
                size++
            }
        }
    }

    private fun add(number: Int,array: Array<Node?>): Boolean {
        val newElement = Node(number,null)
        val position = getElementPosition(number,array)
        var existElement = array[position]

        if (existElement == null){
            array[position] = newElement
            return true
        }else{
            while (true){
                if (existElement?.item == number)  {
                    return false
                }else{
                    if (existElement?.next == null){
                        existElement?.next = newElement
                        return true
                    }else{
                        existElement = existElement.next
                    }
                }
            }
        }
    }



    private fun getElementPosition(element:Int,arrayList: Array<Node?>):Int{
        return abs(element % arrayList.size)
    }

    private fun increaseArray(){
        val newArray = arrayOfNulls<Node>(elements.size*2)

        for (node in elements){
            var currentElement = node
            while (currentElement != null){
                add(currentElement.item,newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    override fun remove(number: Int) {
        TODO("Not yet implemented")
    }

    override fun contains(number: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    class Node(
        val item: Int,
        var next:Node? = null
    )

    companion object{
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}