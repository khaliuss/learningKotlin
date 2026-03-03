package collections

import org.example.practic.p1.Item
import org.example.practic.p1.PracticeHashSet
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class
NumbersHashSetTest {

    private val numbers = PracticeHashSet<Item>()

    @Test
    fun `When added 100 elements Then size 100`() {
        repeat(100) {
            numbers.add(Item(it))
        }
        assertEquals(100, numbers.size)
    }

    @Test
    fun `When added 10 similar elements Then size 1`() {
        repeat(10) {
            numbers.add(Item(0))
        }
        assertEquals(1, numbers.size)
    }

    @Test
    fun `When adding is succeed Then method return true`() {
        assertTrue { numbers.add(Item(0)) }
    }

    @Test
    fun `When adding is failed Then method return false`() {
        numbers.add(Item(0))
        assertFalse { numbers.add(Item(0)) }
    }

    @Test
    fun `When element present in set Then method returns true`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        assertTrue { numbers.contains(Item(9)) }
    }

    @Test
    fun `When element doesn't present in set Then method returns false`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        assertFalse { numbers.contains(Item(10)) }
    }

    @Test
    fun `When element removed Then size is decreased`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        numbers.remove(Item(0))
        assertEquals(9, numbers.size)
    }

    @Test
    fun `When element removed Then contains return false`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        numbers.remove(Item(0))
        assertFalse { numbers.contains(Item(0)) }
    }

    @Test
    fun `When set is cleared Then size return 0`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        numbers.clear()
        assertEquals(0, numbers.size)
    }

    @Test
    fun `When set is cleared Then all elements is absent`() {
        repeat(10) {
            numbers.add(Item(it))
        }
        numbers.clear()
        repeat(10) {
            assertFalse(numbers.contains(Item(it)))
        }
    }


}