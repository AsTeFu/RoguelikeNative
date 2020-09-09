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
import sceneManager.context.PlayerDto
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
            sceneManager.context.addObject { ChestDto(entity) }
            sceneManager.context.addObject { PlayerDto(player) }
            sceneManager.switchScene(SceneNames.CHEST)
        }
    }

}