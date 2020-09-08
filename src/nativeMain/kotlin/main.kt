import atf.bearlibterminal.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sceneManager.SceneManager
import sceneManager.SceneNames
import sceneManager.context.ConfigDto
import sceneManager.context.Context
import sceneManager.scenes.ChestScene
import sceneManager.scenes.GameScene
import sceneManager.scenes.MenuScene
import terminal.Terminal
import utility.Config
import utility.reader.FileReader

fun main() {
    val terminal = Terminal

    val config = Json.decodeFromString<Config>(
        FileReader.read("/home/astefu/Documents/Roguelike/src/nativeMain/resources/config.json")
    )

    terminal.open()
    terminal.setSize(config.terminalSize.first, config.terminalSize.second).setCellSize(8, 16).setTitle(config.title)
    terminal.refresh()

    val context = Context()
    context.addObject { ConfigDto(config) }

    val sceneManager = SceneManager(context)
    sceneManager.addScene(SceneNames.MENU, MenuScene(sceneManager, context))
    sceneManager.addScene(SceneNames.GAME, GameScene(sceneManager, context))
    sceneManager.addScene(SceneNames.CHEST, ChestScene(sceneManager, context))

//    sceneManager.switchScene(menuScene)
    terminal.refresh()

//    val room = RoomBuilder().build()
//    room.engine.update()
//    terminal.refresh()

    while (true) {
        if (terminal.hasInput()) {
//            terminal.clear()

            if (terminal.peek() == TK_CLOSE) break

            sceneManager.update()

//            room.engine.update()
            terminal.refresh()
            terminal.read()
        }
    }

    terminal.close()
}