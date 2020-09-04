import atf.bearlibterminal.*
import game.rooms.builder.RoomBuilder
import terminal.Terminal

fun main() {
    val terminal = Terminal

    terminal.open()
    terminal.setSize(150, 45).setCellSize(8, 16)
    terminal.refresh()

    val room = RoomBuilder().build()
    room.engine.update()
    terminal.refresh()

    while (true) {
        if (terminal.hasInput()) {
            terminal.clear()

            if (terminal.peek() == TK_CLOSE) break

            room.engine.update()
            terminal.refresh()
            terminal.read()
        }
    }
}