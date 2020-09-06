import org.jetbrains.kotlin.cli.jvm.main

plugins {
    kotlin("multiplatform") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
}

group = "atf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
                linkerOpts =
                    mutableListOf("-lBearLibTerminal", "-L/home/astefu/Documents/Roguelike/lib/BearLibTerminal/Linux64")
                runTask?.dependsOn("");
            }

            compilations.getByName("main") {
                val interop by cinterops.creating {
                    defFile(project.file("src/nativeInterop/cinterop/BearLibTerminal.def"))

                    packageName("atf.bearlibterminal")

                    compilerOpts("-I/home/astefu/Documents/Roguelike/lib/BearLibTerminal/include")
                }
            }
        }
    }

    sourceSets {
        val nativeMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.0.0-RC")
                implementation("org.junit.jupiter:junit-jupiter:5.4.2")
            }
        }
        val nativeTest by getting
    }
}