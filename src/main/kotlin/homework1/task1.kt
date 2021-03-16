package homework1

import kotlin.system.exitProcess

fun readNumber() : Int
{
    println("Enter an integer non-negative number:")
    val number = readLine()?.toIntOrNull()
    if (number == null || number < 0) {
        println("It's not a non-negative integer")
        exitProcess(-1)
    }
    return number
}

fun computeFactorialRecursive(number: Int) : Int
{
    if (number == 0)
        return 1
    return computeFactorialRecursive(number - 1) * number
}

fun computeFactorialIterative(number: Int) : Int
{
    var factorial = 1
    for (i in 2..number)
        factorial *= i
    return factorial
}

fun main()
{
    val number = readNumber()
    println("FactorialRecursive: ${computeFactorialRecursive(number)}")
    println("FactorialIterative: ${computeFactorialIterative(number)}")
}