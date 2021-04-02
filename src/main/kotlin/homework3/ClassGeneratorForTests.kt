package homework3

import com.charleskorn.kaml.Yaml
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.FileSpec
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

/**
 * Stores the function name.
 * Required for correct parsing.
 */
@Serializable
data class Function(val name: String)

/**
 * Stores data from the config after parsing.
 */
@Serializable
data class Config(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    val functions: List<Function>
)

class ClassGeneratorForTests(private val yamlPath: String) {
    private var config = Yaml.default.decodeFromString(Config.serializer(), File(yamlPath).readText())

    /**
     * Returns a class with tests already added.
     */
    private fun getClassForTests(): TypeSpec {
        val builder = TypeSpec.classBuilder(config.className + "Test").addModifiers(KModifier.INTERNAL)
        for (function in config.functions) {
            val newTest = FunSpec.builder(function.name)
                .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
                .build()
            builder.addFunction(newTest)
        }
        return builder.build()
    }

    fun generate(): FileSpec {
        val classForTests: TypeSpec = getClassForTests()
        val newFile = FileSpec.builder(config.packageName, "tests")
            .addType(classForTests)
            .build()
        return newFile
    }

    fun generateToFile(ktPath: String) = this.generate().writeTo(File(ktPath))
}
