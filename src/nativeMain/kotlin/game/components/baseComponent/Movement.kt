package game.components.baseComponent

import ecs.IComponent
import utility.Vector2

class Movement(var direction: Vector2 = Vector2(0, 0)) : IComponent
