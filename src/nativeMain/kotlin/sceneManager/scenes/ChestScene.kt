package sceneManager.scenes

import ecs.Entity
import game.components.inventoryComponent.InventoryComponent
import game.inventory.InventoryItem
import sceneManager.IScene
import sceneManager.SceneManager
import sceneManager.SceneNames
import sceneManager.context.ChestDto
import sceneManager.context.Context
import sceneManager.scenes.utils.Cursor
import sceneManager.scenes.utils.ListSelector
import sceneManager.scenes.utils.drawBox
import terminal.Terminal
import utility.Colors
import utility.Input
import utility.KeyCode
import utility.Vector2

class ChestScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    private val position = Vector2(20, 10)
    private val size = Vector2(110, 25)

    private lateinit var player: Entity
    private lateinit var chest: InventoryComponent

    private lateinit var listSelector : ListSelector<InventoryItem>
    private val cursor = Cursor()

    override fun start() {
        repeat(30) {
            Terminal.setLayer(it)
            Terminal.clearArea(position, size)
//            Terminal.crop(position, size)
        }

        player = context.getObject<ChestDto>().player
        chest = context.getObject<ChestDto>().chest.getComponent()!!
        listSelector = ListSelector(chest.inventory)

        this.render()
    }

    override fun end() {
        Terminal.clear()
    }

    override fun update() {
        if (Input.getKey(KeyCode.ESCAPE)) {
            sceneManager.switchScene(SceneNames.GAME)
        }
        if (Input.getKey(KeyCode.W)) listSelector.upward()
        if (Input.getKey(KeyCode.S)) listSelector.downward()

        this.render()
    }

    private fun render() {
        Terminal.setLayer(50)
        Terminal.clearArea(position, size)

        drawBox(position, size, "CHEST")

        var drawPosition = position + Vector2(3, 5)

        chest.inventory.forEachIndexed { index, item ->
            if (listSelector.currentElement === item) {
                cursor.print(drawPosition.x, drawPosition.y + index * 2)
                Terminal.setColor(Colors.RED)
            } else Terminal.setColor(Colors.WHITE)

            Terminal.print(drawPosition.x, drawPosition.y + index * 2, item.toString())
        }

        Terminal.refresh()
    }

}