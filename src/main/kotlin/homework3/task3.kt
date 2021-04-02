package homework3

fun pathInput(message: String): String {
    println(message)
    return readLine() ?: error("This is not a string")
}

fun main() {
    val yamlPath = pathInput("Enter the path to the yaml config:")
    val ktPath = pathInput("Enter the path where to save the kt file:")
    ClassGeneratorForTests(yamlPath).generateToFile(ktPath)
}
