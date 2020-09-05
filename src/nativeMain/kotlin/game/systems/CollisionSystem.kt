package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.WallComponent
import game.components.baseComponent.Collider
import game.components.baseComponent.Transform

class CollisionSystem(engine: Engine) : ISystem(engine) {

    override fun filter(entity: Entity): Boolean = entity.hasComponent<Transform>() and
            entity.hasComponent<Collider>() and entity.hasComponent<WallComponent>()

    override fun preUpdate(entity: Entity) = entity.getComponent<Collider>()!!.clear()

    override fun update(entity: Entity) {
        val transform = entity.getComponent<Transform>()!!
        val collider = entity.getComponent<Collider>()!!

        entities.forEach {
            val len = (transform.position - it.getComponent<Transform>()!!.position).length2
            if (len <= 2) collider.addCollision(it)
        }
    }
}
