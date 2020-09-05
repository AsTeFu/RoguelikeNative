package utility

import atf.bearlibterminal.TK_KEY_RELEASED
import terminal.Terminal

object Input {

    private val terminal = Terminal

    fun getKey(keyCode: KeyCode): Boolean = terminal.peek() == keyCode.code

    fun getKeyUp(keyCode: KeyCode): Boolean = terminal.peek() == (keyCode.code or TK_KEY_RELEASED)

}