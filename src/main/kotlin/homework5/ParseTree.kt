package homework5

import java.io.File
import java.util.ArrayDeque

class ParseTree(private val inputFile: String) {
    private val expression: String = File(inputFile).readText()
    private var root: ParseTreeNodes? = null

    private fun splitBracketsIntoPairs(closingBracketIndex: IntArray, openingBracketIndex: IntArray) {
        val openingBrackets = ArrayDeque<Int>()
        for (i in expression.indices) {
            when (expression[i]) {
                '(' -> openingBrackets.push(i)
                ')' -> {
                    closingBracketIndex[openingBrackets.peek()] = i
                    openingBracketIndex[i] = openingBrackets.pop()
                }
            }
        }
    }

    private fun initializeOperand(
        start: Int,
        end: Int,
        closingBracketIndex: IntArray,
        openingBracketIndex: IntArray
    ): ParseTreeNodes {
        return if (expression[start] == '(') {
            buildRecursive(start, closingBracketIndex[start], closingBracketIndex, openingBracketIndex)
        } else {
            Number(expression.substring(start, end + 1).toInt())
        }
    }

    private fun buildRecursive(
        start: Int,
        end: Int,
        closingBracketIndex: IntArray,
        openingBracketIndex: IntArray
    ): ParseTreeNodes {
        val startOfFirstOperand = expression.indexOf(' ', start) + 1
        val startOfSecondOperand = if (expression[end - 1] == ')') {
            openingBracketIndex[end - 1]
        } else {
            expression.lastIndexOf(' ', end) + 1
        }

        val operand1 = initializeOperand(
            startOfFirstOperand, startOfSecondOperand - 2,
            closingBracketIndex, openingBracketIndex
        )
        val operand2 = initializeOperand(startOfSecondOperand, end - 1, closingBracketIndex, openingBracketIndex)

        return when (expression[start + 1]) {
            '+' -> AdditionNode(operand1, operand2)
            '-' -> SubtractionNode(operand1, operand2)
            '*' -> MultiplicationNode(operand1, operand2)
            else -> DivisionNode(operand1, operand2)
        }
    }

    fun build() {
        val closingBracketIndex = IntArray(expression.length, { -1 })
        val openingBracketIndex = IntArray(expression.length, { -1 })
        splitBracketsIntoPairs(closingBracketIndex, openingBracketIndex)
        root = buildRecursive(0, expression.length - 1, closingBracketIndex, openingBracketIndex)
    }

    fun compute(): Double? = root?.compute()

    fun printTreeToString(): String? = root?.printTreeToString("", 0)

    fun outputForDebugging(): String = root?.printTreeToString("", 0) + "\n" + "Result: ${compute()}"
}
