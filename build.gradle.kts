import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kotlinVersion: String = "1.7.21"

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "io.github.kscripting"
version = "4.2.0-SNAPSHOT"

val shadowJar by tasks.getting(ShadowJar::class) {
    // set empty string to classifier and version to get predictable jar file name: build/libs/kscript.jar
    archiveFileName.set("kscript.jar")
}

val createKscriptLayout by tasks.register<Copy>("createKscriptLayout") {
    dependsOn(shadowJar)

    into(layout.projectDirectory)

    from(shadowJar) {
        into("build/kscript/bin")
    }

    from("src/kscript") {
        into("build/kscript/bin")
    }

    from("src/kscript.bat") {
        into("build/kscript/bin")
    }
}

val packageKscriptDistribution by tasks.register<Zip>("packageKscriptDistribution") {
    dependsOn(createKscriptLayout)

    from(layout.buildDirectory.dir("kscript")) {
        into("kscript-${project.version}")
    }

    archiveFileName.set("kscript-${project.version}-bin.zip")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))

    from(layout.buildDirectory.dir("kscript-${project.version}"))
}

val assemble: Task by tasks.getting {
    dependsOn(packageKscriptDistribution)
}


application {
    mainClass.set(project.group.toString() + ".KscriptKt")
}

val shadowDistTar: Task by tasks.getting {
    enabled = false
}

val shadowDistZip: Task by tasks.getting {
    enabled = false
}

val distTar: Task by tasks.getting {
    enabled = false
}

val distZip: Task by tasks.getting {
    enabled = false
}

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
