package org.example.calculator

class LoggingCalculator : Calculator {

    override fun sum(a: Int, b: Int): Int {
        val result: Int = a + b
        println("Operation: sum($a, $b). Result: $result")
        return result
    }

    override fun multiplication(a: Int, b: Int): Int {
        val result: Int = a * b
        println("Operation: multiplication($a, $b). Result: $result")
        return result
    }

    override fun division(a: Int, b: Int): Double {
        val result: Double = a.toDouble() / b
        println("Operation: division($a, $b). Result: $result")
        return result
    }

    override fun subtraction(a: Int, b: Int): Int {
        val result: Int = a - b
        println("Operation: subtraction($a, $b). Result: $result")
        return result
    }

}