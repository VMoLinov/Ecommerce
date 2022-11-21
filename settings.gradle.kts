@file:Suppress("UnstableApiUsage")

include(":featureCart")


include(":featureDetails")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ecommerce"
include(":app")
include(":core")
include(":network")
include(":featureMain")
include(":model")
