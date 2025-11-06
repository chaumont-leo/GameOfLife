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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}