package homework5

interface ParseTreeNodes {
    fun compute(): Double
    fun printTreeToString(outputString: String, depth: Int): String
}

class Number(private val number: Int) : ParseTreeNodes {
    override fun compute(): Double = number.toDouble()

    override fun printTreeToString(outputString: String, depth: Int): String {
        var newOutputString = outputString + "\n"
        repeat(depth) { newOutputString = newOutputString + "...." }
        return newOutputString + number.toString()
    }
}

fun printTreeOfOperationNode(
    operation: Char,
    operand1: ParseTreeNodes,
    operand2: ParseTreeNodes,
    depth: Int,
    outputString: String
): String {
    var newOutputString = outputString
    if (outputString != "") {
        newOutputString = newOutputString + "\n"
    }
    repeat(depth) { newOutputString = newOutputString + "...." }
    newOutputString = newOutputString + operation
    newOutputString = operand1.printTreeToString(newOutputString, depth + 1)
    return operand2.printTreeToString(newOutputString, depth + 1)
}

class AdditionNode(private val operand1: ParseTreeNodes, private val operand2: ParseTreeNodes) : ParseTreeNodes {
    override fun compute(): Double = operand1.compute() + operand2.compute()
    override fun printTreeToString(outputString: String, depth: Int): String {
        return printTreeOfOperationNode('+', operand1, operand2, depth, outputString)
    }
}

class SubtractionNode(private val minuend: ParseTreeNodes, private val subtrahend: ParseTreeNodes) : ParseTreeNodes {
    override fun compute(): Double = minuend.compute() - subtrahend.compute()
    override fun printTreeToString(outputString: String, depth: Int): String {
        return printTreeOfOperationNode('-', minuend, subtrahend, depth, outputString)
    }
}

class MultiplicationNode(private val operand1: ParseTreeNodes, private val operand2: ParseTreeNodes) : ParseTreeNodes {
    override fun compute(): Double = operand1.compute() * operand2.compute()
    override fun printTreeToString(outputString: String, depth: Int): String {
        return printTreeOfOperationNode('*', operand1, operand2, depth, outputString)
    }
}

class DivisionNode(private val dividend: ParseTreeNodes, private val divider: ParseTreeNodes) : ParseTreeNodes {
    override fun compute(): Double {
        val dividerValue = divider.compute()
        if (dividerValue == 0.0) {
            throw ArithmeticException("You can't divide by zero!")
        }
        return dividend.compute() / dividerValue
    }

    override fun printTreeToString(outputString: String, depth: Int): String {
        return printTreeOfOperationNode('/', dividend, divider, depth, outputString)
    }
}
