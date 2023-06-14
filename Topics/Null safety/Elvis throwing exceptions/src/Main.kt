fun main() {
    print("Elvis says: ${readlnOrNull() ?: throw IllegalStateException()}")
}
