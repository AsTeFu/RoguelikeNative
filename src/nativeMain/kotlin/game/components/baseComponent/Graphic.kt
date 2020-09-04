package game.components.baseComponent

import ecs.IComponent
import utility.Display


class Graphic(val display: Display, var layer: Int = 0) : IComponent
