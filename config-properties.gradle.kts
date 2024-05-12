// Create variables with empty default values
extra["RELEASE_STORE_FILE"] = "/"
extra["RELEASE_STORE_PASSWORD"] = ""
extra["RELEASE_KEY_ALIAS"] = ""
extra["RELEASE_KEY_PASSWORD"] = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    val p = java.util.Properties()
    java.io.FileInputStream(secretPropsFile).use { p.load(it) }
    p.forEach { name, value -> extra["$name"] = value }
} else {
    extra["RELEASE_STORE_FILE"] = System.getenv("RELEASE_STORE_FILE")
    extra["RELEASE_STORE_PASSWORD"] = System.getenv("RELEASE_STORE_PASSWORD")
    extra["RELEASE_KEY_ALIAS"] = System.getenv("RELEASE_KEY_ALIAS")
    extra["RELEASE_KEY_PASSWORD"] = System.getenv("RELEASE_KEY_PASSWORD")
}
