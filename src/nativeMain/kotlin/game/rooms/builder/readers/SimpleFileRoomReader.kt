package game.rooms.builder.readers

import game.rooms.builder.RoomStructure
import utility.*
import utility.reader.FileOpenType
import utility.reader.FileReader

class SimpleFileRoomReader(private val fileName: String) : IRoomReader {

    override fun read(): RoomStructure {
        val symbols = FileReader.readBySymbol(fileName, FileOpenType.Read)
        val roomStructure = RoomStructure()

        repeat(symbols.size) { i ->
            repeat(symbols[i].size) { j ->
                if (symbols[i][j] != '#') {
                    roomStructure.append(
                        Display('.', Color("#9fe2bf")),
                        Vector2(j, i)
                    )
                }

                roomStructure.append(
                    Display(symbols[i][j], Color("#FFFFFF")),
                    Vector2(j, i)
                )
            }
        }

        return roomStructure
    }

}
