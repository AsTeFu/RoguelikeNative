package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.baseComponent.Trigger
import game.components.inventoryComponent.PickUpComponent

class PickupSystem(engine: Engine) : ISystem(engine) {

    private val player = engine.entityManager.getByTag("player")!!.first()

    override fun filter(entity: Entity)
            = entity.hasComponent<Trigger>() and entity.hasComponent<PickUpComponent>()

    override fun update(entity: Entity) {
        val trigger = entity.getComponent<Trigger>()!!
        if (player in trigger) {
            val item = entity.getComponent<PickUpComponent>()!!
            item.pickUp(player)
            engine.deleteEntity(entity)
        }
    }

}