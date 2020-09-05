package sceneManager

import sceneManager.context.Context

open class IScene(
    private val sceneManager: SceneManager,
    private val context: Context
) {

    open fun start() {}
    open fun end() {}
    open fun update() {}

}