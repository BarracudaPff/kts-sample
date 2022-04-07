import java.io.File

inline fun tryRun(block: () -> Unit) {
    try {
        block()
    } catch (_: Throwable) {
    }
}

val keyFromFile by lazy {
    File("...").readText()
        .split("\n")
        .map { it.trim() }
        .let { it[0] to it[1] }
}

val getAccessKey: String
    get() = System.getenv("ACCESS_KEY") ?: "TEMP-KEY" ?: keyFromFile.second
