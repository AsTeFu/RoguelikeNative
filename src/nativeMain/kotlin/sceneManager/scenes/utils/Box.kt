package sceneManager.scenes.utils

import atf.bearlibterminal.terminal_put
import terminal.Terminal
import utility.Colors
import utility.Display
import utility.Vector2

fun box(position: Vector2, size: Vector2, title: String) {
    Terminal.print(
        position.x + size.x / 2 - title.length / 2,
        position.y - 1 + size.y / 2,
        title
    )

    drawBorder(position, size)
}

private fun drawBorder(position: Vector2, size: Vector2) {
    horizontalBorder(position, size)
    verticalBorder(position, size)
}

private fun horizontalBorder(position: Vector2, size: Vector2) {
    horizontalLine(position.x, position.y, size.x)
    horizontalLine(position.x, position.y + size.y - 1, size.x)
}

private fun verticalBorder(position: Vector2, size: Vector2) {
    verticalLine(position.x, position.y, size.y)
    verticalLine(position.x + size.x - 1, position.y, size.y)
}

private fun horizontalLine(
    x: Int, y: Int, len: Int,
    display: Display = Display('=', Colors.WHITE.color)
) {
    Terminal.setColor(display.color)
    Terminal.print(x, y,
        buildString { repeat(len) { append(display.graphic) } }
    )
}

private fun verticalLine(
    x: Int, y: Int, len: Int,
    display: Display = Display('|', Colors.WHITE.color)
) {
    Terminal.setColor(display.color)
    repeat(len - 1) { Terminal.put(x, y + it - 1, display.graphic) }
}
