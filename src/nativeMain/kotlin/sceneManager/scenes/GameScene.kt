package sceneManager.scenes

import game.rooms.Room
import game.rooms.builder.RoomBuilder
import sceneManager.IScene
import sceneManager.SceneManager
import sceneManager.context.Context
import terminal.Terminal

class GameScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    private lateinit var room: Room

    override fun start() {
        Terminal.clear()
        room = RoomBuilder().build()
        room.engine.update()
        Terminal.refresh()
    }

    override fun end() {
        Terminal.clear()
        Terminal.refresh()
    }

    override fun update() {
        room.engine.update()
    }

}