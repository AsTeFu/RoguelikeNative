package game.rooms.builder

import utility.Color
import utility.Display
import utility.Vector2
import kotlin.random.Random


class RoomReader : IRoomReader {

    override fun read(): RoomStructure {
        val roomStructure = RoomStructure()

        repeat(20) { i ->
            repeat(40) { j ->

                if (Random.nextDouble() < 0.3 && (i != 10 && j != 10)) {
                    if (Random.nextDouble() > 0.5)
                        roomStructure.append(
                                Display('#', Color("#ffffff")),
                                Vector2(j, i)
                        )
                    else roomStructure.append(
                            Display('$', Color("#ffff00")),
                            Vector2(j, i)
                    )
                } else {
                    roomStructure.append(
                            Display('.', Color("#ffffff")),
                            Vector2(j, i)
                    )
                }
            }
        }

        return roomStructure
    }

}
