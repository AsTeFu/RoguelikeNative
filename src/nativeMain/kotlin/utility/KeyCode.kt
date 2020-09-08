package utility

import atf.bearlibterminal.*

enum class KeyCode(val code: Int) {
    Space(TK_SPACE),
    Close(TK_CLOSE),
    Enter(TK_ENTER),
    W(TK_W),
    S(TK_S),
    ESCAPE(TK_ESCAPE)
}