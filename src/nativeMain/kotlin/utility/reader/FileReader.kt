package utility.reader

import platform.posix.*

class FileReader {

    fun read(fileName: String, type: FileOpenType) : List<List<Char>> {
        val file = fopen(fileName, type.type)

        if (file == NULL) {
            println("Unable to open $fileName")
            throw Exception("Unable to open $fileName")
        }

        val readSymbols = arrayListOf<ArrayList<Char>>()
        readSymbols.add(arrayListOf())

        while(feof(file) == 0) {
            val symbol = fgetc(file).toChar()

            if (symbol == '\n') readSymbols.add(arrayListOf())
            else readSymbols.last().add(symbol)
        }

        if (fclose(file) != 0) {
            println("Unable to close $fileName")
            throw Exception("Unable to close $fileName")
        }

        return readSymbols
    }

}

