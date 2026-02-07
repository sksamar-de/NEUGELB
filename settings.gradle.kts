pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "NEUGELB"
include(":app")
include(":movies:movies_data")
include(":movies:movies_domain")
include(":movies:movies_presentation")
include(":search:search_data")
include(":search:search_domain")
include(":search:search_presentation")
include(":common:common_utls")
include(":details:details_data")
include(":details:details_domain")
include(":details:details_presentation")
