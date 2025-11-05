plugins {
    kotlin("jvm") version "2.2.20"
}

group = "fr.solevyr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jogamp.org/deployment/maven/")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.processing:core:4.4.10")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}