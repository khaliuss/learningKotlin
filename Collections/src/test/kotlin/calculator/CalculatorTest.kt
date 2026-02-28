package calculator

import org.example.calculator.Calculator
import org.example.calculator.LoggingCalculator
import org.example.calculator.SimpleCalculator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test

class CalculatorTest {

    private val calculator = SimpleCalculator()

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 5 Add To 10 Then Result 15`(calculator: Calculator) {
        val result: Int = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, result)
    }


    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 50 Add To 100 Then Result 150`(calculator: Calculator) {
        val result: Int = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 5 multiply by 10 Then result 50`(calculator: Calculator) {
        val result: Int = calculator.multiplication(5, 10)
        val expected = 50
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 5 multiply by 0 Then result 50`(calculator: Calculator) {
        val result: Int = calculator.multiplication(5, 0)
        val expected = 0
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 10 divide by 2 Then result 5`(calculator: Calculator) {
        val result: Double = calculator.division(10, 2)
        val expected = 5.0
        assertEquals(expected, result, 0.001)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When O divide by 2 Then result 0`(calculator: Calculator) {
        val result: Double = calculator.division(0, 2)
        val expected = 0.0
        assertEquals(expected, result, 0.001)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When subtract 3 from 10 Then result 7`(calculator: Calculator) {
        val result: Int = calculator.subtraction(10, 3)
        val expected = 7
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When subtract 10 from 3 Then result -7`(calculator: Calculator) {
        val result: Int = calculator.subtraction(3, 10)
        val expected = -7
        assertEquals(expected, result)
    }

    companion object{
        @JvmStatic
        fun calculatorSource() = listOf(SimpleCalculator(), LoggingCalculator())

    }


}