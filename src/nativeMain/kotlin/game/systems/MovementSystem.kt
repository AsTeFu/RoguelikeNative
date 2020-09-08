package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.WallsComponent
import game.components.baseComponent.Collider
import game.components.baseComponent.Movement
import game.components.baseComponent.Transform

class MovementSystem(engine: Engine) : ISystem(engine) {

    private lateinit var walls: WallsComponent

    override fun updateComponents() {
        super.updateComponents()
        walls = engine.entityManager.getByTag("walls")?.first()?.getComponent()!!
    }

    override fun filter(entity: Entity) = entity.hasComponent<Movement>() && entity.hasComponent<Transform>()

    override fun preUpdate(entity: Entity) {
        if (isCanMove(entity)) {
            entity.getComponent<Transform>()!!.position += entity.getComponent<Movement>()!!.direction
        }
    }

    private fun isCanMove(entity: Entity): Boolean {
        if (!entity.hasComponent<Collider>()) return true

        val transform = entity.getComponent<Transform>()!!
        val movement = entity.getComponent<Movement>()!!

        val posEntity = transform.position + movement.direction
        if (walls.findWallAtPoint(posEntity) || movement.direction.isZero()) return false

        var canMove = true

        entities.forEach {
            if (!it.hasComponent<Collider>() || it.id == entity.id) return@forEach

            if (transform.position + movement.direction == it.getComponent<Transform>()?.position) {
                canMove = false
                return@forEach
            }
        }

        return canMove //and !movement.direction.isZero()
    }
}
