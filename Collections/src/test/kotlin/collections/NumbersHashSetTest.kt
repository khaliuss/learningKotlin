package collections

import org.example.collections.NumbersHashSet
import org.example.collections.NumbersMutableSet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class
NumbersHashSetTest {

    @ParameterizedTest
    @MethodSource("mutableHashSetSource")
    fun `When add 1 element not exist then return true`(list: NumbersMutableSet) {
        Assertions.assertTrue { list.add(4) }
    }

    @ParameterizedTest
    @MethodSource("mutableHashSetSource")
    fun `When add 1 element existed then return false`(list: NumbersMutableSet) {
        list.add(4)
        Assertions.assertFalse { list.add(4) }
    }

    @ParameterizedTest
    @MethodSource("mutableHashSetSource")
    fun `When add 1 element with collision true`(list: NumbersMutableSet) {
        list.add(4)
        Assertions.assertTrue { list.add(20) }

    }

    @ParameterizedTest
    @MethodSource("mutableHashSetSource")
    fun `When add 1 element with collision existed false`(list: NumbersMutableSet) {
        list.add(4)
        list.add(20)
        Assertions.assertFalse { list.add(20) }

    }

    companion object {
        @JvmStatic
        fun mutableHashSetSource() = listOf<NumbersMutableSet>(NumbersHashSet())
    }

}