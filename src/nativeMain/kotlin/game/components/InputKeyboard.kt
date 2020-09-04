package game.components

import ecs.IComponent


class InputKeyboard() : IComponent {

    var key: Int = 0
    var downs = mutableMapOf<Int, Boolean>()

}
