package game.components.windows

open class WindowConfig(
    val postUpdate: () -> Unit,
    val preUpdate: () -> Unit = {},
)