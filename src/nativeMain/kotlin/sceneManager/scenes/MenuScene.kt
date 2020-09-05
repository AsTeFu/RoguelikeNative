package sceneManager.scenes

import atf.bearlibterminal.terminal_print
import sceneManager.IScene
import sceneManager.SceneManager
import sceneManager.context.Context

class MenuScene(sceneManager: SceneManager, context: Context) : IScene(sceneManager, context) {

    override fun update() {
        terminal_print(4, 10, "MENU");
    }

}