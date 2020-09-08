package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.baseComponent.Transform
import game.components.baseComponent.Trigger

class TriggerSystem(engine: Engine) : ISystem(engine) {

    private val player = engine.entityManager.getByTag("player")!!.first()

    override fun filter(entity: Entity) = entity.hasComponent<Transform>() and entity.hasComponent<Trigger>()

    override fun preUpdate(entity: Entity) = entity.getComponent<Trigger>()!!.clear()

    override fun update(entity: Entity) {
        val transform = entity.getComponent<Transform>()!!
        val trigger = entity.getComponent<Trigger>()!!

        if (player.getComponent<Transform>()!!.position == transform.position) {
            trigger.addEntity(player)
        }

//        entities.forEach {
//            val entityPosition = it.getComponent<Transform>()!!.position
//            if (transform.position == entityPosition) {
//                trigger.addEntity(it)
//            }
//        }
    }

}