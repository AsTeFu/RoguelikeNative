package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.baseComponent.Collider
import game.components.inventoryComponent.ChestComponent
import game.components.inventoryComponent.InventoryComponent
import sceneManager.SceneManager
import sceneManager.SceneNames
import sceneManager.context.ChestDto
import utility.Input
import utility.KeyCode

class ChestOpenSystem(engine: Engine, private val sceneManager: SceneManager) : ISystem(engine) {

    private val player = engine.entityManager.getByTag("player")!!.first()

    override fun filter(entity: Entity)
            = entity.hasComponent<ChestComponent>() and entity.hasComponent<Collider>()

    override fun postUpdate(entity: Entity) {
        if (!Input.getKey(KeyCode.Enter)) return

        val collider = entity.getComponent<Collider>()!!
        if (player in collider) {
            val inventoryChest = entity.getComponent<InventoryComponent>()!!
            inventoryChest.forEach { println(it) }

            sceneManager.context.addObject { ChestDto(player, entity) }
            sceneManager.switchScene(SceneNames.CHEST)
        }
    }

}