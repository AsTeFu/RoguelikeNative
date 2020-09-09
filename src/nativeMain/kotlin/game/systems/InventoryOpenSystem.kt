package game.systems

import ecs.Engine
import ecs.Entity
import ecs.ISystem
import game.components.InputKeyboard
import game.components.inventoryComponent.InventoryComponent
import sceneManager.SceneManager
import sceneManager.SceneNames
import sceneManager.context.PlayerDto
import utility.Input
import utility.KeyCode

class InventoryOpenSystem(
    engine: Engine,
    private val sceneManager: SceneManager
) : ISystem(engine) {

    override fun filter(entity: Entity)
            = entity.hasComponent<InputKeyboard>() and entity.hasComponent<InventoryComponent>()

    override fun postUpdate(entity: Entity) {
        if (Input.getKey(KeyCode.I)) {

            sceneManager.context.addObject { PlayerDto(entity) }
            sceneManager.switchScene(SceneNames.INVENTORY)
        }
    }

}