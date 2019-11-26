pluginManagement {
    repositories {
        gradlePluginPortal()
        val repoUrl: String by settings
        maven(url = "$repoUrl/groups/public")
    }
}

rootProject.name = "Narrative"
