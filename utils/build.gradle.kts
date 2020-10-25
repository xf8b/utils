plugins {
    java
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.4.10"
    id("net.minecrell.licenser") version "0.4.1"
}

fun property(name: String): Any = project.findProperty(name)
    ?: throw NoSuchElementException("No property found for name $name!")

fun propertyOrEnv(propertyName: String, envName: String): Any = project.findProperty(propertyName)
    ?: System.getenv(envName)

group = property("mavenGroup")
version = property("currentVersion")

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:${property("junitVersion")}")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    jar {
        manifest {
            attributes(
                mapOf(
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.version
                )
            )
        }
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "14"
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven(
            "https://repo.repsy.io/mvn/${propertyOrEnv("repsyUsername", "REPSY_USERNAME")}/${property("repsyRepoName")}"
        ) {
            credentials {
                username = propertyOrEnv("repsyUsername", "REPSY_USERNAME") as String
                password = propertyOrEnv("repsyPassword", "REPSY_PASSWORD") as String
            }
        }
    }
}

license {
    header = rootProject.file("LICENSE_HEADER.txt")

    ext {
        set("name", "xf8b")
        set("years", "2020")
        set("projectName", "Utils")
    }

    include("**/*.kt")
}
