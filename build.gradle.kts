import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Date
import java.text.SimpleDateFormat

plugins {
    id("java-library")
    kotlin("jvm") version "1.9.0" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

group = "xyz.sorridi.velib"
version = "1.0-SNAPSHOT"

subprojects {
    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.github.johnrengelman.shadow")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    repositories {
        mavenCentral()
        maven {
            name = "jitpack"
            url = uri("https://www.jitpack.io")
        }
        maven {
            name = "mikigal-repo"
            url = uri("https://repo.mikigal.pl/releases")
        }
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")

        /* Data structures related */
        implementation("com.google.guava:guava:32.1.1-jre")
        implementation("commons-codec:commons-codec:1.16.0")
        implementation("org.apache.commons:commons-collections4:4.4")
        implementation("org.apache.commons:commons-math3:3.6.1")
        implementation("com.github.ben-manes.caffeine:caffeine:3.1.6")

        /* DB related */
        implementation("com.rabbitmq:amqp-client:5.18.0")
        implementation("com.zaxxer:HikariCP:5.0.1")
        implementation("org.jooq:jooq:3.18.5")

        /* Config related */
        implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.4")

        /* Reflections related */
        implementation("org.reflections:reflections:0.10.2")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    tasks.withType<ShadowJar> {
        val time = SimpleDateFormat("dd-MM-yyyy_HH-mm").format(Date())
        archiveFileName.set("${project.name}-${time}.jar")
    }
}