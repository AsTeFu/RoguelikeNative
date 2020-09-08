package game.components.inventoryComponent

import ecs.Entity
import ecs.IComponent

class PickUpComponent(
    val pickUp: (entity: Entity) -> Unit
) : IComponent