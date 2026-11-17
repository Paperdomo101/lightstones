plugins {
    id("dev.kikugie.stonecutter")
    id("dev.architectury.loom") version "1.9-SNAPSHOT" apply false
}

stonecutter active "1.21.1-fabric" /* [SC] DO NOT EDIT */

stonecutter parameters {
    val loader = node.metadata.project.substringAfterLast('-')
    constants.match(loader, "fabric", "forge", "neoforge")
}

tasks.register<Copy>("buildAndCollect") {
    group = "build"
    description = "Builds all Minecraft versions and modloaders and collects release JARs into build/libs"
    dependsOn(
        ":1.20.1-fabric:build",
        ":1.20.1-forge:build",
        ":1.21.1-fabric:build",
        ":1.21.1-neoforge:build",
    )
    from(
        project(":1.20.1-fabric").layout.buildDirectory.dir("libs"),
        project(":1.20.1-forge").layout.buildDirectory.dir("libs"),
        project(":1.21.1-fabric").layout.buildDirectory.dir("libs"),
        project(":1.21.1-neoforge").layout.buildDirectory.dir("libs"),
    )
    include("*.jar")
    exclude("*-dev*.jar", "*-sources*.jar")
    into(layout.projectDirectory.dir("build/libs"))
}
