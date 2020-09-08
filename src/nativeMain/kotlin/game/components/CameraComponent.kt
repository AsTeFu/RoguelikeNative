package game.components

import ecs.IComponent
import utility.Vector2

class CameraComponent(
    val size: Vector2,
    val border: Vector2
) : IComponent {
    var offset: Vector2 = Vector2(0, 0)
}