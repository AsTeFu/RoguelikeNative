package sceneManager.scenes.utils

import terminal.Terminal
import utility.Color
import utility.Vector2

class Cursor(
    private val display: String = "=>",
    private val color: Color = Color("#FFFFFF")
) {

    var indent = display.length + 1

    fun print(position: Vector2) {
        Terminal.setColor(color)
        Terminal.print(position, display)
    }

    fun print(x: Int, y: Int) {
        Terminal.setColor(color)
        Terminal.print(x - indent, y, display)
    }

}