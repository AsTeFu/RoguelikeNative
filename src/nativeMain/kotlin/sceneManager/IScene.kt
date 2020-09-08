package sceneManager

import sceneManager.context.Context

open class IScene(
    protected val sceneManager: SceneManager,
    protected val context: Context
) {

    open fun start() {}
    open fun end() {}
    open fun update() {}

}