plugins {
    id("com.diffplug.spotless")
}

internal val Project.libs: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

spotless {
    format("misc") {
        target("**/*.gradle", "**/*.md", "**/.gitignore")
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt", "**/*.kts")
        ktlint(libs.findVersion("ktlint").get().requiredVersion)
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
