import atf.bearlibterminal.*
import game.rooms.builder.RoomBuilder
import sceneManager.SceneManager
import sceneManager.context.Context
import sceneManager.gameScene
import sceneManager.menuScene
import sceneManager.scenes.GameScene
import sceneManager.scenes.MenuScene
import terminal.Terminal

fun main() {
    val terminal = Terminal

    terminal.open()
    terminal.setSize(150, 45).setCellSize(8, 16)
    terminal.refresh()

    val context = Context()
    val sceneManager = SceneManager(context)
    sceneManager.addScene(menuScene, MenuScene(sceneManager, context))
    sceneManager.addScene(gameScene, GameScene(sceneManager, context))

//    sceneManager.switchScene(menuScene)
    terminal.refresh()

//    val room = RoomBuilder().build()
//    room.engine.update()
//    terminal.refresh()

    while (true) {
        if (terminal.hasInput()) {
            terminal.clear()

            if (terminal.peek() == TK_CLOSE) break
            
            sceneManager.update()

//            room.engine.update()
            terminal.refresh()
            terminal.read()
        }
    }
}