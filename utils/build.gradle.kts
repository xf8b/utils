import org.jetbrains.kotlin.gradle.tasks.KotlinCompile as CompileKotlin

plugins {
    java
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.4.21"
    id("org.jetbrains.dokka") version "1.4.20"
    id("net.minecrell.licenser") version "0.4.1"
    id("com.github.ben-manes.versions") version "0.36.0"
}

fun property(name: String) = project.findProperty(name)
        ?: throw NoSuchElementException("No property found for name $name!")

fun propertyOrEnv(propertyName: String, envName: String = propertyName) = project.findProperty(propertyName)
        ?: System.getenv(envName)

group = property("mavenGroup")
version = property("currentVersion")
base.archivesBaseName = property("archivesBaseName") as String

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation("junit:junit:${property("junitVersion")}")
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

    withType<CompileKotlin>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
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
