package utils

class ConsolePrinter : Printer {
    override fun print(message: String) {
        kotlin.io.print("[OUTPUT]: " + message)
    }

    override fun println(message: String) {
        kotlin.io.println("[OUTPUT]: " + message)
    }
}
