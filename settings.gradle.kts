pluginManagement {
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        jcenter() // Warning: this repository is going to shut down soon
        mavenCentral()
    }
}

rootProject.name = "AppDemo"
include(":app")
 