package sceneManager.scenes

import game.rooms.Room
import game.rooms.builder.RoomBuilder
import sceneManager.IScene
import sceneManager.SceneManager
import sceneManager.context.Context
import sceneManager.context.PlayerDto
import terminal.Terminal

class GameScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    private var room: Room = RoomBuilder(sceneManager).build()

    override fun start() {
        Terminal.clear()
        room.engine.update()
        Terminal.refresh()
    }

    override fun end() {
//        Terminal.clear()
//        Terminal.refresh()
    }

    override fun update() {
        Terminal.clear()
        room.engine.update()
    }

}