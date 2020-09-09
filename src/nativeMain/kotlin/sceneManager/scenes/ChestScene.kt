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
import sceneManager.scenes.utils.verticalLine
import terminal.Terminal
import utility.*

class ChestScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    private val position = Vector2(20, 10)
    private val size = Vector2(110, 25)

    private lateinit var player: Entity
    private lateinit var playerInventory: InventoryComponent
    private lateinit var chest: InventoryComponent

    private val cursor = Cursor()
    private val renderSide = RenderSide(::leftRender, ::rightRender)
    private lateinit var selector: Selector

    override fun start() {
        repeat(30) {
            Terminal.setLayer(it)
            Terminal.clearArea(position, size)
//            Terminal.crop(position, size)
        }

        player = context.getObject<ChestDto>().player
        playerInventory = player.getComponent()!!
        chest = context.getObject<ChestDto>().chest.getComponent()!!

        val playerSelector = ListSelector(playerInventory.inventory)
        val chestSelector = ListSelector(chest.inventory)

        selector = Selector(playerSelector, chestSelector)

        this.render()
    }

    override fun end() {
        Terminal.clear()
    }

    override fun update() {
        if (Input.getKey(KeyCode.Escape)) sceneManager.switchScene(SceneNames.GAME)

        if (Input.getKey(KeyCode.W) || Input.getKey(KeyCode.UpArrow)) selector.currentSelector.upward()
        if (Input.getKey(KeyCode.S) || Input.getKey(KeyCode.DownArrow)) selector.currentSelector.downward()

        if (Input.getKey(KeyCode.Space)) selectItem()

        renderSide.update()
        selector.update()

        this.render()
    }

    private fun selectItem() {
        if (selector.currentSelector.list.isEmpty()) return

        val item = selector.currentSelector.currentElement
        var playerIndex = 0
        var chestIndex = 0

        if (!selector.side) {
            if (chest.capacity == chest.inventory.size) return

            playerInventory.removeItem(selector.currentSelector.index)
            chest.addItem(item)
            if (playerInventory.inventory.size == selector.currentSelector.index)
                playerIndex = selector.currentSelector.index - 1
            else playerIndex = selector.currentSelector.index
        } else {
            if (playerInventory.capacity == playerInventory.inventory.size) return

            chest.removeItem(selector.currentSelector.index)
            playerInventory.addItem(item)

            if (chest.inventory.size == selector.currentSelector.index)
                chestIndex = selector.currentSelector.index - 1
            else chestIndex = selector.currentSelector.index
        }

        val playerSelector = ListSelector(playerInventory.inventory, playerIndex)
        val chestSelector = ListSelector(chest.inventory, chestIndex)

        selector = Selector(
            playerSelector,
            chestSelector,
            if (selector.side) KeyCode.RightArrow else KeyCode.LeftArrow
        )
    }

    private fun render() {
        Terminal.setLayer(50)
        Terminal.clearArea(position, size)

        drawBox(position, size, "CHEST")

        renderSide.currentRender()

        Terminal.refresh()
    }

    private fun leftRender() {
        var drawPosition = position + Vector2(5, 2)

        verticalLine(position.x + (size.x * 0.60).toInt(), position.y, size.y)

        Terminal.print(drawPosition, "Player inventory ${playerInventory.inventory.size} / ${playerInventory.capacity}")
        drawPosition += Vector2(0, 2)

        drawInventory(playerInventory, drawPosition, true)

        drawPosition = Vector2(position.x + (size.x * 0.60).toInt() + 5, position.y + 2)

        Terminal.setColor(Color(80, 80, 80, 255))
        Terminal.print(drawPosition, "Chest inventory ${chest.inventory.size} / ${chest.capacity}")
        drawPosition += Vector2(0, 2)

        drawInventory(chest, drawPosition)
        Terminal.setColor(Colors.WHITE)
    }

    private fun rightRender() {
        var drawPosition = position + Vector2(5, 2)

        verticalLine(position.x + (size.x * 0.40).toInt(), position.y, size.y)

        Terminal.setColor(Color(80, 80, 80, 255))
        Terminal.print(drawPosition, "Player inventory ${playerInventory.inventory.size} / ${playerInventory.capacity}")
        drawPosition += Vector2(0, 2)

        drawInventory(playerInventory, drawPosition)

        drawPosition = Vector2(position.x + (size.x * 0.40).toInt() + 5, position.y + 2)

        Terminal.setColor(Colors.WHITE)
        Terminal.print(drawPosition, "Chest inventory ${chest.inventory.size} / ${chest.capacity}")
        drawPosition += Vector2(0, 2)

        drawInventory(chest, drawPosition, true)
    }

    private fun drawInventory(
        inventory: InventoryComponent,
        drawPosition: Vector2,
        drawCursor: Boolean = false
    ) {
        if (inventory.inventory.isEmpty()) {
            Terminal.print(drawPosition, "It's empty")
            return
        }

        inventory.inventory.forEachIndexed { index, item ->
            if (drawCursor && selector.currentSelector.index == index) {
                cursor.print(drawPosition.x, drawPosition.y + index)
            }

            Terminal.print(drawPosition.x, drawPosition.y + index, item.toString())
        }

    }

    private class RenderSide(
        leftRender: () -> Unit,
        rightRender: () -> Unit,
    ) {

        private val sides = mapOf(
            KeyCode.LeftArrow to leftRender,
            KeyCode.RightArrow to rightRender,
            KeyCode.A to leftRender,
            KeyCode.D to rightRender,
        )

        var currentRender: () -> Unit = leftRender
            private set

        fun update() {
            currentRender = sides[Input.peek()] ?: return
        }

    }

    private class Selector(
        playerSelector: ListSelector<InventoryItem>,
        chestSelector: ListSelector<InventoryItem>,
        createdButton: KeyCode = KeyCode.LeftArrow,
    ) {

        private val sides = mapOf(
            KeyCode.LeftArrow to playerSelector,
            KeyCode.RightArrow to chestSelector,
            KeyCode.D to chestSelector,
            KeyCode.A to playerSelector,
        )

        private val sidesBool = mapOf<KeyCode?, Boolean>(
            KeyCode.LeftArrow to false,
            KeyCode.RightArrow to true,
            KeyCode.D to true,
            KeyCode.A to false,
        )

        var currentSelector = sides[createdButton] ?: error("bad created button")
            private set

        var side = sidesBool[createdButton] ?: error("bad created button")
            private set

        fun update() {
            side = sidesBool.getOrElse(Input.peek(), { side })
            currentSelector = sides[Input.peek()] ?: return
        }

    }

}