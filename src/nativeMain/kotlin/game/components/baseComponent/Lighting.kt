package game.components.baseComponent

import ecs.IComponent
import utility.Vector2

class Lighting(var renderPosition: Vector2) : IComponent {

    var observed = false
    var lighted = false

}
