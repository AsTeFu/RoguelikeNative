package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.CameraComponent
import game.components.baseComponent.Transform
import utility.Vector2
import kotlin.math.abs
import kotlin.math.sign

class CameraSystem(engine: Engine) : ISystem(engine) {

    private val target = engine.entityManager
        .getByTag("player")!!
        .first()
        .getComponent<Transform>()!!

    override fun filter(entity: Entity)
            = entity.hasComponent<CameraComponent>() and entity.hasComponent<Transform>()

    override fun update(entity: Entity) {
        val camera = entity.getComponent<CameraComponent>()!!
        val transform = entity.getComponent<Transform>()!!

        val distance = transform.position - target.position

        if (abs(distance.x) > camera.border.x) {
            transform.position -= Vector2(distance.x.sign, 0)
        }

        if (abs(distance.y) > camera.border.y) {
            transform.position -= Vector2(0, distance.y.sign)
        }

        camera.offset = camera.size - transform.position
    }

}