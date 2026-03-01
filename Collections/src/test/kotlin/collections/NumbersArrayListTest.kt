package collections


import org.example.collections.NumbersMutableList
import org.example.practic.p1.PracticeLinkedList
import org.example.practic.p1.PracticeMutableList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersMutableListTest {

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size is 1`(list: PracticeMutableList) {
        list.add(0)
        assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list is cleared then size is O elements`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.clear()
        assertEquals(0, list.size)
    }


    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list contains element then method returns true` (list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        assertTrue(list.contains(90))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list doesn't contains element then method returns false` (list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        assertFalse(list.contains(190))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to first position then it is in first position`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.add(0, 1000)
        assertEquals(1000, list[0])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to last position then it is in last position`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.add(100, 1000)
        assertEquals(1000, list[100])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to middle position then it is in middle position`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.add(50, 1000)
        assertEquals(1000, list[50])
    }


    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 element then size is 10`(list: PracticeMutableList) {
        repeat(10) {
            list.add(it)
        }
        assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 100 element then size is 100`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        assertEquals(100, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 5th element then result is correct`(list: PracticeMutableList) {
        repeat(10) {
            list.add(it)
        }
        assertEquals(5, list[5])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 50th element then result is correct`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        assertEquals(50, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element removed then size decreased`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.removeAt(50)
        assertEquals(99, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed 50th element then next value`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }

        list.removeAt(50)
        assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 next value at this position`(list: PracticeMutableList) {
        repeat(100) {
            list.add(it)
        }
        list.remove(50)
        //when we override get operator we can get excess by [] not by .get()
        //assertEquals(51, list.get(50))
        assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoked with wrong index then exception is thrown` (list: PracticeMutableList) {
        repeat (10) {
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException> {
            list[10]
        }

    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoked with negative index then exception is thrown` (list: PracticeMutableList) {
        repeat (10) {
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException> {
            list[-10]
        }

    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method removeAt invoked with wrong index then exception is thrown` (list: PracticeMutableList) {
        repeat (10) {
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException> {
            list.removeAt(10)
        }

    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method removeAt invoked with negative index then exception is thrown` (list: PracticeMutableList) {
        repeat (10) {
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException> {
            list.removeAt(-10)
        }

    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method add invoked with wrong index then exception is thrown` (list: PracticeMutableList) {
        repeat (10) {
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException> {
            list.add(15,9)
        }

    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method add invoked with negative index then exception is thrown` (list: PracticeMutableList) {
        repeat (10) {
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException> {
            list.add(-10,9)
        }

    }



    companion object {

        @JvmStatic
        fun mutableListSource() = listOf(PracticeLinkedList())

    }

}