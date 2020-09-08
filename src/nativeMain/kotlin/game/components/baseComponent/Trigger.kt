package game.components.baseComponent

import ecs.Entity
import ecs.IComponent

class Trigger : IComponent {

    private val entities = arrayListOf<Entity>()

    fun addEntity(entity: Entity) = entities.add(entity)

    fun clear() = entities.clear()

    operator fun contains(entity: Entity) = entities.any { it.id == entity.id }

}