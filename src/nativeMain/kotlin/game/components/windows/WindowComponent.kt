package game.components.windows

import ecs.IComponent

open class WindowComponent(
    val postUpdate: () -> Unit,
    val preUpdate: () -> Unit = {},
) : IComponent