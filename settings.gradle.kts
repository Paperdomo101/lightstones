pluginManagement {
    repositories {
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        maven("https://maven.architectury.dev/") { name = "Architectury" }
        maven("https://maven.minecraftforge.net/") { name = "Forge" }
        maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("dev.kikugie.stonecutter") version "0.7.11"
}

stonecutter {
    create(rootProject) {
        version("1.20.1-fabric", "1.20.1")
        version("1.20.1-forge", "1.20.1")
        version("1.21.1-fabric", "1.21.1")
        version("1.21.1-neoforge", "1.21.1")
        vcsVersion = "1.21.1-fabric"
    }
}

rootProject.name = "lightstones"
