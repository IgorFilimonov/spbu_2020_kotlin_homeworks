import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.4.31"
    id("io.gitlab.arturbosch.detekt") version "1.15.0"
    application
    id("org.jetbrains.dokka") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.31"
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("com.charleskorn.kaml:kaml:0.29.0")
    implementation("com.squareup:kotlinpoet:1.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.openjfx:javafx-base:11.0.2")
    implementation("org.openjfx:javafx:11.0.2")
    implementation("org.openjfx:javafx-controls:11.0.2")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.4.32")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
}

detekt {
    failFast = true // fail build on any finding
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

javafx {
    modules("javafx.controls")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(
            org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
            TestLogEvent.STARTED,
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
            TestLogEvent.SKIPPED
        )
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Werror")
    }
}

tasks.dokkaHtml {
    dokkaSourceSets {
        configureEach {
            moduleName.set("SPbU Kotlin Homeworks")
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/IgorFilimonov/spbu_2020_kotlin_homeworks/tree/master/src" +
                        "main/kotlin"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}

application {
    mainClass.set("MainKt")
}