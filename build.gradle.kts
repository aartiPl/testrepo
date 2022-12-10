val kotlinVersion: String = "1.7.21"

plugins {
    kotlin("jvm") version "1.7.21"
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "io.github.kscripting"
version = "4.2.0-SNAPSHOT"

dependencies {
    implementation("commons-cli:commons-cli:1.5.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation("org.jetbrains.kotlin:kotlin-scripting-common:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-dependencies-maven-all:$kotlinVersion")

    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation("commons-codec:commons-codec:1.15")
    implementation("com.konghq:unirest-java:3.13.13")

    implementation("net.igsoft:tablevis:0.6.0")
    implementation("io.arrow-kt:arrow-core:1.1.2")

    implementation("io.github.kscripting:shell:0.5.0")

    implementation("org.slf4j:slf4j-nop:2.0.5")

    implementation("org.semver4j:semver4j:3.0.0")


    testImplementation("org.junit.platform:junit-platform-suite-engine:1.9.0")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.9.0")
    testImplementation("org.junit.platform:junit-platform-suite-commons:1.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")
    testImplementation("io.mockk:mockk:1.13.2")

    testImplementation(kotlin("script-runtime"))
}
