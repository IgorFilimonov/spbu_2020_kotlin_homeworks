package homework3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class Task3KtTest {
    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of("test1"),
            Arguments.of("test2")
        )
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "test {index}, {1}")
    fun generateTestFile(folder: String) {
        val expected = Task3KtTest::class.java.getResource("$folder/expected.kt").readText().replace("\r", "")
        val yamlPath = Task3KtTest::class.java.getResource("$folder/config.yaml").path
        val generated = ClassGeneratorForTests(yamlPath).generate().toString()
        assertEquals(expected, generated)
    }
}
