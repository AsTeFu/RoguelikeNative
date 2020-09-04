package terminal

import atf.bearlibterminal.*
import utility.Vector2

object Terminal {

    const val space = TK_SPACE
    const val close = TK_CLOSE

    fun open(): Boolean = terminal_open() == 0

    fun close() = terminal_close()

    fun refresh() = terminal_refresh()

    fun clear() = terminal_clear()

    fun put(x: Int, y: Int, code: Int) = terminal_put(x, y, code)

    fun put(x: Int, y: Int, code: Char) = put(x, y, code.toInt())

    fun put(position: Vector2, code: Int) = put(position.x, position.y, code)

    fun put(position: Vector2, code: Char) = put(position, code.toInt())

    fun setTitle(title: String): Terminal {
        terminal_set("window.title='$title';")
        return this
    }

    fun setSize(x: Int, y: Int): Terminal {
        terminal_set("window.size=${x}x$y;")
        return this
    }

    fun setCellSize(x: Int, y: Int): Terminal {
        terminal_set("window.cellsize=${x}x$y;")
        return this
    }

    fun set(option: String): Terminal {
        terminal_set(option)
        return this
    }


    fun print(x: Int, y: Int, string: String) = terminal_print(x, y, string)

    fun print(position: Vector2, string: String) = print(position.x, position.y, string)

    fun hasInput(): Boolean = terminal_has_input() != 0

    fun read(): Int = terminal_read()

    fun peek(): Int = terminal_peek()

    fun setLayer(layer: Int) = terminal_layer(layer)

    fun pick(position: Vector2, layer: Int) = terminal_pick(position.x, position.y, layer)

}
