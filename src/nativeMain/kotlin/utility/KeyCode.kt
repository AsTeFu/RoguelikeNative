package utility

import atf.bearlibterminal.*

enum class KeyCode(val code: Int) {
    Space(TK_SPACE),
    Close(TK_CLOSE),
    Enter(TK_ENTER),
    W(TK_W),
    S(TK_S),
    Escape(TK_ESCAPE),
    LeftArrow(TK_LEFT),
    RightArrow(TK_RIGHT),
    UpArrow(TK_UP),
    DownArrow(TK_DOWN),
    D(TK_D),
    A(TK_A),
    I(TK_I),
}