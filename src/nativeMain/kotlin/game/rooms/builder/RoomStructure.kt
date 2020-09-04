package game.rooms.builder

import utility.Display
import utility.Vector2

class RoomStructure {

    private val listObjects = arrayListOf<Pair<Display, Vector2>>()

    fun forEach(func: (Display, Vector2) -> Unit) {
        listObjects.forEach {
            func(it.first, it.second)
        }
    }

    fun append(display: Display, position: Vector2) {
        listObjects.add(Pair(display, position))
    }

}
