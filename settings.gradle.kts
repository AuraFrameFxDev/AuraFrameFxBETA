pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// If you have multiple modules, list them here. For example:
include(":app")
// include(":module1")
// include(":module2")
rootProject.name = "AuraFrameFxBETA"
