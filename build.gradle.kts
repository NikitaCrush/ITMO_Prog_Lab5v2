import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "7.1.2" // Add this line for the shadow plugin
}

group = "com.NikitaCrush"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    // Add this block to configure the JAR task
    jar {
        manifest {
            attributes(
                "Main-Class" to "MainKt"
            )
        }
        archiveBaseName.set("5Lab")
        archiveVersion.set("3.5.0")
    }


    withType<ShadowJar> {
        archiveClassifier.set("")
    }
}
