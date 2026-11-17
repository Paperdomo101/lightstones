plugins {
    id("dev.architectury.loom")
    id("maven-publish")
}

val loader = stonecutter.current.project.substringAfterLast('-')
val mcVersion = stonecutter.current.version
val javaMajor = if (stonecutter.eval(mcVersion, ">=1.20.6")) 21 else 17

group = property("maven_group") as String
version = property("mod_version") as String

base {
    archivesName.set("${property("archives_base_name")}-${loader}-${mcVersion}")
}

loom {
    silentMojangMappingsLicense()
    accessWidenerPath.set(rootProject.file("src/main/resources/lightstones.accesswidener"))

    if (loader == "forge") {
        forge {
            convertAccessWideners.set(true)
            mixinConfigs("lightstones-common.mixins.json")
        }
    }
}

repositories {
    mavenCentral()
    maven {
        name = "NeoForged"
        url = uri("https://maven.neoforged.net/releases/")
    }
    maven {
        name = "Shedaniel"
        url = uri("https://maven.shedaniel.me/")
    }
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/releases/")
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${mcVersion}")

    val neoforgePatch = findProperty("yarn_mappings_patch_neoforge_version") as String?
    if (neoforgePatch != null) {
        mappings(loom.layered {
            mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
            mappings("dev.architectury:yarn-mappings-patch-neoforge:${neoforgePatch}")
        })
    } else {
        mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    }

    when (loader) {
        "fabric" -> {
            modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
            modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")
            modCompileOnly("com.terraformersmc:modmenu:${property("modmenu_version")}")
            modCompileOnly("me.shedaniel.cloth:cloth-config-fabric:${property("cloth_config_version")}") {
                exclude(group = "net.fabricmc.fabric-api")
            }
        }
        "forge" -> {
            "forge"("net.minecraftforge:forge:${property("forge_version")}")
            modCompileOnly("me.shedaniel.cloth:cloth-config-forge:${property("cloth_config_version")}")
        }
        "neoforge" -> {
            "neoForge"("net.neoforged:neoforge:${property("neoforge_version")}")
            modCompileOnly("me.shedaniel.cloth:cloth-config-neoforge:${property("cloth_config_version")}")
        }
    }
}

if (loader == "forge" || loader == "neoforge") {
    sourceSets.main.get().java.srcDir(rootProject.file("loader/${loader}/java"))
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.toVersion(javaMajor)
    targetCompatibility = JavaVersion.toVersion(javaMajor)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(javaMajor)
}

val targetJavaLauncher = javaToolchains.launcherFor {
    languageVersion.set(JavaLanguageVersion.of(javaMajor))
}

tasks.withType<JavaExec>().configureEach {
    javaLauncher.set(targetJavaLauncher)
}

val baseName = property("archives_base_name") as String

val resourceProps = mutableMapOf<String, Any>(
    "version" to project.version,
    "java_major" to javaMajor,
)
if (loader == "fabric") {
    resourceProps["mc_dep"] = property("mc_dep") as String
    resourceProps["fabric_loader_dep"] = property("fabric_loader_dep") as String
    resourceProps["fabric_api_id"] = property("fabric_api_id") as String
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(rootProject.file("resources/${mcVersion}"))
    from(rootProject.file("loader/${loader}/resources"))

    inputs.properties(resourceProps)

    filesMatching(
        listOf(
            "fabric.mod.json",
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
            "lightstones-common.mixins.json",
            "lightstones-fabric.mixins.json",
        )
    ) {
        expand(resourceProps)
    }
}

tasks.named<Jar>("sourcesJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(rootProject.file("resources/${mcVersion}"))
    from(rootProject.file("loader/${loader}/resources"))
}

tasks.jar {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_${baseName}" }
    }
}
