import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    java
    kotlin("jvm") version "1.9.0" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

group = "xyz.sorridi.velib"
version = "1.0-SNAPSHOT"

subprojects {
    apply {
        plugin("java")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("com.github.johnrengelman.shadow")
    }

    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")

        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven {
            name = "mikigal-repo"
            url = uri("https://repo.mikigal.pl/releases")
        }
        maven {
            name = "jitpack"
            url = uri("https://www.jitpack.io")
        }
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.11.4"))
        testImplementation("org.junit.jupiter:junit-jupiter")

        /* Data structures related */
        implementation("com.google.guava:guava:33.4.0-jre")
        implementation("commons-codec:commons-codec:1.17.1")
        implementation("org.apache.commons:commons-collections4:4.4")
        implementation("org.apache.commons:commons-math3:3.6.1")
        implementation("org.apache.commons:commons-lang3:3.13.0")
        implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

        /* DB related */
        implementation("com.mysql:mysql-connector-j:9.1.0")
        implementation("org.mariadb.jdbc:mariadb-java-client:3.5.1")
        implementation("org.postgresql:postgresql:42.7.4")
        implementation("com.rabbitmq:amqp-client:5.24.0")
        implementation("com.zaxxer:HikariCP:6.2.1")
        implementation("org.jooq:jooq:3.19.16")
        implementation("org.hibernate.orm:hibernate-core:6.6.3.Final")

        /* Config related */
        implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8.4")

        /* Reflections related */
        implementation("org.reflections:reflections:0.10.2")

        /* Others */
        implementation("org.apache.logging.log4j:log4j-core:2.24.3")

        /* Encoding/Decoding related. */
        implementation("com.fasterxml.jackson.core:jackson-core:2.18.2")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
        implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-ion:2.18.2")
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
