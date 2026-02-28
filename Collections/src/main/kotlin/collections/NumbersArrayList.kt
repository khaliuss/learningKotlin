package org.example.collections

class NumbersArrayList : NumbersMutableList {

    var arrayList = arrayOfNulls<Int>(10)

    override var size: Int = 0
        private set

    override fun add(number: Int) {
        isNeedExpand()
        arrayList[size] = number
        size++
    }

    override fun plus(number: Int) {
        add(number)
    }



    fun isNeedExpand(){
        if (size == arrayList.size){
            val newArray = arrayOfNulls<Int>(arrayList.size*2)
            for (index in arrayList.indices){
                newArray[index] = arrayList[index]
            }
            arrayList = newArray
        }
    }

    override fun add(index: Int, number: Int) {
        isNeedExpand()

        for (i in size downTo index+1){
            arrayList[i] = arrayList[i-1]
        }
        arrayList[index] = number
        size++
    }

    override fun removeAt(index: Int) {
        for (i in index until size-1){
            arrayList[i] = arrayList[i+1]
        }

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

    override fun minus(number: Int) {
        remove(number)
    }

    override fun contains(number: Int): Boolean {
        for(i in 0 until size){
            if (arrayList[i] == number){
                removeAt(i)
                return true
            }
        }
        return false
    }

    override fun get(index: Int): Int {
        return arrayList[index]!!
    }



    override fun clear() {
        arrayList = arrayOfNulls(10)
        size=0
    }
}