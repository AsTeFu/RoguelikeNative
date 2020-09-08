package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.baseComponent.Collider
import game.components.inventoryComponent.ChestComponent
import game.components.inventoryComponent.InventoryComponent
import utility.Input
import utility.KeyCode
import kotlin.reflect.typeOf

class ChestOpenSystem(engine: Engine) : ISystem(engine) {

    private val player = engine.entityManager.getByTag("player")!!.first()

    override fun filter(entity: Entity)
            = entity.hasComponent<ChestComponent>() and entity.hasComponent<Collider>()

    override fun update(entity: Entity) {
        if (!Input.getKey(KeyCode.Enter)) return

        val collider = entity.getComponent<Collider>()!!
        if (player in collider) {
            val inventoryChest = entity.getComponent<InventoryComponent>()!!
            inventoryChest.forEach { println(it) }
        }
    }

}