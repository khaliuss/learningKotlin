package org.example.collections

class NumbersArrayList<T> : NumbersMutableList<T>, Iterable<T> {

    companion object {
        private const val INITIAL_CAPACITY = 10
    }

    var arrayList = arrayOfNulls<Any?>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        isNeedExpand()
        arrayList[size] = element
        size++
        return true
    }

    fun isNeedExpand() {
        if (size == arrayList.size) {
            val newArray = arrayOfNulls<Any?>(arrayList.size * 2)
            System.arraycopy(arrayList, 0, newArray, 0, size)
            arrayList = newArray
        }
    }

    override fun add(index: Int, element: T) {
        isNeedExpand()
        checkIndexForAdd(index)
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index)
        arrayList[index] = element
        size++
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1)

        size--
        arrayList[size] = null
    }

    override fun remove(element: T) {
        for (i in 0 until size) {
            if (arrayList[i] == element) {
                removeAt(i)
                return
            }
        }
    }


    override fun contains(element: T): Boolean {
        for (i in 0 until size) {
            if (arrayList[i] == element) {
                return true
            }
        }
        return false
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

    override fun get(index: Int): T {
        checkIndex(index)
        return arrayList[index] as T
    }


    override fun clear() {
        arrayList = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun iterator(): MutableIterator<T> {

        return object : MutableIterator<T> {

            private var nextIndex = 0

            override fun next(): T {
                return arrayList[nextIndex++] as T
            }

            override fun hasNext(): Boolean {
                return nextIndex < size
            }

            override fun remove() {
                TODO("Not yet implemented")
            }

        }
    }
}