package sceneManager.scenes

import atf.bearlibterminal.color_from_name
import atf.bearlibterminal.terminal_color
import sceneManager.IScene
import sceneManager.SceneManager
import sceneManager.SceneNames
import sceneManager.context.ConfigDto
import sceneManager.context.Context
import sceneManager.scenes.utils.Cursor
import sceneManager.scenes.utils.ListSelector
import sceneManager.scenes.utils.box
import terminal.Terminal
import utility.*

class MenuScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    private val menuList = listOf(
        MenuItem("Start") { sceneManager.switchScene(SceneNames.GAME) },
        MenuItem("Editor") { println("Editor") },
        MenuItem("Viewer") { println("Viewer") },
        MenuItem("Setting") { println("Setting") },
        MenuItem("Exit") { Terminal.close() },
    )
    private val listSelector = ListSelector(menuList)
    private val cursor = Cursor(color = Colors.RED.color)

    private val size = context.getObject<ConfigDto>().config.terminalSize.toVector()
    private val title = "Roguelike game"

    override fun start() {
        this.render();
    }

    override fun update () {
        if (Input.getKey(KeyCode.W)) listSelector.upward()
        if (Input.getKey(KeyCode.S)) listSelector.downward()
        if (Input.getKey(KeyCode.Enter)) listSelector.currentElement.select()

        this.render()
    }

    private fun render() {
        box(Vector2(5, 5), Vector2(size.x - 10, 10), title)

        var x = size.x / 2 - 7
        var y = size.y / 2 - menuList.size * 2

//        Terminal.setColor(Color("#123456"))
//        Terminal.print(x - title.length / 2, y, title)
//        y += 4

        menuList.forEachIndexed { index, menuItem ->
            if (listSelector.currentElement.name === menuItem.name) {
                cursor.print(x, y + index * 2)
                Terminal.setColor(Colors.RED)
            } else Terminal.setColor(Colors.WHITE)

            Terminal.print(x, y + index * 2, menuItem.name)
        }
    }

    private data class MenuItem(
        val name: String,
        val select: () -> Unit
    )
}