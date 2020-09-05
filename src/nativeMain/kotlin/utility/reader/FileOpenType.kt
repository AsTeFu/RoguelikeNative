package utility.reader

enum class FileOpenType(val type: String) {
    Read("r"),
    Write("w"),
    Append("a"),
    ReadWrite("r+"),
    Rewrite("w+"),
    CreateOrAppend("a+"),
}
