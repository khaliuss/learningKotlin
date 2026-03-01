package collections

import org.example.collections.NumbersHashSet
import org.example.collections.NumbersMutableSet
import org.example.practic.p1.PracticeHashSet
import org.example.practic.p1.PracticeMutableSet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class
NumbersHashSetTest {

    private val numbers = PracticeHashSet()

    @Test
    fun `When added 100 elements Then size 100`() {
        repeat(100) {
            numbers.add(it)
        }
        assertEquals(100, numbers.size)
    }

    @Test
    fun `When added 10 similar elements Then size 1`() {
        repeat(10) {
            numbers.add(0)
        }
        assertEquals(1, numbers.size)
    }

    @Test
    fun `When adding is succeed Then method return true`() {
        assertTrue { numbers.add(0) }
    }

    @Test
    fun `When adding is failed Then method return false`() {
        numbers.add(0)
        assertFalse { numbers.add(0) }
    }

    @Test
    fun `When element present in set Then method returns true`() {
        repeat(10) {
            numbers.add(it)
        }
        assertTrue { numbers.contains(9) }
    }

    @Test
    fun `When element doesn't present in set Then method returns false`() {
        repeat(10) {
            numbers.add(it)
        }
        assertFalse { numbers.contains(10) }
    }

    @Test
    fun `When element removed Then size is decreased`() {
        repeat(10) {
            numbers.add(it)
        }
        numbers.remove(0)
        assertEquals(9, numbers.size)
    }

    @Test
    fun `When element removed Then contains return false`() {
        repeat(10) {
            numbers.add(it)
        }
        numbers.remove(0)
        assertFalse { numbers.contains(0) }
    }

    @Test
    fun `When set is cleared Then size return 0`() {
        repeat(10) {
            numbers.add(it)
        }
        numbers.clear()
        assertEquals(0, numbers.size)
    }

    @Test
    fun `When set is cleared Then all elements is absent`() {
        repeat(10) {
            numbers.add(it)
        }
        numbers.clear()
        repeat(10) {
            assertFalse(numbers.contains(it))
        }
    }


}