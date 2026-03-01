package practic.p1

import org.example.practic.p1.PracticeArrayList
import org.example.practic.p1.PracticeMutableList
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PracticeArrayListTest {


    @ParameterizedTest
    @MethodSource("mutableList")
    fun `when add 1 element then size increase 1`(mutableList: PracticeMutableList){
        mutableList.add(1)
        assertEquals(1,mutableList().size)
    }


    @ParameterizedTest
    @MethodSource("mutableList")
    fun `when 15 added 5th position then return 15`(mutableList: PracticeMutableList){
        repeat(10){
            mutableList.add(it)
        }
        mutableList.add(5,15)
        assertEquals(15,mutableList[5])
    }

    @ParameterizedTest
    @MethodSource("mutableList")
    fun `when array contains element then return true`(mutableList: PracticeMutableList){
        mutableList.add(1)
        assertTrue(mutableList.contains(1))
    }

    @ParameterizedTest
    @MethodSource("mutableList")
    fun `when array doesn't contains element then return false`(mutableList: PracticeMutableList){
        mutableList.add(1)
        assertFalse(mutableList.contains(2))
    }

    companion object{
        @JvmStatic
        fun mutableList() = listOf<PracticeMutableList>(PracticeArrayList())
    }

}