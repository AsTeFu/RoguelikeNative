package game.components.baseComponent

import ecs.Entity
import ecs.IComponent

class Collider : IComponent {

    private val colls = arrayListOf<Entity>()

    fun addCollision(entity: Entity) = colls.add(entity)

    fun clear() = colls.clear()

    operator fun contains(entity: Entity) = colls.any { it.id == entity.id }

}
