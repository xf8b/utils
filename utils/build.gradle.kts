import org.jetbrains.kotlin.gradle.tasks.KotlinCompile as CompileKotlin

plugins {
    `java-library`
    kotlin("jvm") version "1.4.21"
    `maven-publish`
    id("org.jetbrains.dokka") version "1.4.20"
    id("net.minecrell.licenser") version "0.4.1"
    id("com.github.ben-manes.versions") version "0.36.0"
}

fun property(name: String) = rootProject.findProperty(name)
    ?: throw NoSuchElementException("No property found for name $name!")

fun propertyOrEnv(propertyName: String, envName: String = propertyName) = rootProject.findProperty(propertyName)
    ?: System.getenv(envName)

fun hasPropertyOrEnv(propertyName: String, envName: String = propertyName) = rootProject.hasProperty(propertyName)
        || System.getenv().containsKey(envName)

group = property("mavenGroup")
base.archivesBaseName = "utils"
version = property("currentVersion")

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:${property("junitVersion")}")
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

    test {
        useJUnitPlatform()
    }

    withType<CompileKotlin>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            languageVersion = "1.4"
        }
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

val javadocJar by tasks.creating(Jar::class) {
    dependsOn("dokkaJavadoc")
    archiveClassifier.set("javadoc")
    from("build/dokka/javadoc")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(sourcesJar)
            artifact(javadocJar)
            from(components["kotlin"])
        }
    }

    repositories {
        if (hasPropertyOrEnv("repsyUsername", "REPSY_USERNAME")
            && hasPropertyOrEnv("repsyPassword", "REPSY_PASSWORD")
        ) {
            val tempUsername = propertyOrEnv("repsyUsername", "REPSY_USERNAME") as String
            val repoName = property("repsyRepoName")

            maven("https://repo.repsy.io/mvn/$tempUsername/$repoName") {
                credentials {
                    username = propertyOrEnv("repsyUsername", "REPSY_USERNAME") as String
                    password = propertyOrEnv("repsyPassword", "REPSY_PASSWORD") as String
                }
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
