import org.gradle.internal.impldep.org.eclipse.jgit.lib.ObjectChecker.type

plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version("7.1.2")
    id ("io.freefair.lombok") version("6.4.3")
}

group = "quest.highworld"
version = "1.0"


repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.minestom.com/repository/maven-public/")
}

dependencies {

    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    // Minestom api
    implementation("com.github.Minestom:Minestom:-SNAPSHOT")

    // JNoise Library
    // https://mavenlibs.com/maven/dependency/de.articdive/jnoise
    implementation("com.github.Articdive.JNoise:jnoise-pipeline:b93008e35e")

    // Commons IO
    implementation("commons-io:commons-io:2.6")

    // JSON
    implementation("org.json:json:20220320")

    // SQLite
    //implementation("org.sqlite:sqlite:3.32.1")
    implementation("org.xerial:sqlite-jdbc:3.30.1")

    //relocate("com.github.Minestom:Minestom:-SNAPSHOT", "net.bridgeduels.server")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

// Set compile to utf 8
tasks.getByName<JavaCompile>("compileJava") {
    options.encoding = "UTF-8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("server.jar")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "quest.highworld.Highworld"
    }
}