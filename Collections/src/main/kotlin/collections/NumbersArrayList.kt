package org.example.collections

class NumbersArrayList : NumbersMutableList {

    companion object{
        private const val INITIAL_CAPACITY = 10
    }

    var arrayList = arrayOfNulls<Int>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        isNeedExpand()
        arrayList[size] = number
        size++
    }

    fun isNeedExpand(){
        if (size == arrayList.size){
            val newArray = arrayOfNulls<Int>(arrayList.size*2)
            System.arraycopy(arrayList,0,newArray,0,size)
            arrayList = newArray
        }
    }

    override fun add(index: Int, number: Int) {
        isNeedExpand()
        checkIndexForAdd(index)
        System.arraycopy(arrayList,index,arrayList,index+1,size-index)
        arrayList[index] = number
        size++
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(arrayList,index+1,arrayList,index,size-index-1)

        size--
        arrayList[size] = null
    }

    override fun remove(number: Int) {
        for(i in 0 until size){
            if (arrayList[i] == number){
                removeAt(i)
                return
            }
        }
    }


    override fun contains(number: Int): Boolean {
        for(i in 0 until size){
            if (arrayList[i] == number){
                return true
            }
        }
        return false
    }

    private fun checkIndex(index:Int) {
        if (index < 0 || index >= size){
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun checkIndexForAdd(index:Int) {
        if (index < 0 || index > size){
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return arrayList[index]!!
    }



    override fun clear() {
        arrayList = arrayOfNulls(INITIAL_CAPACITY)
        size=0
    }
}