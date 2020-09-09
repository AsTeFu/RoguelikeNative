package sceneManager.scenes

import sceneManager.IScene
import sceneManager.SceneManager
import sceneManager.context.Context
import sceneManager.context.PlayerDto
import sceneManager.scenes.utils.drawBox
import sceneManager.scenes.utils.horizontalLine
import terminal.Terminal
import utility.Vector2

class InventoryScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    private class Window(
        val title: String,
        val position: Vector2,
        val size: Vector2,
        val render: (Window) -> Unit,
    )

    private val windows = listOf(
        Window("Equipments", Vector2(0, 0), Vector2(100, 20), ::renderEquipments),
        Window("Special", Vector2(100, 0), Vector2(50, 45), ::renderSpecial),
    )

    private lateinit var player: PlayerDto

    override fun start() {
        Terminal.clear()
        Terminal.refresh()

        player = context.getObject()
    }

    override fun end() {
        Terminal.clear()
        Terminal.refresh()
    }

    override fun update() {
        windows.forEach {
            drawBox(it.position, it.size, it.title.toUpperCase())
            it.render(it)
        }

        Terminal.refresh()
    }

    private fun renderEquipments(window: Window) {
        var drawPosition = window.position + Vector2(5, 2)

        fun draw(message: String, offset: Vector2 = Vector2(0, 1)) {
            Terminal.print(drawPosition, message)
            drawPosition += offset
        }

        draw("Weapon: ")
        draw("\t" + player.weaponComponent.weapon.toString())

        draw("Armors:")
        player.armorComponent.forEach { _, armor ->
            draw("\t" + armor.toString())
        }

        drawPosition += Vector2(0, 2)

        horizontalLine(window.position.x, drawPosition.y, window.size.x)
        Terminal.print(window.position + Vector2(3, 0), "Inventory".toUpperCase())
        drawPosition += Vector2(0, 2)

        draw("Available space: ${player.inventoryComponent.inventory.size} / ${player.inventoryComponent.capacity}", Vector2(0, 2))
        player.inventoryComponent.forEach {
            draw("\t" + it.toString())
        }
    }

    private fun renderSpecial(window: Window) {

    }

}