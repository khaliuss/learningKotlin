package org.example.practic.p1

class PracticeArrayList : PracticeMutableList {

    companion object{
        const val INITIAL_CAPACITY = 10
    }

    private var array = arrayOfNulls<Int>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set


    override fun add(number: Int) {
        increaseArray()
        array[size] = number
        size++
    }



    override fun add(index: Int, number: Int) {
        checkIndexForAdd(index)
        increaseArray()
        System.arraycopy(array,index,array,index+1,size-index)
        array[index] = number
        size++
    }

    override fun remove(number: Int) {
        for (index in 0 until size){
            if (array[index] == number){
                removeAt(index)
                return
            }
        }
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(array,index+1,array,index,size-index-1)
        size--
        array[size] = null
    }

    override fun contains(number: Int): Boolean {
        for (index in 0 until size){
            if (array[index] == number){
                return true
            }
        }

        return false
    }


    override fun get(index: Int): Int {
        checkIndex(index)
        return array[index]!!
    }

    override fun clear() {
        array = arrayOfNulls(INITIAL_CAPACITY)
        size=0
    }


    private fun increaseArray(){
        if (size >= array.size){
            val newArray = arrayOfNulls<Int>(array.size*2)
            System.arraycopy(array,0,newArray,0,size)
            array = newArray
        }
    }

    private fun checkIndex(index:Int){
        if (index !in 0..<size){
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun checkIndexForAdd(index:Int){
        if (index !in 0..size){
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }
}